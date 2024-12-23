package com.web.proyect1.shoppingcart.services;

import com.web.proyect1.shoppingcart.entities.*;
import com.web.proyect1.shoppingcart.mappers.UserMapper;
import com.web.proyect1.shoppingcart.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    public UserDto save(UserDto userDto) {
        var userEntity = userMapper.toEntity(userDto);
        userRepo.save(userEntity);
        return new UserDto(userDto.id(),userDto.username(),userDto.email(), userDto.password());
    }


    public void delete(Integer id) {
        userRepo.deleteById(id);
    }

    public UserDto findById(Integer id) {
        var user = userRepo.findById(id).orElse(null);

        return user != null ? new UserDto(user.getId(),user.getUsername(), user.getEmail(), user.getPassword()) : null;
    }


    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Shopping> findAllOrdersByUserId(Integer id) {
        return userRepo.findById(id).orElse(null).getShoppingList();
    }
}
