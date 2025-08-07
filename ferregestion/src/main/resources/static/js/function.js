document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.getElementById("toggleSidebar");
    const sidebar = document.getElementById("sidebarMenu");
    const body = document.body;
    const mainContainer = document.querySelector(".d_flex");

    if (toggleButton && sidebar && mainContainer) {
        toggleButton.addEventListener("click", function () {
            sidebar.classList.toggle("collapsed");
            body.classList.toggle("sidebar-collapsed");
        });

        // Cerrar menú al hacer clic en cualquier enlace
        const menuLinks = sidebar.querySelectorAll(".nav-link");
        menuLinks.forEach(link => {
            link.addEventListener("click", function () {
                sidebar.classList.add("collapsed");
                body.classList.add("sidebar-collapsed");
            });
        });
    }
});