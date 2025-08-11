package co.edu.sena.ferregestion.model;

public enum SaleStatus {
    PENDING("Pendiente"),
    COMPLETED("Completada"),
    CANCELLED("Cancelada");

    private final String displayName;

    SaleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}