package com.web.proyect1.shoppingcart.services;

import com.web.proyect1.shoppingcart.entities.*;
import com.web.proyect1.shoppingcart.mappers.ShoppingMapper;
import com.web.proyect1.shoppingcart.repositories.ProductPurshasedRepo;
import com.web.proyect1.shoppingcart.repositories.ProductRepo;
import com.web.proyect1.shoppingcart.repositories.ShoppingRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingService implements IService<ShoppingDto, ShoppingResponseDto>{

    private final ShoppingRepo shoppingRepo;
    private final ShoppingMapper shoppingMapper;
    private final ProductPurshasedRepo purshasedRepo;
    private final ProductRepo productRepo;

    public ShoppingService(ShoppingRepo shoppingRepo, ShoppingMapper shoppingMapper, ProductPurshasedRepo purshasedRepo, ProductRepo productRepo) {
        this.shoppingRepo = shoppingRepo;
        this.shoppingMapper = shoppingMapper;
        this.purshasedRepo = purshasedRepo;
        this.productRepo = productRepo;
    }

    @Override
    public ShoppingResponseDto save(ShoppingDto shoppingDto) {
        var shoppingEntity=shoppingRepo.save(shoppingMapper.toEntity(shoppingDto));
        var listProductsPurchased=shoppingEntity.getProducts();

        Double total = 0.0;

        for(ProductPurshased productPurshased:listProductsPurchased){
            productPurshased.setShopping(shoppingEntity);
            var product=productRepo.findById(productPurshased.getProduct().getId()).orElse(null);
            purshasedRepo.save(productPurshased);
            product.setQuantity(product.getQuantity()-productPurshased.getQuantity());
            productRepo.save(product);
            total += product.getPrice()*productPurshased.getQuantity();
        }
        shoppingEntity.setTotal(total);
        shoppingRepo.save(shoppingEntity);

        return new ShoppingResponseDto(shoppingEntity.getDate(), shoppingEntity.getTotal(),shoppingEntity.getProducts(),shoppingEntity.getId());
    }

    @Override
    public void delete(Integer id) {
        var shoppingEntity = shoppingRepo.findById(id).orElse(null);

        var listProductsPurchased=shoppingEntity.getProducts();

        for(ProductPurshased productPurshased:listProductsPurchased){
            var product=productRepo.findById(productPurshased.getProduct().getId()).orElse(null);
            product.setQuantity(product.getQuantity()+productPurshased.getQuantity());
        }
        shoppingRepo.delete(shoppingEntity);
    }

    @Override
    public ShoppingResponseDto findById(Integer id) {
        var shoppingEntity = shoppingRepo.findById(id).orElse(null);

        return shoppingEntity != null ? new ShoppingResponseDto(shoppingEntity.getDate(), shoppingEntity.getTotal(),shoppingEntity.getProducts(),shoppingEntity.getId()) : null;
    }

    @Override
    public List<ShoppingResponseDto> findAll() {
        return shoppingRepo.findAll()
                .stream()
                .map(shoppingMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    public List<ProductPurshased> findAllByShoppingId(Integer id) {
        return shoppingRepo.findById(id).orElse(null).getProducts();
    }
}
