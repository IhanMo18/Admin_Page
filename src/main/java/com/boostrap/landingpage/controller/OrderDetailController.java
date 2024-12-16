package com.boostrap.landingpage.controller;

import com.boostrap.landingpage.dto.OrderDetailDTO;
import com.boostrap.landingpage.services.OrderDetailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class OrderDetailController {

    public OrderDetailController(OrderDetailServiceImpl orderDetailServiceImpl) {
        this.orderDetailServiceImpl = orderDetailServiceImpl;
    }

    OrderDetailServiceImpl orderDetailServiceImpl;

    @PostMapping("order/detail/save")
    public ResponseEntity<?> save(@RequestBody OrderDetailDTO orderDetailDTO){

        return new  ResponseEntity<>(orderDetailServiceImpl.save(orderDetailDTO),HttpStatus.OK);
    }

    @GetMapping("order/details")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(orderDetailServiceImpl.getAll(),HttpStatus.OK);
    }
}
