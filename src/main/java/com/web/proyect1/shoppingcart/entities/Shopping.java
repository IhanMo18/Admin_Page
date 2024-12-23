package com.web.proyect1.shoppingcart.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Shopping {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate date;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private Double total;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
    private Usuario user;

    @OneToMany(
            mappedBy = "shopping",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<ProductPurshased> products;

    public Shopping() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Usuario getUser_id() {
        return user;
    }

    public void setUser_id(Usuario user_id) {
        this.user = user_id;
    }

    public List<ProductPurshased> getProducts() {
        return products;
    }

    public void setProducts(List<ProductPurshased> products) {
        this.products = products;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
