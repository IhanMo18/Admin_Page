package com.boostrap.landingpage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer orderDetail_id;

    private Integer subTotal;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private OrderEntity order;


    @OneToMany(mappedBy = "orderDetailEntity",
    cascade = CascadeType.ALL)
   @JsonManagedReference
   private List <PurchasedProductEntity> purchasedProductEntityList;




    public OrderDetailEntity(OrderEntity order) {
        this.order=order;

    }

    public OrderDetailEntity() {

    }

    public Integer getOrderDetail_id() {
        return orderDetail_id;
    }

    public void setOrderDetail_id(Integer orderItem_id) {
        this.orderDetail_id = orderItem_id;
    }


    public Integer getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<PurchasedProductEntity> getPurchasedProductEntityList() {
        return purchasedProductEntityList;
    }

    public void setPurchasedProductEntityList(List<PurchasedProductEntity> purchasedProductEntityList) {
        this.purchasedProductEntityList = purchasedProductEntityList;
    }


}
