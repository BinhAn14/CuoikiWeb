package com.example.demo.controller.admin;

import com.example.demo.model.Product;
import com.example.demo.reponsitory.ProductReponsitory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@Tag(name = "Quản lý sản phẩm", description = "API dành cho quản trị viên quản lý sản phẩm.")
public class AdminProductController {

    @Autowired
    private ProductReponsitory productRepository;

    @Operation(summary = "Hiển thị danh sách sản phẩm", description = "Lấy danh sách tất cả các sản phẩm trong hệ thống.")
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "AdminProduct/product_list"; // Tên file Thymeleaf để hiển thị danh sách sản phẩm
    }

    @Operation(summary = "Hiển thị form thêm sản phẩm", description = "Hiển thị trang để thêm sản phẩm mới.")
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "AdminProduct/add_product";
    }

    @Operation(summary = "Xử lý thêm sản phẩm", description = "Thêm một sản phẩm mới vào hệ thống.")
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @Operation(summary = "Hiển thị form chỉnh sửa sản phẩm", description = "Hiển thị trang chỉnh sửa thông tin sản phẩm theo ID.")
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "AdminProduct/edit_product";
    }

    @Operation(summary = "Xử lý cập nhật sản phẩm", description = "Cập nhật thông tin sản phẩm theo ID.")
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImageUrl(product.getImageUrl());
        productRepository.save(existingProduct);
        return "redirect:/admin/products";
    }

    @Operation(summary = "Xóa sản phẩm", description = "Xóa một sản phẩm theo ID.")
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }
}
