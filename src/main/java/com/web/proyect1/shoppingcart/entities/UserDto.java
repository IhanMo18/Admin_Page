package com.web.proyect1.shoppingcart.entities;

public record UserDto(
        Integer id,
        String username,
        String email,
        String password
) {
}
