package co.edu.sena.ferregestion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @Column(nullable = false)
    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatus status;

    @Column(length = 200)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; // Opcional

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleDetail> saleDetails;

    public Sale() {
        this.saleDate = LocalDateTime.now();
        this.status = SaleStatus.PENDING;
    }

    // Getters y Setters...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }
}