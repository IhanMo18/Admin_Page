package com.boostrap.landingpage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Product_Purchased")
public class PurchasedProductEntity {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_purchaseProduct;


    private Integer productQuantity;
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "id_order")
    @JsonBackReference
    OrderEntity orderEntity;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product")
    @JsonManagedReference
    ProductEntity productEntity;


    public PurchasedProductEntity(Integer productQuantity, OrderEntity orderEntity) {
        this.productQuantity = productQuantity;
        this.orderEntity = orderEntity;
    }

    public PurchasedProductEntity() {
    }


    public Integer getId_purchaseProduct() {
        return id_purchaseProduct;
    }

    public void setId_purchaseProduct(Integer id_item) {
        this.id_purchaseProduct = id_item;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = productQuantity * productEntity.getPrice();
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }


}
