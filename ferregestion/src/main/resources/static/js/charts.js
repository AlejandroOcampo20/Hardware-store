document.addEventListener("DOMContentLoaded", function () {
    // Gráfica de Ventas
    const salesCtx = document.getElementById('salesChart').getContext('2d');
    new Chart(salesCtx, {
        type: 'bar',
        data: {
            labels: ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'],
            datasets: [{
                label: 'Ventas ($)',
                data: [1200, 1900, 1500, 2200, 1800, 2500, 1700],
                backgroundColor: '#fd7e14',
                borderRadius: 6
            }]
        },
        options: { responsive: true, plugins: { legend: { display: false } } }
    });

    // Gráfica de Productos
    const productsCtx = document.getElementById('productsChart').getContext('2d');
    new Chart(productsCtx, {
        type: 'doughnut',
        data: {
            labels: ['Herramientas', 'Electricidad', 'Plomería', 'Pintura'],
            datasets: [{
                data: [40, 25, 20, 15],
                backgroundColor: ['#fd7e14', '#007bff', '#28a745', '#ffc107']
            }]
        },
        options: { responsive: true, plugins: { legend: { position: 'bottom' } } }
    });
});