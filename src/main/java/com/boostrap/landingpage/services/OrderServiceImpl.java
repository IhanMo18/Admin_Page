package com.boostrap.landingpage.services;


import com.boostrap.landingpage.dto.OrderDTO;
import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.PurchasedProductEntity;
import com.boostrap.landingpage.mappers.OrderMapper;
import com.boostrap.landingpage.repository.IOrderRepository;
import com.boostrap.landingpage.repository.IPurchaseProductRepository;
import com.boostrap.landingpage.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IService<OrderDTO> {



    IOrderRepository iOrderRepository;
    OrderMapper orderMapper;
    IPurchaseProductRepository iPurchaseProductRepository;

    public OrderServiceImpl(IOrderRepository iOrderRepository, OrderMapper orderMapper,IPurchaseProductRepository iPurchaseProductRepository) {
        this.iOrderRepository = iOrderRepository;
        this.orderMapper = orderMapper;
        this.iPurchaseProductRepository= iPurchaseProductRepository;
    }

    @Override
    public OrderDTO save(OrderDTO element) {
        iOrderRepository.save( orderMapper.toEntity(element));
        return element;
    }

    @Override
    public List<OrderDTO> getAll() {
        return iOrderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList()
                );
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
