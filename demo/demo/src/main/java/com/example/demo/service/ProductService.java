package com.example.demo.service;

import com.example.demo.reponsitory.ProductReponsitory; // Đảm bảo rằng tên của repository là đúng
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductReponsitory productRepository; // Sửa lỗi chính tả từ "ProductReponsitory" thành "ProductRepository"

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


}
