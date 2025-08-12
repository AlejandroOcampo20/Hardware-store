/**
 * Filtros dinámicos para la tabla de ventas
 * Compatible con Thymeleaf, fechas DD/MM/YYYY y búsqueda sin errores
 */
document.addEventListener("DOMContentLoaded", function () {
    // === Selección de elementos ===
    const statusFilter = document.getElementById('statusFilter');
    const dateFilter = document.getElementById('dateFilter');
    const searchFilter = document.getElementById('searchFilter');
    const dataTable = document.getElementById('dataTable');

    // Validar que la tabla exista
    if (!dataTable) {
        console.warn('No se encontró la tabla #dataTable');
        return;
    }

    const tbody = dataTable.querySelector('tbody');
    const rows = tbody ? tbody.getElementsByTagName('tr') : [];

    if (rows.length === 0) {
        console.warn('No hay filas en la tabla para filtrar');
        return;
    }

    // === Función: formatear fecha YYYY-MM-DD → DD/MM/YYYY ===
    function formatDate(input) {
        if (!input) return '';
        const [year, month, day] = input.split('-');
        return `${day}/${month}/${year}`;
    }

    // === Función: aplicar filtros ===
    function filterTable() {
        const statusValue = (statusFilter?.value || '').toLowerCase();
        const dateValue = dateFilter?.value; // YYYY-MM-DD
        const searchValue = (searchFilter?.value || '').toLowerCase().trim();

        const formattedDate = dateValue ? formatDate(dateValue) : '';

        // Iterar sobre cada fila
        Array.from(rows).forEach(row => {
            const cells = row.getElementsByTagName('td');

            // Validar que la fila tenga suficientes celdas
            if (cells.length < 7) return;

            const statusText = (cells[5]?.textContent || '').toLowerCase();
            const dateText = cells[1]?.textContent.split(' ')[0]; // Solo fecha (sin hora)
            const clientText = (cells[2]?.textContent || '').toLowerCase();
            const employeeText = (cells[3]?.textContent || '').toLowerCase();

            let showRow = true;

            // Filtro por estado
            if (statusValue && !statusText.includes(statusValue)) {
                showRow = false;
            }

            // Filtro por fecha
            if (formattedDate && dateText !== formattedDate) {
                showRow = false;
            }

            // Filtro por texto (cliente o empleado)
            if (searchValue) {
                const matchesClient = clientText.includes(searchValue);
                const matchesEmployee = employeeText.includes(searchValue);
                if (!matchesClient && !matchesEmployee) {
                    showRow = false;
                }
            }

            // Mostrar u ocultar fila
            row.style.display = showRow ? '' : 'none';
        });
    }

    // === Función: limpiar filtros ===
    window.clearFilters = function () {
        if (statusFilter) statusFilter.value = '';
        if (dateFilter) dateFilter.value = '';
        if (searchFilter) searchFilter.value = '';
        filterTable();
    };

    // === Escuchar cambios en los filtros ===
    if (statusFilter) {
        statusFilter.addEventListener('change', filterTable);
    }

    if (dateFilter) {
        dateFilter.addEventListener('change', filterTable);
    }

    if (searchFilter) {
        searchFilter.addEventListener('input', filterTable);
    }

    // Aplicar filtros iniciales (por si hay valores predeterminados)
    filterTable();

});