package com.web.proyect1.shoppingcart.mappers;

import com.web.proyect1.shoppingcart.entities.Usuario;
import com.web.proyect1.shoppingcart.entities.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper{

    public Usuario toEntity(UserDto userDto) {
        return new Usuario(userDto.username(), userDto.email(),userDto.password());
    }


    public UserDto toDto(Usuario user) {
        return new UserDto(user.getId(),user.getUsername(), user.getEmail(), user.getPassword());
    }
}
