package com.example.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Định nghĩa bean OpenAPI để cấu hình Swagger
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tài liệu API Quản lý Người dùng")  // Tiêu đề API
                        .version("1.0")  // Phiên bản API
                        .description("Tài liệu API dùng để quản lý người dùng"));  // Mô tả API
    }
}
