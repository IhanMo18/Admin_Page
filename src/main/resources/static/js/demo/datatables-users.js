// Call the dataTables jQuery plugin
$(document).ready(async function() {
  await loadUserTable();
  $('#tabla_usuarios').DataTable();
});

// ------------------------Usuarios START----------------------------

let users;

async function loadUserTable(){
  
  const request = await fetch("/api/get/users", {
    method: "GET",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
});

  if(!request.ok) 
    throw new Error("No hay ningun usuario registrado");

  users = await request.json();

  console.log(users);

  let usersHtml = " ";

  for(let user of users){
    usersHtml+= `<tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>
                      <a href="#" onclick="redirectToOrders(${user.id})" id=${user.id} class="btn btn-success btn-circle btn-sm">
                        <i class="fa fa-shopping-cart"></i>
                      </a>
                  
                      <a href="#" onclick="deleteUser(event)" id=${user.id} class="btn btn-danger btn-circle btn-sm" >
                        <i class="fas fa-trash"></i>
                      </a>
                    </td>
                </tr>`
  }

  const tablaBodyHtml = document.getElementById("tabla_body");

  console.log(tablaBodyHtml);

  console.log(usersHtml);

  tablaBodyHtml.innerHTML = usersHtml;

  console.log(tablaBodyHtml);

}


async function deleteUser(event){
  event.preventDefault();

  const btnDelete = event.target.closest(".btn-danger");

  console.log(btnDelete);

  const userId = btnDelete.getAttribute("id");

  await fetch(`/api/delete/user/${userId}`,{
    method: 'DELETE', 
    headers: {
      'Content-type': 'application/json; charset=UTF-8' 
    }})
  location.reload();
}

function redirectToOrders(id){
  window.location.replace(`tabla-orders.html?userId=${id}`);
}


// async function seeOrdersByUserId(event){
//   event.preventDefault();

//   const btnSeeOrders = event.target.closest(".btn-success");

//   const userId = btnSeeOrders.getAttribute("id");

//   let request = await fetch(`/api/findOrdersById/${userId}`, {
//     method: "GET",
//     headers: {
//         "Accept": "application/json",
//         "Content-Type": "application/json"
//     }
// })

//   const listOrders = await request.json();

//   console.log(listOrders);

//   let ordersHtml="";

//   for(let order of listOrders){
//     ordersHtml += `<tr>
//                     <td>${order.id}</td>
//                     <td>${order.date}</td>
//                     <td>${order.total}</td>
//                     <td>
//                       <a href="#" onclick="seeProductsListByOrderId(event)" id=${order.id} class="btn btn-info btn-circle btn-sm">
//                         <i class="fas fa-info-circle"></i>
//                       </a>
//                       <a href="#" onclick="deleteOrder(event)" id=${order.id} class="btn btn-danger btn-circle btn-sm" >
//                         <i class="fas fa-trash"></i>
//                       </a>
//                     </td>
//                 </tr>`
//   }

//   const ordersHtmlBody = document.getElementById("tabla_orders_body");

//   ordersHtmlBody.innerHTML = ordersHtml;

// }

// ------------------------USUARIOS END----------------------------


// ------------------------PRODUCTOS START----------------------------



// ------------------------ORDERS START----------------------------



// ------------------------ORDERS END----------------------------


// async function seeProductsListByOrderId(event){
//   event.preventDefault();

//   const btnInfo = event.target.closest(".btn-info");

//   const orderId = btnInfo.getAttribute("id");

//   const request = await fetch(`/api/get/productsByTicketId/${orderId}`, {
//     method: "GET",
//     headers: {
//         "Accept": "application/json",
//         "Content-Type": "application/json"
//     }
// })

//   const listProducts = await request.json();

//   console.log(listProducts);

//   let productsHtml = "";

//   for(let product of listProducts){

//     const request = await fetch(`/api/findProductIdByPurshasedId/${product.productPurchasedId}`, {
//       method: "GET",
//       headers: {
//           "Accept": "application/json",
//           "Content-Type": "application/json"
//       }
//   })

//   const productEntityId = await request.json();

//   console.log(productEntityId);

//   const requestProductEntity = await fetch(`/api/find/product/${productEntityId}`, {
//     method: "GET",
//     headers: {
//         "Accept": "application/json",
//         "Content-Type": "application/json"
//     }
// })

//   const productEntity = await requestProductEntity.json();

//   console.log(productEntity);

//   productsHtml+=`<tr>
//                     <td>${product.productPurchasedId}</td>
//                     <td>${productEntity.name}</td>
//                     <td>${productEntity.price}</td>
//                     <td>${product.quantity}</td>
//                 </tr>`
//   }

//   const tablaProductsHtml = document.getElementById("tabla_productsByOrderId");

//   tablaProductsHtml.innerHTML = productsHtml;

// }