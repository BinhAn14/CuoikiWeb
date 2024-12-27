package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.reponsitory.ProductReponsitory; // Đảm bảo rằng tên của repository là đúng

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductReponsitory productRepository; 

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


}
