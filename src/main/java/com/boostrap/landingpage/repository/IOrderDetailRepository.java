package com.boostrap.landingpage.repository;

import com.boostrap.landingpage.entity.OrderDetailEntity;
import com.boostrap.landingpage.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity,Integer>{

}
