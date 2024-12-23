package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.PurchaseProductDTO;
import com.boostrap.landingpage.entity.ProductEntity;
import com.boostrap.landingpage.entity.PurchasedProductEntity;
import com.boostrap.landingpage.mappers.PurchasedProductMapper;
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
        var product = iProductRepository.findById(element.id_product()).get();

        if (product.getInExist()){
            subTotal(product,purchasedProduct);
            setRealStock(purchasedProduct,product);
            return element;
        }
       return null;
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
        //Controlo que el precio sea lo que tenga

        if (product.getStock() < purchasedProduct.getProductQuantity()){
            Double subTotal =  product.getPrice() * product.getStock();
            purchasedProduct.setSubTotal(subTotal);
            iPurchaseProductRepository.save(purchasedProduct);
        }
        Double subTotal = product.getPrice() * purchasedProduct.getProductQuantity();
        purchasedProduct.setSubTotal(subTotal);
        iPurchaseProductRepository.save(purchasedProduct);
    }

    private void setRealStock(PurchasedProductEntity purchasedProduct,ProductEntity product){

        //Aseguro que el maximo sea el stock que existe

        if (product.getStock() < purchasedProduct.getProductQuantity()){
            purchasedProduct.setProductQuantity(product.getStock());
            product.setStock(0);
            product.setInExist(false);
            iPurchaseProductRepository.save(purchasedProduct);
        }else{
            Integer realStock = product.getStock() - purchasedProduct.getProductQuantity();
            product.setStock(realStock);
            iPurchaseProductRepository.save(purchasedProduct);
        }

    }


}

