package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.PurchasedProductEntity;
import com.boostrap.landingpage.mappers.PurchasedProductMapper;
import com.boostrap.landingpage.repository.IOrderDetailRepository;
import com.boostrap.landingpage.repository.IProductRepository;
import com.boostrap.landingpage.repository.IPurchaseProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseProductServiceImpl implements IService<PurchaseProductDTO>{


    IProductRepository productRepository;
    IPurchaseProductRepository purchaseProductRepository;
    PurchasedProductMapper purchasedProductMapper;

    public PurchaseProductServiceImpl(IPurchaseProductRepository purchaseProductRepository, PurchasedProductMapper purchasedProductMapper,IProductRepository productRepository) {
        this.purchaseProductRepository = purchaseProductRepository;
        this.purchasedProductMapper = purchasedProductMapper;
        this.productRepository=productRepository;
    }



    @Override
    public PurchaseProductDTO save(PurchaseProductDTO element) {
       var purchedProduct = purchasedProductMapper.toEntity(element);
       purchedProduct.setProductEntity(productRepository.findById(element.id_Product()).get());
        purchaseProductRepository.save(purchedProduct);
        return element;
    }

    @Override
    public List<PurchaseProductDTO> getAll() {
        List<PurchaseProductDTO> purchaseProductDTOS=new ArrayList<>();
      for (PurchasedProductEntity product : purchaseProductRepository.findAll()){
         purchaseProductDTOS.add(purchasedProductMapper.toDto(product));
      }
      return purchaseProductDTOS;
    }

    @Override
    public PurchaseProductDTO getById(Integer id) {
     return  purchasedProductMapper.toDto(purchaseProductRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        purchaseProductRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        purchaseProductRepository.deleteAll();
    }
}
