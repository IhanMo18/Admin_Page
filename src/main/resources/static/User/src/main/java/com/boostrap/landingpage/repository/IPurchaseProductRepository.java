package com.boostrap.landingpage.repository;

import com.boostrap.landingpage.entity.PurchasedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseProductRepository extends JpaRepository<PurchasedProductEntity,Integer> {
}
