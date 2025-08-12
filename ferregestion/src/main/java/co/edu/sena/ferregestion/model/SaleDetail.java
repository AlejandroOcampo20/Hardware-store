package co.edu.sena.ferregestion.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer subtotal;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Getters y Setters...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}