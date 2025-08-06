package co.edu.sena.ferregestion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "El nombre del producto es obligatorio")
    @NotBlank(message = "La categoría es obligatoria")
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    @NotNull(message = "La cantidad disponible es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @NotNull(message = "Debe seleccionar un proveedor")
    private long id;
    private String name;
    private String catgeory;
    private BigDecimal price;
    private long quantity;
    @ManyToOne
    @JoinColumn(name = "id")
    private Supplier id_supplier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatgeory() {
        return catgeory;
    }

    public void setCatgeory(String catgeory) {
        this.catgeory = catgeory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Supplier getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(Supplier id_supplier) {
        this.id_supplier = id_supplier;
    }
}
