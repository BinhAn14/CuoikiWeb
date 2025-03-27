package com.example.demo.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Product;
import com.example.demo.reponsitory.ProductReponsitory;
import com.example.demo.service.ProductService;

import org.springframework.web.bind.annotation.RequestBody;



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
      @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id) {
        Product product = productReponsitory.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID: " + id));
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productReponsitory.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Product productUpdate = productReponsitory.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID: " + id));
        productUpdate.setProductName(product.getProductName());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setImageUrl(product.getImageUrl());
        productUpdate.setStock(product.getStock());
        Product updatedProduct = productReponsitory.save(productUpdate);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productReponsitory.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
