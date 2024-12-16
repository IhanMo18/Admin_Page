package com.boostrap.landingpage.mappers;

import com.boostrap.landingpage.dto.ProductDTO;
import com.boostrap.landingpage.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter @Setter
public class ProductMapper implements  IMapper<ProductEntity, ProductDTO>{

    @Override
    public ProductDTO toDto(ProductEntity element) {
      return new ProductDTO(element.getName(),element.getPrice(),element.getStock(),element.getId_product());
    }

    @Override
    public ProductEntity toEntity(ProductDTO element) {
        return new ProductEntity(element.name(),element.price(),element.stock());
    }
}
