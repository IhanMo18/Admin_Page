// Call the dataTables jQuery plugin
$(document).ready(async function() {
        await loadDataTableProducts();
        $('#dataProducts').DataTable();

});

async function loadDataTableProducts() {
        const products = await getData();
        console.log(products)

        let table = '';
        for (let product of products) {


            if (product.inExist) {
                table += `<tr>
                <td>${product.id_product}</td>
                <td>${product.name}</td>
                <td>${product.price}$</td>
                <td>${product.stock}</td>
                <td>
                    <i class="fa fa-circle text-success">
                    </i>
                </td>
                
                <td>
                   <div class="input-group" style="max-width: 150px;">
                      <button class="btn btn-outline-success btn-sm" type="button">-</button>
                      <input type="text" class="form-control text-center" value="0" style="max-width: 60px;">
                      <button class="btn btn-outline-success btn-sm" type="button">+</button
                   </div>
                </td>
            
                 <td>
                    <a  class="btn btn-danger btn-circle btn-sm delete"  onclick="deleteProduct(event)" id=${product.id_product}>
                        <i class="fas fa-trash"></i>
                    </a>
                    
                     <a  class="btn btn-success btn-circle btn-sm refresh"  id=${product.id_product}>
                        <i class="fas fa-arrow-up"></i>
                    </a>
                    
                  </td>
                  
                  
                
              
              </tr>`

            }if (!product.inExist) {
                table += `<tr>
                <td class="bg-gray-100">${product.id_product}</td>
                <td class="bg-gray-100">${product.name}</td>
                <td class="bg-gray-100">${product.price}$</td>
                <td class="bg-gray-100">${product.stock}</td>
                <td class="bg-gray-100">
                    <i class="fa fa-circle text-danger bg-gray-100">
                    </i>
                </td>
                

                <td class="justify-content-center align-items-center bg-gray-100">
                   <div class="input-group flex-nowrap" style="max-width:150px;">
                      <button class="btn btn-outline-success btn-sm" type="button">-</button>
                      <input type="text" class="form-control text-center" value="0" style="max-width:60px">
                      <button class="btn btn-outline-success btn-sm" type="button">+</button
                   </div>
                </td>
            
                 <td class="bg-gray-100">
                     <a  class="btn btn-success btn-circle btn-sm refresh"  id=${product.id_product}>
                        <i class="fas fa-arrow-up"></i>
                    </a>
                    
                  </td>
                
              
              </tr>`
            }
        }

        let data = document.querySelector(".table_products");
        if (data) {
            data.innerHTML = table;
        } else {
            console.warn("No se encontró el elemento con la clase 'table_products'.");
        }
}

// Obtengo la data de products del backend
let getData = async () => {
    const response = await fetch('http://localhost:8080/api/v1/products');
    return await response.json();
};


async function deleteProduct(event){
    event.preventDefault();
    const btnDelete = event.target.closest(".delete");
    const productId = btnDelete.getAttribute("id");
    const response=await fetch(`http://localhost:8080/api/v1/product/delete/${productId}`,{
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }})


    if (response.ok)location.reload();

    if (responsePurchase.ok){



    }

}

async function saveProduct() {
    const name = document.querySelector(".name").value;
    const price = document.querySelector(".price").value;
    const stock = document.querySelector(".stock").value;
    const label = document.querySelector(".infoProduct")
    console.log(name, price, stock);
    console.log("ENTRE");


    let product = { name: name, price: price, stock: stock };
    console.log(product)

    if (!product){
        console.log("entre en no es null")
    }else{

        const response = await fetch("http://localhost:8080/api/v1/product/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(product),
        });

        if (!response.ok) {
            console.error(`HTTP error! status: ${response.status}`);
        } else {
            const data = await response.json();
            console.log(data);
        }

        location.reload()
    }

}
