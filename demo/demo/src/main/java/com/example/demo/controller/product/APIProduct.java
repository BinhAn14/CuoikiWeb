package com.example.demo.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.reponsitory.ProductReponsitory;
import com.example.demo.service.ProductService;



@RestController
@RequestMapping("/api/product")
public class APIProduct {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductReponsitory productReponsitory;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }



}
