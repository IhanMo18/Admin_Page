package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.OrderDTO;
import com.boostrap.landingpage.dto.UserDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.UserEntity;
import com.boostrap.landingpage.mappers.OrderMapper;
import com.boostrap.landingpage.repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IService<OrderDTO>{

    public OrderServiceImpl(IOrderRepository iOrderRepository, OrderMapper orderMapper) {
        this.iOrderRepository = iOrderRepository;
        this.orderMapper = orderMapper;
    }

    IOrderRepository iOrderRepository;
    OrderMapper orderMapper;


    @Override
    public OrderDTO save(OrderDTO element) {
        System.out.println("El id een el service es " + element.user_id());
       var order =  orderMapper.toEntity(element);
      iOrderRepository.save(order);
        return element;
    }

    @Override
    public List<OrderDTO> getAll() {
      List<OrderDTO> list = new ArrayList<>();
      for (OrderEntity order : iOrderRepository.findAll()){
          list.add(orderMapper.toDto(order));
      }
      return list;
    }

    @Override
    public OrderDTO getById(Integer id) {
       return orderMapper.toDto(iOrderRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        iOrderRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        iOrderRepository.deleteAll();
    }

}
