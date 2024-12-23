// Call the dataTables jQuery plugin
$(document).ready(async function() {
    await loadProductsStockTable();
    $('#tabla_products_stock').DataTable();
});

let products;

async function loadProductsStockTable(){
  
  const request = await fetch("/api/get/products", {
    method: "GET",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
});

  if(!request.ok) 
    throw new Error("No hay ningun producto registrado");

  products = await request.json();

  console.log(products);

  let productsHtml = " ";

  for(let product of products){
    productsHtml+=`<tr>
                      <td>${product.id}</td>
                      <td>${product.name}</td>
                      <td>${product.price}</td>
                      <td>${product.quantity}</td>
                      <td>
                        <a href="#" id=${product.id} class="btn btn-success btn-circle btn-sm">
                          <i class="fas fa-check"></i>
                        </a>
                        <a href="#" id=${product.id} class="btn btn-warning btn-circle btn-sm">
                          <i class="fas fa-exclamation-triangle"></i>
                        </a>
                        <a href="#" onclick="deleteProduct(event)" id=${product.id} class="btn btn-danger btn-circle btn-sm" >
                          <i class="fas fa-trash"></i>
                        </a>
                    </td>
                  </tr>`
  }

  const tablaProductsHtml = document.getElementById("tabla_products_stock_body");

  tablaProductsHtml.innerHTML = productsHtml;

  console.log(tablaProductsHtml);

}


// ------------------------PRODUCTOS END----------------------------
async function deleteProduct(event){
  event.preventDefault();

  const btnDelete = event.target.closest(".btn-danger");

  console.log(btnDelete);

  const productId = btnDelete.getAttribute("id");

  await fetch(`/api/delete/product/${productId}`,{
    method: 'DELETE', 
    headers: {
      'Content-type': 'application/json; charset=UTF-8' 
    }})

  location.reload();
}