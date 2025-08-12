document.addEventListener("DOMContentLoaded", function () {
    const toggleBtnMobile = document.getElementById("toggleSidebarMobile");
    const toggleBtnDesktop = document.getElementById("toggleSidebarDesktop");
    const collapseBtnMobile = document.getElementById("collapseSidebar");
    const sidebar = document.getElementById("sidebarMenu");
    const overlay = document.querySelector(".sidebar-overlay") || createOverlay();
    const body = document.body;
    const menuLinks = document.querySelectorAll("#sidebarMenuList .nav-link");

    // === Crear overlay si no existe ===
    function createOverlay() {
        const div = document.createElement("div");
        div.className = "sidebar-overlay";
        document.body.appendChild(div);
        return div;
    }

    // === Funciones de apertura/cierre ===
    function openSidebar() {
        sidebar.style.transform = "translateX(0)";
        body.classList.add("sidebar-open");
        overlay.style.display = "block";
        updateToggleButtons(true);
    }

    function closeSidebar() {
        sidebar.style.transform = "translateX(-250px)";
        body.classList.remove("sidebar-open");
        overlay.style.display = "none";
        updateToggleButtons(false);
    }

    function updateToggleButtons(isOpen) {
        const desktopHtml = isOpen
            ? '<i class="bi bi-plus-square me-1"></i> <small>Expandir</small>'
            : '<i class="bi bi-dash-square me-1"></i> <small>Menú</small>';
        const mobileHtml = isOpen
            ? '<i class="bi bi-plus-square"></i> <small>Expandir</small>'
            : '<i class="bi bi-dash-square"></i> <small>Menú</small>';

        if (toggleBtnDesktop) toggleBtnDesktop.innerHTML = desktopHtml;
        if (collapseBtnMobile) collapseBtnMobile.innerHTML = mobileHtml;
    }

    // === Estado inicial: SIEMPRE inicia cerrado ===
    closeSidebar(); // Forzamos cierre inicial
    localStorage.setItem('sidebarCollapsed', 'true'); // Aseguramos el estado

    // Si se recarga, respetar solo en escritorio
    window.addEventListener("resize", () => {
        if (window.innerWidth >= 992) {
            if (localStorage.getItem('sidebarCollapsed') === 'false') {
                openSidebar();
            } else {
                closeSidebar();
            }
        } else {
            closeSidebar(); // En móvil, siempre cerrado por defecto
        }
    });

    // Ejecutar una vez al cargar
    window.dispatchEvent(new Event('resize'));

    // === Eventos de toggle ===
    [toggleBtnMobile, toggleBtnDesktop, collapseBtnMobile].forEach(btn => {
        if (btn) {
            btn.addEventListener("click", () => {
                const isOpen = sidebar.style.transform === "translateX(0px)" ||
                               sidebar.style.transform === "translateX(0)";
                if (isOpen) {
                    closeSidebar();
                    localStorage.setItem('sidebarCollapsed', 'true');
                } else {
                    openSidebar();
                    localStorage.setItem('sidebarCollapsed', 'false');
                }
            });
        }
    });

    // Cerrar con overlay (móvil)
    overlay.addEventListener("click", closeSidebar);

    // === Manejo de enlaces activos (resaltado naranja) ===
    menuLinks.forEach(link => {
        link.addEventListener("click", function () {
            // Remover active de todos
            menuLinks.forEach(l => {
                l.classList.remove("active", "bg-orange", "text-white");
                l.style.backgroundColor = "";
                l.style.color = "";
            });

            // Aplicar active al actual
            this.classList.add("active", "bg-orange", "text-white");
            this.style.backgroundColor = "#fd7e14";
            this.style.color = "white";
        });

        // Resaltar el enlace actual al cargar (basado en la URL)
        if (window.location.pathname === link.getAttribute('href')) {
            link.classList.add("active", "bg-orange", "text-white");
            link.style.backgroundColor = "#fd7e14";
            link.style.color = "white";
        }
    });

    // Evitar FOUC
    body.classList.add("loaded");
});