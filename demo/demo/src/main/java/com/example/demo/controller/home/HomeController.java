package com.example.demo.controller.home;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Product;
import com.example.demo.model.Topic;
import com.example.demo.reponsitory.ProductReponsitory;
import com.example.demo.reponsitory.TopicReponsitory;
import com.example.demo.service.CommentService;
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
public class HomeController {
    private CommentService commentService;

    @Autowired
    private TopicReponsitory topicReponsitory;

    @Autowired
    private ProductReponsitory productReponsitory;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto, Model model) {
        List<Topic> topics = topicReponsitory.findAll();
        model.addAttribute("topics", topics);

        List<Product> products = productReponsitory.findAll().stream().limit(3).toList();
        model.addAttribute("products", products);

        model.addAttribute("commentcount", commentService);

        return "/home";
    }
}
