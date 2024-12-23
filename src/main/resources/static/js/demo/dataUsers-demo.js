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
                <td>${user.id_user}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.country}</td>
                <td class="d-flex">
                    <a href="#" class="btn btn-success btn-circle btn-sm">
                        <i class="fa fa-shopping-cart"></i>
                    </a>
                    <a href="#" class="btn btn-danger btn-circle btn-sm">
                        <i class="fas fa-trash"></i>
                    </a>
                </td>
            </tr>`;
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

