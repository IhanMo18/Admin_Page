package com.boostrap.landingpage.repository;

import com.boostrap.landingpage.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository  extends JpaRepository<ProductEntity,Integer> {
}
