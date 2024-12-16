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
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_order_detail")
    @JsonBackReference
    OrderDetailEntity orderDetailEntity;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    @JsonManagedReference
    ProductEntity productEntity;


    public PurchasedProductEntity( Integer productQuantity,OrderDetailEntity orderDetailEntity) {
        this.productQuantity = productQuantity;
        this.orderDetailEntity = orderDetailEntity;
        this.productEntity = productEntity;
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

    public Double getTotal() {
        return this.total;
    }

    public void setTotal() {
        this.total = productQuantity * productEntity.getPrice();
    }

    public OrderDetailEntity getOrderDetailEntity() {
        return orderDetailEntity;
    }

    public void setOrderDetailEntity(OrderDetailEntity orderDetailEntity) {
        this.orderDetailEntity = orderDetailEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }


}
