package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.UserDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.UserEntity;
import com.boostrap.landingpage.mappers.UserMapper;
import com.boostrap.landingpage.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IService<UserDTO> {

    IUserRepository userRepository;
    UserMapper userMapper;

    public UserServiceImpl(IUserRepository iUserRepository, UserMapper userMapper) {
        this.userRepository = iUserRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDTO save(UserDTO userDTO) {
        userRepository.save(userMapper.toEntity(userDTO));
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> listUserDto = new ArrayList<>();
        for (UserEntity user : userRepository.findAll()){
            LocalDate localDate = LocalDate.now();
            listUserDto.add(userMapper.toDto(user));
        }
        return  listUserDto;
    }

    @Override
    public UserDTO getById(Integer id) {
      return userMapper.toDto(userRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    public List<OrderEntity> getAllEntitiesByuserId(Integer id){
        return userRepository.findById(id).get().getOrderEntities();
    }
}
