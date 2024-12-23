// Call the dataTables jQuery plugin
$(document).ready(async function() {
        await loadDataTableProducts();
        let deleteLabel = document.querySelector(".delete")
        deleteLabel.g
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
                <td class="d-flex center">
                    <i class="fa fa-circle text-success">
                    </i>
                </td>
                
                <td>
                   <div class="input-group" style="max-width: 150px;">
                      <button class="btn btn-outline-secondary btn-sm" type="button">-</button>
                      <input type="text" class="form-control text-center" value="0" style="max-width: 60px;">
                      <button class="btn btn-outline-secondary btn-sm" type="button">+</button
                   </div>
                </td>
            
                 <td>
                    <a href="#" class="btn btn-danger btn-circle btn-sm">
                        <i class="fas fa-trash"></i>
                    </a>
                  </td>
                
              
              </tr>`;

            } else {
                table += `<tr>
                <td>${product.id_product}</td>
                <td>${product.name}</td>
                <td>${product.price}$</td>
                <td>${product.stock}</td>
                <td class="d-flex">
                    <i class="fa fa-circle text-danger">
                    </i>
                </td>;
                

                <td>
                   <div class="input-group" style="max-width: 150px;">
                      <button class="btn btn-outline-secondary btn-sm" type="button">-</button>
                      <input type="text" class="form-control text-center" value="0" style="max-width: 60px;">
                      <button class="btn btn-outline-secondary btn-sm" type="button">+</button
                   </div>
                </td>
            
                 <td>
                    <a class="btn btn-danger btn-circle btn-sm delete" value={product.id_product}>
                        <i class="fas fa-trash"></i>
                    </a>
                  </td>
                
              
              </tr>`;
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


async function deleteProduct(id){
    const response =fetch(`api/v1/product/delete/${id}`)
    return await response.json()
}
