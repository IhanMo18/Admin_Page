document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname;

    const navLinks = document.querySelectorAll(".nav-item a");

    navLinks.forEach(link => {
        if (link.getAttribute("href") === currentPath) {
        document.querySelectorAll(".nav-item").forEach(item => item.classList.remove("active"));
        link.parentElement.classList.add("active");
    }
    });
});