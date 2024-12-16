package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.PurchasedProductEntity;
import com.boostrap.landingpage.mappers.PurchasedProductMapper;
import com.boostrap.landingpage.repository.IOrderRepository;
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
    IOrderRepository iOrderRepository;


    public PurchaseProductServiceImpl(IPurchaseProductRepository purchaseProductRepository, PurchasedProductMapper purchasedProductMapper,IProductRepository productRepository,IOrderRepository iOrderRepository) {
        this.purchaseProductRepository = purchaseProductRepository;
        this.purchasedProductMapper = purchasedProductMapper;
        this.productRepository=productRepository;
        this.iOrderRepository = iOrderRepository;
    }



    @Override
    public PurchaseProductDTO save(PurchaseProductDTO element) {
        var purchedProduct = purchasedProductMapper.toEntity(element);
        total(element.id_Order());
        purchedProduct.setProductEntity (productRepository.findById(element.id_Product()).get());
        subTotal(element.id_Product(),purchedProduct);
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

    public void subTotal(Integer id,PurchasedProductEntity purchasedProduct){
        var product=productRepository.findById(id);
        Double subTotal = product.get().getPrice()*purchasedProduct.getProductQuantity();
        purchasedProduct.setSubTotal(subTotal);
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

