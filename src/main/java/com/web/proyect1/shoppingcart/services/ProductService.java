package com.web.proyect1.shoppingcart.services;

import com.web.proyect1.shoppingcart.entities.*;
import com.web.proyect1.shoppingcart.mappers.ProductMapper;
import com.web.proyect1.shoppingcart.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IService<ProductDtoAdmin, ProductResponseDto> {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public ProductService(ProductMapper productMapper, ProductRepo productRepo) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    @Override
    public ProductResponseDto save(ProductDtoAdmin productDtoAdmin) {
        var product=productRepo.save(productMapper.toEntity(productDtoAdmin));
        return new ProductResponseDto(product.getId(),productDtoAdmin.name(), productDtoAdmin.price(), productDtoAdmin.quantity());
    }

    @Override
    public void delete(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public ProductResponseDto findById(Integer id) {
        var product = productRepo.findById(id).get();

        return new ProductResponseDto(product.getId(),product.getName(), product.getPrice(), product.getQuantity());
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepo.findAll()
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public void setQuantityById(Integer productId, int quantity) {
        var product = productRepo.findById(productId).get();
        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);
    }

}
