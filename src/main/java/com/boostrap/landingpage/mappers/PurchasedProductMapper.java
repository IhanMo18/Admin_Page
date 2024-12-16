package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.PurchasedProductEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchasedProductMapper implements IMapper<PurchasedProductEntity, PurchaseProductDTO>{


    @Override
    public PurchaseProductDTO toDto(PurchasedProductEntity element) {
        return new PurchaseProductDTO(element.getProductQuantity(),element.getOrderEntity().getOrder_id(),element.getProductEntity().getId_product());
    }

    @Override
    public PurchasedProductEntity toEntity(PurchaseProductDTO element) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrder_id(element.id_Order());

        return new PurchasedProductEntity(element.cantidad(), orderEntity);
    }

}
