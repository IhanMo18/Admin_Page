package com.boostrap.landingpage.controller;

import com.boostrap.landingpage.dto.OrderDTO;
import com.boostrap.landingpage.services.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    OrderServiceImpl orderServiceImpl;

    @PostMapping("order/save")
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO){

        return new  ResponseEntity<>(orderServiceImpl.save(orderDTO),HttpStatus.OK);
    }

    @GetMapping("order/details")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(orderServiceImpl.getAll(),HttpStatus.OK);
    }

    @DeleteMapping("order/delete/all")
    public void deleteALL(){
        orderServiceImpl.deleteAll();
    }

    @DeleteMapping("order/delete/{id}")
    public void deleteALL(@PathVariable Integer id){
        orderServiceImpl.deleteById(id);
    }
}
