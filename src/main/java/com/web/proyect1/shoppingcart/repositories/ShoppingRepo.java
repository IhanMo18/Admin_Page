package com.web.proyect1.shoppingcart.repositories;

import com.web.proyect1.shoppingcart.entities.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepo extends JpaRepository<Shopping, Integer> {
}
