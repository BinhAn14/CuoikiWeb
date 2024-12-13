package com.example.demo.controller.admin;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Topic;
import com.example.demo.reponsitory.TopicReponsitory;
import com.example.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
@Tag(name = "Quản trị trang chủ", description = "API dành cho quản trị viên để xem thông tin trang chủ")
public class AdminHomeController {
    private CommentService commentService;
    @Autowired
    private TopicReponsitory topicReponsitory;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @Operation(summary = "Hiển thị trang chủ quản trị", description = "Hiển thị danh sách các chủ đề và số lượng bình luận liên quan.")
    @GetMapping("/admin_home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto, Model model) {
        List<Topic> topics = topicReponsitory.findAll();
        model.addAttribute("topics", topics);
        model.addAttribute("commentcount", commentService);

        return "/admin_home";
    }
}
