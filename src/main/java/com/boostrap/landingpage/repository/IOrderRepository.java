package com.boostrap.landingpage.repository;

import com.boostrap.landingpage.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity,Integer>{

}
