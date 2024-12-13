package com.example.demo.controller.user_login_logout_registration;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
@Tag(name = "User Login Controller", description = "API quản lý đăng nhập người dùng")
public class UserLoginController {

    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @Operation(summary = "Hiển thị form đăng nhập", description = "API này trả về trang form đăng nhập người dùng.")
    @GetMapping("/login")
    public String showLoginForm(){
        return "/login"; 
    }

    @Operation(summary = "Xử lý đăng nhập", description = "API này xử lý thông tin đăng nhập người dùng và điều hướng tới trang tương ứng.")
    @PostMapping("/login")
    public String Login(@ModelAttribute("userdto") UserDto userDto, Model model){
        if(userService.checkUserbyEmail(userDto.getEmail())==false){
            return "redirect:/login?emailwrong";
        }
        
        User user = userService.getUserbyEmail(userDto.getEmail());
        
        if(user.getRole().equals("ADMIN")){
            return "redirect:/admin_home";
        }

        if(userService.checkPasswordUser(userDto.getEmail(), userDto.getPassword())){
            return "redirect:/home?success"; 
        }

        return "redirect:/login?passwordwrong"; 
    }
}
