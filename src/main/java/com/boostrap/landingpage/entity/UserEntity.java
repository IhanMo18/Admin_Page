package com.boostrap.landingpage.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "User_Table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;
    private String username;
    private String password;
    private String email;
    private String role;
    private String country;




    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderEntity> orderEntityList;


    public UserEntity(String username, String password, String email,String role,String country) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.country = country;
    }

    public UserEntity() {}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId_user() {
        return id_user;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEnntityList) {
        this.orderEntityList = orderEnntityList;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
