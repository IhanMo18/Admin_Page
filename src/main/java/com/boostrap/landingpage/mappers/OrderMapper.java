package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.OrderDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderMapper implements IMapper<OrderEntity, OrderDTO>{

    @Override
    public OrderDTO toDto(OrderEntity element) {
   return new OrderDTO(element.getUser().getId_user(),element.getOrder_id());
    }

    @Override
    public OrderEntity toEntity(OrderDTO element) {
        LocalDate localDate=LocalDate.now();
        UserEntity user = new UserEntity();
        user.setId_user(element.id_user());
        return new OrderEntity(user,localDate);
    }
}
