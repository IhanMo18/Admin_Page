package com.boostrap.landingpage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class ProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_product;

    private String name;
    private Double price;
    private Integer stock;



    @OneToOne(mappedBy = "productEntity",cascade = CascadeType.PERSIST)
    @JsonBackReference
   PurchasedProductEntity purchasedProductEntity;

    public ProductEntity(String name, Double price,Integer stock) {
        this.name = name;
        this.price =price;
        this.stock = stock;
    }

    public ProductEntity() {
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }


    public PurchasedProductEntity getPurchasedProductEntity() {
        return purchasedProductEntity;
    }

    public void setPurchasedProductEntity(PurchasedProductEntity purchasedProductEntity) {
        this.purchasedProductEntity = purchasedProductEntity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stack) {
        this.stock = stack;
    }



}

