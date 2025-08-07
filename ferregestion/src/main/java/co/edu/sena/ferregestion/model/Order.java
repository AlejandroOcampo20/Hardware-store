package co.edu.sena.ferregestion.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client id_client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee id_employee;

    @ManyToOne
    @JoinColumn(name = "product_id")

    private Product id_product;
    private long quantity;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getId_client() {
        return id_client;
    }

    public void setId_client(Client id_client) {
        this.id_client = id_client;
    }

    public Employee getId_employee() {
        return id_employee;
    }

    public void setId_employee(Employee id_employee) {
        this.id_employee = id_employee;
    }

    public Product getId_product() {
        return id_product;
    }

    public void setId_product(Product id_product) {
        this.id_product = id_product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
