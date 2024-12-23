package com.web.proyect1.shoppingcart.controllers;

import com.web.proyect1.shoppingcart.entities.ProductPurshased;
import com.web.proyect1.shoppingcart.entities.ShoppingDto;
import com.web.proyect1.shoppingcart.entities.ShoppingResponseDto;
import com.web.proyect1.shoppingcart.services.ShoppingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/api/get/tickets")
    public List<ShoppingResponseDto> getALlTickets(){
        return shoppingService.findAll();
    }

    @GetMapping("/api/find/ticket/{id}")
    public ShoppingResponseDto findTicketById(@PathVariable Integer id){
        return shoppingService.findById(id);
    }

    @PostMapping("/api/save/ticket")
    public ShoppingResponseDto saveTicket(@RequestBody ShoppingDto shoppingDto){
        return shoppingService.save(shoppingDto);
    }

    @DeleteMapping("/api/delete/ticket/{id}")
    public void deleteTicket(@PathVariable Integer id){
        shoppingService.delete(id);
    }
    @GetMapping("/api/get/productsByTicketId/{id}")
    public List<ProductPurshased> getALlTickets(@PathVariable Integer id){
        return shoppingService.findAllByShoppingId(id);
    }

}
