package org.example.robinsonaguilar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idClient")
    private Integer id;
    @Column(name = "name",nullable = false,length = 20)
    private String name;
    @Column(name = "last-name",nullable = false,length = 20)
    private String lastName;
    @Column(name = "age",nullable = false)
    private int age;
   @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
   @JsonIgnore
    private List<Sale> sales;

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

}
