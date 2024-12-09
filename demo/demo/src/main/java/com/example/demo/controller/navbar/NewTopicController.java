package com.example.demo.controller.navbar;

import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Tags;
import com.example.demo.model.User;
import com.example.demo.reponsitory.UserReponsitory;
import com.example.demo.service.TagsService;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
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
    public TopicDto topicDto(){
        return new TopicDto();
    }

    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @GetMapping("/newtopic")
    public String showNewTopic(Model model){
        List<Tags> tags = tagsService.getListTag();
        model.addAttribute("tags",tags);
        return "/newtopic";
    }
    @PostMapping("/newtopic")
    public String newTopic(@ModelAttribute("userdto") UserDto userDto,@ModelAttribute("topic") TopicDto topicDto,Model model){
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if(user== null){
            return "redirect:/login";
        }
        topicService.save(topicDto,user);
        return "redirect:/home";
    }
}
