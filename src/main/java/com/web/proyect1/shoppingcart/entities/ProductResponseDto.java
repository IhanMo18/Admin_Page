package com.web.proyect1.shoppingcart.entities;

public record ProductResponseDto(
        Integer id,
        String name,
        Double price,
        int quantity
) {
}
