package com.boostrap.landingpage.controller;

import com.boostrap.landingpage.dto.ProductDTO;
import com.boostrap.landingpage.services.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class ProductController implements IRestController<ProductDTO>{


    private final ProductServiceImpl service;;

    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }


    @GetMapping("products")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.FOUND);
    }

    @PostMapping("product/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(service.save(productDTO),HttpStatus.CREATED);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("product/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }

    @DeleteMapping("product/delete/all")
    public void deleteAll(){
        service.deleteAll();
    }


}
