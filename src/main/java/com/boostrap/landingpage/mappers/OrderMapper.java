package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.OrderDTO;
import com.boostrap.landingpage.entity.OrderDetailEntity;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderMapper implements  IMapper<OrderEntity, OrderDTO>{

    @Override
    public OrderDTO toDto(OrderEntity element) {
        return new OrderDTO(element.getUserEntity().getId_user());


    }

    @Override
    public OrderEntity toEntity (OrderDTO element) {
        System.out.println("entre");
        LocalDate localDate = LocalDate.now();
        System.out.println("el id en el mapper es " +element.user_id());
        UserEntity user = new UserEntity();
        user.setId_user(element.user_id());
        return new OrderEntity(user,localDate);
    }
}
