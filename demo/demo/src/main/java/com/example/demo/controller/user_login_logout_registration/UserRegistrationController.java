package com.example.demo.controller.user_login_logout_registration;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Tag(name = "User Registration Controller", description = "API quản lý đăng ký người dùng mới")
public class UserRegistrationController {
    
    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @Operation(summary = "Hiển thị form đăng ký", description = "API này trả về trang form đăng ký người dùng mới.")
    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "/registration";
    }

    @Operation(summary = "Xử lý đăng ký người dùng", description = "API này xử lý thông tin đăng ký người dùng mới.")
    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("userdto") UserDto userDto) {
        // Kiểm tra email đã tồn tại chưa
        if(userService.checkUserbyEmail(userDto.getEmail())) {
            return "redirect:/registration?emailexist"; 
        }

        if(userDto.getPassword().equals(userDto.getCheckPass()) == false) {
            return "redirect:/registration?checkpass"; 
        }

        userService.save(userDto);
        return "redirect:/registration?success"; 
    }
}
