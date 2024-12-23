package com.web.proyect1.shoppingcart.controllers;


import com.web.proyect1.shoppingcart.entities.Shopping;
import com.web.proyect1.shoppingcart.entities.ShoppingDto;
import com.web.proyect1.shoppingcart.entities.UserDto;
import com.web.proyect1.shoppingcart.entities.UserResponseDto;
import com.web.proyect1.shoppingcart.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/get/users")
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/api/find/user/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping("/api/save/user")
    public UserDto saveUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @DeleteMapping("/api/delete/user/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.delete(id);
    }

    @GetMapping("/api/findOrdersById/{id}")
    public List<Shopping> findAllOrdersById(@PathVariable Integer id){
        return userService.findAllOrdersByUserId(id);
    }



}
