package com.web.proyect1.shoppingcart.repositories;

import com.web.proyect1.shoppingcart.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Usuario, Integer> {
}
