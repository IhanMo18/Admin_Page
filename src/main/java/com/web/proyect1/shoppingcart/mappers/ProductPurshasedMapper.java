package com.web.proyect1.shoppingcart.mappers;

import com.web.proyect1.shoppingcart.entities.Product;
import com.web.proyect1.shoppingcart.entities.ProductPurshasedDto;
import com.web.proyect1.shoppingcart.entities.ProductPurshased;
import org.springframework.stereotype.Service;

@Service
public class ProductPurshasedMapper {

    public ProductPurshased toEntity(ProductPurshasedDto productPurchasedDto){
        var productPurchased = new ProductPurshased(productPurchasedDto.quantity());
        var product = new Product();
        product.setId(productPurchasedDto.id_product());
        productPurchased.setProduct(product);
        return productPurchased;
    }

    public ProductPurshasedDto toDto(ProductPurshased productPurchased){
        return  new ProductPurshasedDto(productPurchased.getProduct().getId(),productPurchased.getQuantity());
    }

}
