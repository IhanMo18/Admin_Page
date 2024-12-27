// Call the dataTables jQuery plugin
$(document).ready(async function() {
        await loadDataTableProducts();
        $('#dataProducts').DataTable();

});

async function loadDataTableProducts() {
        const products = await getData();
        let table = document.querySelector(".table_products");
        makeTableProducts(products,table)

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

}

async function saveProduct() {
    const name = document.querySelector(".name").value;
    const price = document.querySelector(".price").value;
    const stock = document.querySelector(".stock").value;
    const label = document.querySelector(".infoProduct")

    let product = { name: name, price: price, stock: stock };

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

        location.reload()
    }




}

async function searchByProductName(){
    let search = document.querySelector(".search_input").value
    let searchNavBar= document.querySelector(".search_navBar").value
    console.log(search);
    let products = await getData();
    let table = document.querySelector(".table_products");
    let filterLetterSearch = await filterSearch(products,search)
    let filterLetterSearchNavBar =  await filterSearch(products, searchNavBar)

    if ((search && searchNavBar) === " ") {
        makeTableProducts(products,table);
    }if (search !==""){
        makeTableProducts(filterLetterSearch,table);
    }else if (searchNavBar !== ""){
        makeTableProducts(filterLetterSearchNavBar,table);
    }
}


async function filterSearch(products,search){
    let productsFind = []
    for (let i = 0;i<products.length;i++){

        if(products[i].name.toLowerCase().includes(search.toLowerCase())){
            productsFind.push({
                    name: products[i].name,
                    price: products[i].price,
                    stock:products[i].stock,
                    id_product:products[i].id_product,
                    inExist:products[i].inExist
                }
            )
        }
    }
    console.log(productsFind)
    return productsFind;
}
function btnAddProductsUpdate(){
    let num=0
    let texNum = document.querySelector(".number_area")
    texNum.value=num
    console.log(texNum.value)
}





 function makeTableProducts(products,table,){
        let data = ''

    for (const product of products) {

        if (product.inExist) {
            data += `<tr>
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
                      <button class="btn btn-outline-success btn-sm" type="button" onclick="btnAddProductsUpdate()">+</button
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

        }
        if (!product.inExist) {
            data += `<tr>
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
                      <button class="btn btn-outline-success btn-sm" type="button"">-</button>
                      <input type="text" class="form-control text-center number_area" value="0" style="max-width:60px">
                      <button class="btn btn-outline-success btn-sm" type="button" onclick="btnAddProductsUpdate()">+</button
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
    table.innerHTML = data
}


