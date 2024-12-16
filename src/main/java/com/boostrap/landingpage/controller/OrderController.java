package com.boostrap.landingpage.controller;

import com.boostrap.landingpage.dto.OrderDTO;
import com.boostrap.landingpage.services.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("order/save")
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO){
        System.out.println("eeL ID ES " + orderDTO.user_id());
        return new ResponseEntity<>(orderService.save(orderDTO),HttpStatus.CREATED);
    }

    @GetMapping("orders")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.FOUND);
    }




}
