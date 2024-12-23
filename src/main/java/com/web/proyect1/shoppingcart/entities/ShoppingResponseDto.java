package com.web.proyect1.shoppingcart.entities;

import java.time.LocalDate;
import java.util.List;

public record ShoppingResponseDto(
        LocalDate date,
        Double total,
        List<ProductPurshased> products,
        Integer order_id
) {
}
