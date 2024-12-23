package com.web.proyect1.shoppingcart.services;

import com.web.proyect1.shoppingcart.entities.Product;
import com.web.proyect1.shoppingcart.entities.ProductResponseDto;
import com.web.proyect1.shoppingcart.entities.Shopping;
import com.web.proyect1.shoppingcart.entities.ShoppingResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService <T,Y>{
    Y save(T t);
    void delete(Integer id);
    Y findById(Integer id);
    List<Y> findAll();
}
