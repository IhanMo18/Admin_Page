package com.boostrap.landingpage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Order_entity")
public class OrderEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer order_id;


    @Column(columnDefinition = "DATE")
    private LocalDate orderDate;

    private Double total;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinColumn(name = "id_user")
    private UserEntity user;


    @OneToMany(mappedBy = "orderEntity",
    cascade = CascadeType.REMOVE)
   @JsonManagedReference
   private List <PurchasedProductEntity> purchasedProductEntityList;




    public OrderEntity(UserEntity user, LocalDate orderDate) {
        this.user=user;
        this.orderDate=orderDate;

    }

    public OrderEntity() {

    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer orderItem_id) {
        this.order_id = orderItem_id;
    }


    public List<PurchasedProductEntity> getPurchasedProductEntityList() {
        return purchasedProductEntityList;
    }

    public void setPurchasedProductEntityList(List<PurchasedProductEntity> purchasedProductEntityList) {
        this.purchasedProductEntityList = purchasedProductEntityList;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


}
