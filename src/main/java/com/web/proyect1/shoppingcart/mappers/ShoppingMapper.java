package com.web.proyect1.shoppingcart.mappers;

import com.web.proyect1.shoppingcart.entities.*;
import com.web.proyect1.shoppingcart.repositories.ProductPurshasedRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class ShoppingMapper implements IMapper<Shopping, ShoppingDto, ShoppingResponseDto>{

    private ProductPurshasedMapper productPurshasedMapper;
    private ProductPurshasedRepo productPurshasedRepo;

    public ShoppingMapper(ProductPurshasedMapper productPurshasedMapper,ProductPurshasedRepo productPurshasedRepo) {
        this.productPurshasedMapper = productPurshasedMapper;
        this.productPurshasedRepo = productPurshasedRepo;
    }

    @Override
    public Shopping toEntity(ShoppingDto shoppingDto) {
        var user = new Usuario();
        user.setId(shoppingDto.user_id());

        var listPurchaseProductsDto = shoppingDto.productPurchasedDtoList();
        var listPurchaseProductsEntity = listPurchaseProductsDto.stream()
                .map(productPurshasedMapper::toEntity)
                .collect(Collectors.toList());


        var shopping = new Shopping();

        shopping.setUser_id(user);
        shopping.setProducts(listPurchaseProductsEntity);
        shopping.setDate(LocalDate.now());

        return shopping;
    }

    @Override
    public ShoppingResponseDto toResponseDto(Shopping shopping) {

        return new ShoppingResponseDto(shopping.getDate(), shopping.getTotal(),shopping.getProducts(),shopping.getId());
    }


}
