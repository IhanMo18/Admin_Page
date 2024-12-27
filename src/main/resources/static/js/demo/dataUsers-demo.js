// Call the dataTables jQuery plugin
$(document).ready(async function() {
    try {
        await loadDataTableUsers();
        $('#dataUsers').DataTable();
    } catch (error) {
        console.error("Error durante la inicialización de la tabla:", error);
    }
});

async function loadDataTableUsers() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/users');

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        let users = await response.json();
        let table = "";

        for (const user of users) {

            table += `<tr>
                <td class="text-secondary">${user.id_user}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td class="text-uppercase role">${user.role}</td>
                <td  class="text-uppercase">${user.country}</td>
                <td>
                
                    <a href="#" class="btn btn-danger btn-circle btn-sm">
                        <i class="fas fa-trash"></i>
                    </a>
                    
                    <a href="#" class=" btn btn-secondary btn-circle btn-sm">
                        <i class="fa fa-list"></i>
                    </a>
                    
                </td>
            </tr>`;

            if (user.role.toString() === "ADMIN"){
                console.log("es")
                let role = document.querySelector(".role")
                role.className="text-info"
            }

        }

        const data = document.querySelector(".table_user");
        if (!data) {
            console.warn("No se encontró el elemento con la clase 'table_user'.");
            return;
        }

        data.innerHTML = table;
    } catch (error) {
        console.error("Ocurrió un error al cargar los usuarios:", error);
    }
}

