package org.example.robinsonaguilar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sale")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idClient")
    private Integer id;
    @Column(name = "dateOfSale",nullable = false,length = 20)
    private LocalDate date_of_sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,foreignKey = @ForeignKey(name = "fk_sale_to_customer"))
    @JsonIgnore
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "idProduct")
            //inverseJoinColumns = @JoinColumn(name = "idProducto")
    )
    private List<Product> products;

    public Sale() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate_of_sale() {
        return date_of_sale;
    }

    public void setDate_of_sale(LocalDate date_of_sale) {
        this.date_of_sale = date_of_sale;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
