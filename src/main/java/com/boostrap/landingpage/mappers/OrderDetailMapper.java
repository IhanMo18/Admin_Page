package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.OrderDetailDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.OrderDetailEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailMapper implements IMapper<OrderDetailEntity, OrderDetailDTO>{

    @Override
    public OrderDetailDTO toDto(OrderDetailEntity element) {
   return new OrderDetailDTO(element.getOrder().getId_order());
    }

    @Override
    public OrderDetailEntity toEntity(OrderDetailDTO element) {
        OrderEntity order = new OrderEntity();
        order.setId_order(element.id_order());
        return new OrderDetailEntity(order);
    }
}
