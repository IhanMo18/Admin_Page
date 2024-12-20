package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.ProductEntity;
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

    IPurchaseProductRepository iPurchaseProductRepository;
    PurchasedProductMapper purchasedProductMapper;
    IProductRepository iProductRepository;


    public PurchaseProductServiceImpl(IPurchaseProductRepository iPurchaseProductRepository,
                                      PurchasedProductMapper purchasedProductMapper,IProductRepository iProductRepository) {

        this.iPurchaseProductRepository = iPurchaseProductRepository;
        this.purchasedProductMapper = purchasedProductMapper;
        this.iProductRepository = iProductRepository;
    }

    @Override
    public PurchaseProductDTO save(PurchaseProductDTO element) {
        var purchasedProduct = purchasedProductMapper.toEntity(element);
        subTotal(iProductRepository.findById(element.id_product()).get(),purchasedProduct);
        setRealStock(purchasedProduct,iProductRepository.findById(element.id_product()).get());
        return element;
    }

    @Override
    public List<PurchaseProductDTO> getAll() {
        List<PurchaseProductDTO> purchaseProductDTOS=new ArrayList<>();

        for (PurchasedProductEntity purchasedProduct : iPurchaseProductRepository.findAll()){
          purchaseProductDTOS.add(purchasedProductMapper.toDto(purchasedProduct));
        }

      return purchaseProductDTOS;
    }

    @Override
    public PurchaseProductDTO getById(Integer id) {
     return  purchasedProductMapper.toDto(iPurchaseProductRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        iPurchaseProductRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        iPurchaseProductRepository.deleteAll();
    }

    private void subTotal(ProductEntity product,PurchasedProductEntity purchasedProduct){
        Double subTotal = product.getPrice() * purchasedProduct.getProductQuantity();
        purchasedProduct.setSubTotal(subTotal);
        iPurchaseProductRepository.save(purchasedProduct);
    }

    private void setRealStock(PurchasedProductEntity purchasedProductEntity,ProductEntity product){
      Integer realStock = product.getStock() - purchasedProductEntity.getProductQuantity();
      product.setStock(realStock);
      iPurchaseProductRepository.save(purchasedProductEntity);
    }


}

