package com.web.proyect1.shoppingcart.repositories;

import com.web.proyect1.shoppingcart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
