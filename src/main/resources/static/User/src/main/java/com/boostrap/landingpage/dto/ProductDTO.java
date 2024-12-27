package com.boostrap.landingpage.dto;

public record ProductDTO(
        String name,
        Double price,
        Integer stock,
        Integer id_product,
        Boolean inExist
)
{

}
