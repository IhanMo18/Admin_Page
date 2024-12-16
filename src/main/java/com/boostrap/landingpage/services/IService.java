package com.boostrap.landingpage.services;

import com.boostrap.landingpage.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IService<Z>{


    public Z save (Z element);
    public List<Z> getAll();
    Z getById(Integer id);
    void deleteById(Integer id);
    void deleteAll();
}
