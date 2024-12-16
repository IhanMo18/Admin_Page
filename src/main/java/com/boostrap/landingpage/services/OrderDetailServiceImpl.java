package com.boostrap.landingpage.services;


import com.boostrap.landingpage.dto.OrderDetailDTO;
import com.boostrap.landingpage.mappers.OrderDetailMapper;
import com.boostrap.landingpage.repository.IOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements IService<OrderDetailDTO> {



    IOrderDetailRepository iOrderDetailRepository;
    OrderDetailMapper orderDetailMapper;

    public OrderDetailServiceImpl(IOrderDetailRepository iOrderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.iOrderDetailRepository = iOrderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public OrderDetailDTO save(OrderDetailDTO element) {
        var order=orderDetailMapper.toEntity(element);
        order.setSubTotal(123);
        iOrderDetailRepository.save(order);
        return element;
    }

    @Override
    public List<OrderDetailDTO> getAll() {
    return iOrderDetailRepository.findAll()
            .stream()
            .map(orderDetailMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO getById(Integer id) {
      return orderDetailMapper.toDto(iOrderDetailRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        iOrderDetailRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        iOrderDetailRepository.deleteAll();
    }

}
