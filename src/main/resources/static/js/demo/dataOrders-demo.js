// Call the dataTables jQuery plugin
$(document).ready(async function() {
    await loadDataTableOrders();
    $('#dataUsers').DataTable();
});

async function loadDataTableOrders() {
    const response = await fetch('http://localhost:8080/api/v1/order/details');
    let orders = await response.json();
    let cardOrders = "";


    for (const order of orders) {
        cardOrders +=

            `<div class="mb-3 card_order">
                <div class="card   border-bottom-secondary shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">

                            <div class="col mr-1 ml-1">
                                <div class="text-xs font-weight-bold text-dark mb-1">
                                    <h6>ID Order: <span class="text-secondary">${order.id_order}</span></h6>
                                </div>
                            </div>

                            <div class="col mr-1 ml-1">
                                <div class="text-xs font-weight-bold text-dark mb-1">
                                    <h6>ID User: <span class="text-secondary">${order.id_user}</span></h6>
                                </div>
                            </div>

                            <div class="col">
                                <h6>Total: <span class="text-success">${order.total}$</span></h6>
                            </div>
                            
                            <div class="col w-25 d-flex justify-content-end align-items-end">
                                <button type="button" class="btn btn-outline-secondary info text-nowrap text-center" id="${order.id_order}" onclick="showModalWindow(${order.id_order})" data-bs-toggle="modal" data-bs-target="#modal">+</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>`;
    }

    const tableOrders = document.querySelector(".table_orders");
    tableOrders.innerHTML = cardOrders;

}


async function showModalWindow(id) {
    const bodyModal = document.querySelector(".modal-body")
    let details = ''
    const purchasedProducts = await getPurchasedProducts()
    for (const obj of purchasedProducts){
        if (obj.id_Order === id){
           let product = await geProducts(obj.id_product)
            details +=  `<div class="card_order">
                <div class="card border-left-dark shadow h-100 py-2 mt-2">
                    <div class="card-body">
                        
                        <div class="col no-gutters align-items-center">
                        
                            <div class="col-3 mr-2 ml-2">
                                <div class="text-xs font-weight-bold text-dark mb-1">
                                    <h6 class="text-uppercase font-weight-bold text-dark text-nowrap">Product:<span class="text-secondary text-nowrap">${product.name}</span></h6>
                                </div>
                            </div>

                            <div class="col-4 mr-2 ml-2">
                                <div class="text-xs font-weight-bold text-dark mb-1">
                                    <h6 class="text-uppercase font-weight-bold text-dark text-nowrap">Quantity: <span class="text-secondary text-nowrap">${obj.productQuantity}</span></h6>
                                </div>
                            </div>
                        
                        </div>
                        
                        <div class="mt-1 col no-gutters align-items-center">

                            <div class="col-3 mr-2 ml-2">
                                <div class="text-xs font-weight-bold text-dark mb-1">
                                    <h6 class="text-uppercase font-weight-bold text-dark text-nowrap">Price:<span class="text-secondary text-nowrap">${product.price}$</span></h6>
                                </div>
                            </div>
                            
                             <div class="col-4 mr-2 ml-2">
                                <h6 class="text-uppercase font-weight-bold text-dark text-nowrap">subTotal: <span class="text-success text-nowrap">${obj.subTotal}$</span></h6>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
            </div>`;
        }else{
            details = `<h2 class="text-center text-gray-100 text-danger">Don't exist details for this order</h2>`
        }
    }

    bodyModal.innerHTML=details
}

async function getPurchasedProducts() {
    const response = await fetch('http://localhost:8080/api/v1/purchase/products');
    return await response.json();
}
let geProducts = async (id) => {

     const response = await fetch(`/api/v1/product/${id}`,{
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }})
    return response.json()
};
