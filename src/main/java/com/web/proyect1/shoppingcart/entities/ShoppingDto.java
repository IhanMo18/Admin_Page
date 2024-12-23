package com.web.proyect1.shoppingcart.entities;

import java.util.List;

public record ShoppingDto(
    Integer user_id,
    List<ProductPurshasedDto> productPurchasedDtoList
) {
}
