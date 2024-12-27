package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.UserDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.UserEntity;
import com.boostrap.landingpage.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements IMapper<UserEntity, UserDTO>{
    @Override
    public UserDTO toDto(UserEntity element) {
       return new UserDTO(element.getUsername(),element.getPassword(),element.getEmail(),element.getRole(),element.getCountry(),element.getId_user());
    }

    @Override
    public UserEntity toEntity(UserDTO element) {
        return new UserEntity(element.username(), element.password(), element.email(),element.role(),element.country());

    }
}
