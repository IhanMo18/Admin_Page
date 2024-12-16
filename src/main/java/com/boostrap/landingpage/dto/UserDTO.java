package com.boostrap.landingpage.dto;

import com.boostrap.landingpage.entity.OrderEntity;

import java.util.List;

public record UserDTO(

         String username,
         String password,
         String email,
         String role,
         String country,
         Integer id_user,
         List<OrderEntity> orders

) {
}
