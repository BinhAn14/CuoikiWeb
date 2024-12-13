package com.example.demo.controller.home;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Reacts;
import com.example.demo.service.CommentService;
import com.example.demo.service.ReactService;
import com.example.demo.service.TopicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Xóa bình luận", description = "API để quản lý và xóa các bình luận.")
public class DeleteCommentController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactService reactService;
    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }
    @GetMapping("/deletecomment/{id}")
    @Operation(summary = "Xóa bình luận", description = "Xóa một bình luận cùng với các phản ứng liên quan.")
    public String deleteComment(@PathVariable String id){
        reactService.delete(Integer.parseInt(id));
        commentService.delete(Integer.parseInt(id));
        topicService.delete(Integer.parseInt(id));
        return "redirect:/home";
    }
}
