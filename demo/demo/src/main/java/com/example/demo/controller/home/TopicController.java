package com.example.demo.controller.home;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.reponsitory.CommentReponsitory;
import com.example.demo.reponsitory.UserReponsitory;
import com.example.demo.service.CommentService;
import com.example.demo.service.ReactService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
@Tag(name = "Topic Controller", description = "API quản lý các chủ đề và bình luận")
public class TopicController {
    private TopicService topicService;
    private UserService userService;
    private CommentService commentService;
    private ReactService reactService;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Autowired
    private UserReponsitory userReponsitory;

    @ModelAttribute("newcomment")
    public CommentDto commentDto() {
        return new CommentDto();
    }

    @Operation(summary = "Hiển thị chủ đề", description = "Hiển thị thông tin của một chủ đề cùng các bình luận liên quan")
    @GetMapping("/topic/{id}")
    public String showTopicById(Model model, @PathVariable String id) {
        Topic topic = topicService.findTopicById(Integer.parseInt(id));
        List<Comment> comments = commentReponsitory.getAllByTopic_Id(topic.getId());
        int countTopicReact = reactService.countReact(topic);
        Map<Comment, Integer> reactsListComment = new HashMap<>();
        for (Comment comment : comments) {
            int countCommentReact = reactService.countReactComment(comment);
            reactsListComment.put(comment, countCommentReact);
        }
        model.addAttribute("topic", topic);
        model.addAttribute("comments", comments);
        model.addAttribute("commentcount", commentService);
        model.addAttribute("counttopicreact", countTopicReact);
        model.addAttribute("reactsListComment", reactsListComment);
        return "topic";
    }

    @Operation(summary = "Đăng bình luận", description = "Cho phép người dùng đăng bình luận vào một chủ đề")
    @PostMapping("/topic/{id}")
    public String postComment(@ModelAttribute("userdto") UserDto userDto,
                              @PathVariable String id,
                              @RequestBody @ModelAttribute("newcomment") CommentDto commentDto) {
        Topic topic = topicService.findTopicById(Integer.parseInt(id));
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        commentService.save(commentDto, user, topic);
        return "redirect:/topic/" + id;
    }

    @Operation(summary = "React chủ đề", description = "Cho phép người dùng react (up/down) vào một chủ đề")
    @PostMapping("/topic/{id}/down")
    public String downReact(@ModelAttribute("userdto") UserDto userDto,
                            @PathVariable String id) {
        Topic topic = topicService.findTopicById(Integer.parseInt(id));
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        reactService.saveReactTopic("down", topic, user);
        return "redirect:/topic/" + id;
    }

    @Operation(summary = "React chủ đề", description = "Cho phép người dùng react (up/down) vào một chủ đề")
    @PostMapping("/topic/{id}/up")
    public String upReact(@ModelAttribute("userdto") UserDto userDto,
                          @PathVariable String id) {
        Topic topic = topicService.findTopicById(Integer.parseInt(id));
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        reactService.saveReactTopic("up", topic, user);
        return "redirect:/topic/" + id;
    }

    @Operation(summary = "React bình luận", description = "Cho phép người dùng react (up/down) vào một bình luận")
    @PostMapping("/comment/{id}/up")
    public String upcmtReact(@ModelAttribute("userdto") UserDto userDto,
                             @PathVariable String id) {
        Comment comment = commentService.findCommentbyId(Integer.parseInt(id));
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        reactService.saveReactComment("up", comment, user);
        return "redirect:/topic/" + comment.getTopic().getId();
    }

    @Operation(summary = "React bình luận", description = "Cho phép người dùng react (up/down) vào một bình luận")
    @PostMapping("/comment/{id}/down")
    public String downcmtReact(@ModelAttribute("userdto") UserDto userDto,
                               @PathVariable String id) {
        Comment comment = commentService.findCommentbyId(Integer.parseInt(id));
        User user = userReponsitory.getUserByEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        reactService.saveReactComment("down", comment, user);
        return "redirect:/topic/" + comment.getTopic().getId();
    }
}
