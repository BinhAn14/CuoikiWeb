package com.example.demo.controller.user_login_logout_registration;

import com.example.demo.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
@Tag(name = "Logout Controller", description = "API quản lý đăng xuất người dùng")
public class LogoutController {

    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @Operation(summary = "Đăng xuất người dùng", description = "API này thực hiện đăng xuất người dùng và xóa thông tin khỏi session.")
    @GetMapping("/logout")
    public String Logout(@ModelAttribute("userdto") UserDto userDto, WebRequest request, SessionStatus status){
        status.setComplete();
        request.removeAttribute("userdto", WebRequest.SCOPE_SESSION);
        return "redirect:/login"; 
    }
}
