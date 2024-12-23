// Call the dataTables jQuery plugin
$(document).ready(async function() {
    await loadOrderTable();
    $('#tabla_products').DataTable();
});


let orders;

async function loadOrderTable(){
  
  const request = await fetch("/api/get/tickets", {
    method: "GET",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
});

  if(!request.ok) 
    throw new Error("No hay ninguna orden registrada");

  orders = await request.json();

  console.log(orders);

  let ordersHtml = " ";

  for(let order of orders){
    ordersHtml+= `<tr>
                    <td>${order.order_id}</td>
                    <td>${order.date}</td>
                    <td>${order.total}</td>
                    <td>
                      <a href="#" onclick="seeProductsByOrderId(event)" id=${order.order_id} class="btn btn-info btn-circle btn-sm">
                        <i class="fas fa-info-circle"></i>
                      </a>
                      <a href="#" onclick="deleteOrder(event)" id=${order.order_id} class="btn btn-danger btn-circle btn-sm" >
                        <i class="fas fa-trash"></i>
                      </a>
                    </td>
                </tr>`
  }

  const tablaOrdersBodyHtml = document.getElementById("tabla_orders_body");

  tablaOrdersBodyHtml.innerHTML = ordersHtml;

  console.log(tablaOrdersBodyHtml);

}


async function deleteOrder(event){
  event.preventDefault();

  const btnDelete = event.target.closest(".btn-danger");

  console.log(btnDelete);

  const orderId = btnDelete.getAttribute("id");

  await fetch(`/api/delete/ticket/${orderId}`,{
    method: 'DELETE', 
    headers: {
      'Content-type': 'application/json; charset=UTF-8' 
    }})

  location.reload();
}


//Funcion para obtener los parametros por url

let ordersById;

function getQueryParam(param){
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(param);
}

  const userId = getQueryParam("userId");

  if(userId) filterOrderByUserId(userId);


  console.log(userId);

  async function filterOrderByUserId(userId){
  
  const request = await fetch(`/api/findOrdersById/${userId}`, {
      method: "GET",
      headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
      }
  });

  console.log(request);

  ordersById = await request.json();


  console.log(ordersById);

  let ordersHtml = "";

  for(let order of ordersById){
    ordersHtml+=`<tr>
                    <th>${order.id}</th>
                    <th>${order.date}</th>
                    <th>${order.total}</th>
                    <th>
                      <a href="#" onclick="seeProductsByOrderId(event)" id=${order.order_id} class="btn btn-info btn-circle btn-sm">
                        <i class="fas fa-info-circle"></i>
                      </a>
                      <a href="#" onclick="deleteOrder(event)" id=${order.order_id} class="btn btn-danger btn-circle btn-sm" >
                        <i class="fas fa-trash"></i>
                      </a></th>
                  </tr>`
  }

  const orderTableHtml = document.getElementById("tabla_orders_body");

  console.log(ordersHtml)

  orderTableHtml.innerHTML=ordersHtml;

  console.log(orderTableHtml);

}