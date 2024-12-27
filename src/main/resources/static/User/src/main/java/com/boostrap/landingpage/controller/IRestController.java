package com.boostrap.landingpage.controller;

import org.springframework.http.ResponseEntity;

public interface IRestController<Z> {

    public ResponseEntity<?> save(Z element);
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getById(Integer id);
    public void deleteById(Integer id);
    public void deleteAll();

}