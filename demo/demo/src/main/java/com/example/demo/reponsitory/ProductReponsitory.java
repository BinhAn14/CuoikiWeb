package com.example.demo.reponsitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Long> {
}
