// Call the dataTables jQuery plugin
$(document).ready(async function() {
    await loadProductsTable();
    $('#tabla_products').DataTable();
});

let products;

async function loadProductsTable(){
  
  const request = await fetch("/api/get/productsPurshased", {
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

    const request = await fetch(`/api/findProductIdByPurshasedId/${product.productPurchasedId}`, {
      method: "GET",
      headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
      }
  })

  const productEntityId = await request.json();

  console.log(productEntityId);

  const requestProductEntity = await fetch(`/api/find/product/${productEntityId}`, {
    method: "GET",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
})

  const productEntity = await requestProductEntity.json();

  console.log(productEntity);

  productsHtml+=`<tr>
                    <td>${product.productPurchasedId}</td>
                    <td>${productEntity.name}</td>
                    <td>${productEntity.price}</td>
                    <td>${product.quantity}</td>
                </tr>`
  }

  const tablaProductsHtml = document.getElementById("tabla_products_body");

  tablaProductsHtml.innerHTML = productsHtml;

  console.log(tablaProductsHtml);

}


// ------------------------PRODUCTOS END----------------------------
