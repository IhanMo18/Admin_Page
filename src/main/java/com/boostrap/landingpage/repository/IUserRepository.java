package com.boostrap.landingpage.repository;

import com.boostrap.landingpage.entity.OrderDetailEntity;
import com.boostrap.landingpage.entity.OrderEntity;
import com.boostrap.landingpage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Integer> {

}
