// Call the dataTables jQuery plugin
$(document).ready(async function() {
    await loadDataTable();
    $('#dataTable').DataTable();
});

let users;

async function loadDataTable() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/users', {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        users = await response.json();

        let table = "";

        for (const user of users) {
            table += `<tr>
                <td>${user.id_user}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.country}</td>
                <td class="d-flex">
                
                <a href="#" class="btn-success btn-circle btn-sm">
                  <i class="fa fa-upload "></i>
                  </a>
                
               
               <a href="#" class="btn btn-warning btn-circle btn-sm">
                <i class="fa fa-exclamation-triangle"></i>
                </a>
                
                <a href="#"  class="btn btn-danger btn-circle btn-sm" ${user.username}>
                <i class="fas fa-trash"></i>
                </a>
                
            
                </td>
                
                
            </tr>`;
        }

        const data = document.querySelector(".table_user");
        data.innerHTML = table;

    } catch (error) {
        console.error("Error al cargar los datos:", error);
    }
}











