package com.web.proyect1.shoppingcart.controllers;

import com.web.proyect1.shoppingcart.entities.ProductPurshased;
import com.web.proyect1.shoppingcart.entities.ProductPurshasedDto;
import com.web.proyect1.shoppingcart.services.ProductPurshasedService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductPurshasedController {

    private final ProductPurshasedService productPurshasedService;

    public ProductPurshasedController(ProductPurshasedService productPurshasedService) {
        this.productPurshasedService = productPurshasedService;
    }


    @GetMapping("/api/get/productsPurshased")
    public List<ProductPurshased> getAll(){
        return productPurshasedService.findAll();
    }

    @PostMapping("/api/save/productPurshased")
    public ProductPurshased save(@RequestBody ProductPurshasedDto productPurshasedto) {
        return productPurshasedService.save(productPurshasedto);
    }

    @DeleteMapping("/api/delete/productPurshased/{id}")
    public void delete(@PathVariable Integer id) {
        productPurshasedService.delete(id);
    }

    @GetMapping("/api/findProductIdByPurshasedId/{id}")
    public Integer findPurshasedProduct(@PathVariable Integer id) {
        return productPurshasedService.findProductIdByPurchasedId(id);
    }

}
