function exportToPDF() {
    const type = document.getElementById("reportType").value;
    if (!type) {
        alert("Selecciona un tipo de reporte.");
        return;
    }
    window.open(`/pdf/report?type=${encodeURIComponent(type)}`, '_blank');
}

function previewReport() {
    const type = document.getElementById("reportType").value;
    if (!type) {
        alert("Selecciona un tipo de reporte.");
        return;
    }

    const previewDiv = document.getElementById("reportPreview");
    const cardBody = previewDiv.querySelector(".card-body");

    cardBody.innerHTML = `
        <div class="text-center">
            <div class="spinner-border text-orange mb-3" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p>Generando previsualización del reporte...</p>
        </div>
    `;

    setTimeout(() => {
        cardBody.innerHTML = `
            <div class="text-center text-success">
                <i class="bi bi-check-circle" style="font-size: 3rem;"></i>
                <h5 class="mt-3">Reporte listo para exportar</h5>
                <p class="text-muted">Haz clic en "Exportar PDF" para descargar el archivo.</p>
            </div>
        `;
    }, 1500);
}