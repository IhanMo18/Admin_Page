package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.OrderDetailEntity;
import com.boostrap.landingpage.entity.ProductEntity;
import com.boostrap.landingpage.entity.PurchasedProductEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchasedProductMapper implements IMapper<PurchasedProductEntity, PurchaseProductDTO>{


    @Override
    public PurchaseProductDTO toDto(PurchasedProductEntity element) {
        return new PurchaseProductDTO(element.getProductQuantity(),element.getOrderDetailEntity().getOrderDetail_id(),element.getProductEntity().getId_product());
    }

    @Override
    public PurchasedProductEntity toEntity(PurchaseProductDTO element) {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setOrderDetail_id(element.id_Order_Detail());
        return new PurchasedProductEntity(element.cantidad(),orderDetailEntity);
    }

}
