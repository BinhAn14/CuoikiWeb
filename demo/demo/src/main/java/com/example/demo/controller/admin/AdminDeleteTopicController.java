package com.example.demo.controller.admin;

import com.example.demo.dto.UserDto;
import com.example.demo.service.CommentService;
import com.example.demo.service.ReactService;
import com.example.demo.service.TopicService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")

public class AdminDeleteTopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactService reactService;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }


    @GetMapping("admin_home/deletetopic/{id}")
    public String deleteTopic(@PathVariable String id) {
        reactService.delete(Integer.parseInt(id));
        commentService.delete(Integer.parseInt(id));
        topicService.delete(Integer.parseInt(id));
        return "redirect:/admin_home";
    }
}
