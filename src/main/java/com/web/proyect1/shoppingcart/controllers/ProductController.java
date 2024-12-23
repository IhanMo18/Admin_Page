package com.web.proyect1.shoppingcart.controllers;

import com.web.proyect1.shoppingcart.entities.Product;
import com.web.proyect1.shoppingcart.entities.ProductDtoAdmin;
import com.web.proyect1.shoppingcart.entities.ProductResponseDto;
import com.web.proyect1.shoppingcart.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/get/products")
    public List<ProductResponseDto> getAllUsers(){
        return productService.findAll();
    }

    @GetMapping("/api/find/product/{id}")
    public ProductResponseDto getProductById(@PathVariable Integer id){
        return productService.findById(id);
    }

    @PostMapping("/api/save/product")
    public ProductResponseDto saveProduct(@RequestBody ProductDtoAdmin productDtoAdmin){
        return productService.save(productDtoAdmin);
    }

    @DeleteMapping("/api/delete/product/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.delete(id);
    }


}
