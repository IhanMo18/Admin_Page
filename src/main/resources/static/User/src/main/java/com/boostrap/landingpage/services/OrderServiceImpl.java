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

import java.util.ArrayList;
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
//        return iOrderRepository.findAll()
//                .stream()
//                .map(orderMapper::toDto)
//                .collect(Collectors.toList()
//                );
        List<OrderDTO> orderDtos = new ArrayList<>();
        for (OrderEntity order : iOrderRepository.findAll()){
            total(order.getOrder_id());
            orderDtos.add(orderMapper.toDto(order));
        }
        return orderDtos;
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

    private void total(Integer id){
        var orderEntity = iOrderRepository.findById(id);
        Double total = 0.0;

        if (orderEntity.get().getPurchasedProductEntityList() != null){
            for (PurchasedProductEntity product : orderEntity.get().getPurchasedProductEntityList()){
                total += product.getSubTotal();
            }
            orderEntity.get().setTotal(total);
        }

    }


}
