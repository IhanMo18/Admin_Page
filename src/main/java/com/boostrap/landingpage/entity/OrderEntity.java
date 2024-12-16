package com.boostrap.landingpage.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Order_Table")
public class OrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_order;


    @Column(columnDefinition = "DATE")
    private LocalDate orderDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity userEntity;




    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonManagedReference
    private OrderDetailEntity orderDetail;


    public OrderEntity(UserEntity userEntity, LocalDate orderDate) {
       this.userEntity = userEntity;
        this.orderDate = orderDate;
    }

    public OrderEntity() {

    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate order_date) {
        this.orderDate = order_date;
    }



    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderItem) {
        this.orderDetail = orderItem;
    }
    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }
}
