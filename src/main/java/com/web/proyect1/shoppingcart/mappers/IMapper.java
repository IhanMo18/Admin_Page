package com.web.proyect1.shoppingcart.mappers;

import org.springframework.stereotype.Service;

@Service
public interface IMapper <T,Y,Z>{
    T toEntity(Y y);
    Z toResponseDto(T t);
}
