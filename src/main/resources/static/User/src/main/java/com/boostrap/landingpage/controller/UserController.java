package com.boostrap.landingpage.controller;

import com.boostrap.landingpage.dto.UserDTO;
import com.boostrap.landingpage.services.UserServiceImpl;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<?> getAll(){
      return new ResponseEntity<>( userService.getAll(), HttpStatus.OK);
    }
    @PostMapping("user/save")
    public ResponseEntity<?>save(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.save(userDTO),HttpStatus.OK);
    }
    @GetMapping("user/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
       return new ResponseEntity<>( userService.getById(id),HttpStatus.FOUND);
    }
    @DeleteMapping ("user/delete/{id}")
    public void deleteById(@PathVariable Integer id){
         userService.deleteById(id);
    }

    @DeleteMapping ("user/delete/all")
    public void deleteAll(){
        userService.deleteAll();
    }






}
