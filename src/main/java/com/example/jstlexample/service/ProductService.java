package com.example.jstlexample.service;

import com.example.jstlexample.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(String id);
    void save(Product product);
    void delete(String id);

    //List<Product> findByPrice(int  price);
}
