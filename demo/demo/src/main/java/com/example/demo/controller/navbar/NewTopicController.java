package com.example.demo.controller.navbar;

import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Tags;
import com.example.demo.model.User;
import com.example.demo.reponsitory.UserReponsitory;
import com.example.demo.service.TagsService;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
@Tag(name = "New Topic Controller", description = "API quản lý tạo mới chủ đề")
public class NewTopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired
    private TagsService tagsService;

    @ModelAttribute("topic")
    public TopicDto topicDto() {
        return new TopicDto();
    }

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @Operation(summary = "Hiển thị form tạo mới chủ đề", description = "Hiển thị form cho phép người dùng tạo một chủ đề mới, bao gồm các tag")
    @GetMapping("/newtopic")
    public String showNewTopic(Model model) {
        List<Tags> tags = tagsService.getListTag();
        model.addAttribute("tags", tags);
        return "/newtopic";
    }

    @Operation(summary = "Tạo mới chủ đề", description = "Tạo một chủ đề mới với thông tin được nhập vào từ form")
    @PostMapping("/newtopic")
    public String newTopic(@ModelAttribute("userdto") UserDto userDto, 
                           @RequestBody @ModelAttribute("topic") TopicDto topicDto, 
                           Model model) {
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        topicService.save(topicDto, user);
        return "redirect:/home";
    }
}
