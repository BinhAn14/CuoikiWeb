package com.example.demo.controller.product;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "API quản lý sản phẩm")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Lấy danh sách tất cả sản phẩm", description = "API này trả về danh sách tất cả sản phẩm trong hệ thống.")
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "list"; 
    }

    @Operation(summary = "Xem chi tiết sản phẩm", description = "API này trả về chi tiết thông tin của một sản phẩm theo ID.")
    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "view";
    }
}
