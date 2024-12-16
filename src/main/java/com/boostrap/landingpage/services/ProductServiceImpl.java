package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.ProductDTO;
import com.boostrap.landingpage.mappers.ProductMapper;
import com.boostrap.landingpage.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service

public class ProductServiceImpl implements IService<ProductDTO> {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductDTO save(ProductDTO element) {
        productRepository.save(productMapper.toEntity(element));
        return element;
    }


    @Override
    public List<ProductDTO> getAll() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList()
                );
    }

    @Override
    public ProductDTO getById(Integer id){
       try {
           return productMapper.toDto(productRepository.findById(id).get());
       }catch (NoSuchElementException exception){
           exception.printStackTrace();
       }
        return null;
    }


    @Override
    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }
    @Override
    public void deleteAll(){
        productRepository.deleteAll();
    }

//    public ProductDTO update (ProductDTO productDTO, Integer id){
//       ProductEntity productEntity = productRepository.findById(id).get();
//       if (!(productEntity.getName().toLowerCase().equals(productDTO.name()))) productEntity.setName(productDTO.name());
//       else if (productEntity.getPrice() != productDTO.price()){
//           productEntity.setPrice(productDTO.price());
//       }
//       return productDTO;


}
