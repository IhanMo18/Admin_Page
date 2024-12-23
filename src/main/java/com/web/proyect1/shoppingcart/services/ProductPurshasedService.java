package com.web.proyect1.shoppingcart.services;

import com.web.proyect1.shoppingcart.entities.ProductPurshased;
import com.web.proyect1.shoppingcart.entities.ProductPurshasedDto;
import com.web.proyect1.shoppingcart.mappers.ProductPurshasedMapper;
import com.web.proyect1.shoppingcart.repositories.ProductPurshasedRepo;
import com.web.proyect1.shoppingcart.repositories.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductPurshasedService {

    private ProductPurshasedRepo productPurshasedRepo;
    private ProductPurshasedMapper productPurshasedMapper;
    private final ProductRepo productRepo;

    public ProductPurshasedService(ProductPurshasedRepo productPurshasedRepo, ProductPurshasedMapper productPurshasedMapper, ProductRepo productRepo) {
        this.productPurshasedRepo = productPurshasedRepo;
        this.productPurshasedMapper = productPurshasedMapper;
        this.productRepo = productRepo;
    }

    public List<ProductPurshased> findAll(){
        return productPurshasedRepo.findAll();
    }

    public ProductPurshased save(ProductPurshasedDto productPurshasedDto) {
        var productPurshased=productPurshasedMapper.toEntity(productPurshasedDto);
        return productPurshasedRepo.save(productPurshased);
    }
    public ResponseEntity<?> findById(int id) {
        try{
            return new ResponseEntity<>(productPurshasedRepo.findById(id), HttpStatus.FOUND);
        }catch (NoSuchElementException e){
            return null;
        }

    }
    public ProductPurshased update(ProductPurshasedDto productPurshasedDto) {

        var product = productRepo.findById(productPurshasedDto.id_product());
        product.get().setQuantity(product.get().getQuantity()-productPurshasedDto.quantity());
        productRepo.save(product.get());

        return productPurshasedMapper.toEntity(productPurshasedDto);

    }

    public void delete(Integer id) {
        productPurshasedRepo.deleteById(id);
    }
    public Integer findProductIdByPurchasedId(Integer id) {
        return productPurshasedRepo.findById(id).get().getProduct().getId();
    }

}
