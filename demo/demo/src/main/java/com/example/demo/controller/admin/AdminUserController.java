package com.example.demo.controller.admin;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.reponsitory.CommentReponsitory;
import com.example.demo.reponsitory.TopicReponsitory;
import com.example.demo.reponsitory.UserReponsitory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
@Tag(name = "Quản lý người dùng", description = "API dành cho quản trị viên quản lý người dùng.")
public class AdminUserController {

    @Autowired
    private TopicReponsitory topicReponsitory;

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Autowired
    private UserReponsitory userReponsitory;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @Operation(summary = "Hiển thị danh sách người dùng", description = "Lấy danh sách tất cả người dùng hiện có.")
    @GetMapping("/table_user")
    public String showUserControl(@ModelAttribute("userdto") UserDto userDto, Model model) {
        List<User> users = userReponsitory.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("commentcount", commentReponsitory);
        model.addAttribute("topiccount", topicReponsitory);
        return "AdminUser/table_user";
    }

    @Operation(summary = "Hiển thị form thêm người dùng", description = "Hiển thị trang để thêm người dùng mới.")
    @GetMapping("/user/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "AdminUser/add_user";
    }

    @Operation(summary = "Thêm người dùng", description = "Thêm một người dùng mới vào hệ thống.")
    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user) {
        userReponsitory.save(user);
        return "redirect:/table_user";
    }

    @Operation(summary = "Xóa người dùng", description = "Xóa người dùng khỏi hệ thống dựa trên ID.")
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userReponsitory.deleteById(id);
        return "redirect:/table_user";
    }

    @Operation(summary = "Hiển thị form chỉnh sửa người dùng", description = "Hiển thị trang chỉnh sửa thông tin người dùng dựa trên ID.")
    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userReponsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "AdminUser/edit_user";
    }

    @Operation(summary = "Cập nhật người dùng", description = "Cập nhật thông tin người dùng dựa trên ID.")
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("user") User user) {
        User existingUser = userReponsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        existingUser.setUserDisplayName(user.getUserDisplayName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        userReponsitory.save(existingUser);
        return "redirect:/table_user";
    }
}
