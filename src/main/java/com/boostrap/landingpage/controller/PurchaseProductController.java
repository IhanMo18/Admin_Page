package com.boostrap.landingpage.controller;

import com.boostrap.landingpage.dto.PurchaseProductDTO;;
import com.boostrap.landingpage.services.PurchaseProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class PurchaseProductController implements IRestController<PurchaseProductDTO>{


    PurchaseProductServiceImpl purchaseProductService;

    public PurchaseProductController(PurchaseProductServiceImpl purchaseProductService) {
        this.purchaseProductService = purchaseProductService;
    }

    @Override
    @PostMapping("purchase/product/save")
    public ResponseEntity<?> save(@RequestBody PurchaseProductDTO element) {
     return new ResponseEntity<>(purchaseProductService.save(element),HttpStatus.OK);
    }

    @Override
    @GetMapping("purchase/products")
    public ResponseEntity<?> getAll() {
       return new ResponseEntity<>(purchaseProductService.getAll(),HttpStatus.FOUND);
    }

    @Override
    @GetMapping("purchase/product/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
       return new ResponseEntity<>(purchaseProductService.getById(id),HttpStatus.FOUND);
    }

    @Override
    @DeleteMapping("purchase/product/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        purchaseProductService.deleteById(id);
    }

    @Override
    @DeleteMapping("purchase/product/delete")
    public void deleteAll() {
        purchaseProductService.deleteAll();
    }
}
