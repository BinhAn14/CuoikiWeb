package com.example.demo.controller.admin;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.reponsitory.CommentReponsitory;
import com.example.demo.reponsitory.TopicReponsitory;
import com.example.demo.reponsitory.UserReponsitory;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import javax.transaction.Transactional;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class AdminUserController {

    @Autowired
    private TopicReponsitory topicReponsitory;

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Autowired
    private UserReponsitory userReponsitory;

    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @GetMapping("/table_user")
    public String showUserControl(@ModelAttribute("userdto") UserDto userDto, Model model){
        List<User> users = userReponsitory.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("commentcount", commentReponsitory);
        model.addAttribute("topiccount", topicReponsitory);
        return "AdminUser/table_user";    
    }

    @GetMapping("/user/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());  // Tạo đối tượng User mới
        return "AdminUser/add_user";  // Form thêm người dùng
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user) {
        // Kiểm tra logic thêm người dùng và lưu vào database
        userReponsitory.save(user);
        return "redirect:/table_user";  // Chuyển hướng về danh sách người dùng
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) { 
        userReponsitory.deleteById(id);
        return "redirect:/table_user";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userReponsitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "AdminUser/edit_user"; 
    }

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

