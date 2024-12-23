package com.web.proyect1.shoppingcart.mappers;

import com.web.proyect1.shoppingcart.entities.*;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper implements IMapper<Product,ProductDtoAdmin,ProductResponseDto> {

    @Override
    public Product toEntity(ProductDtoAdmin productDtoAdmin) {
        return new Product(productDtoAdmin.name(),
                productDtoAdmin.price(),
                productDtoAdmin.quantity());
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(product.getId(),product.getName(),product.getPrice(), product.getQuantity());
    }
}
