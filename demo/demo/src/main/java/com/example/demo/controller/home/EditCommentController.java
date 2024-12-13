package com.example.demo.controller.home;

import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@SessionAttributes("userdto")
@Tag(name = "Edit Comment", description = "API cho chỉnh sửa nội dung bình luận (Topic)")
public class EditCommentController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @Operation(summary = "Hiển thị form chỉnh sửa bình luận", 
               description = "Lấy dữ liệu của một Topic để hiển thị trong form chỉnh sửa")
    @GetMapping("/edit_comment/{id}")
    public String showEditTopic(Model model, 
                                @PathVariable String id,
                                @ModelAttribute("userdto") UserDto userDto) {
        Topic topic = topicService.findTopicById(Integer.parseInt(id));
        if (topic == null) {
            model.addAttribute("error", "Topic không tồn tại");
            return "error";
        }
        model.addAttribute("topic", topic);
        return "edit_comment"; 
    }

    @Operation(summary = "Cập nhật nội dung bình luận", 
               description = "Cập nhật nội dung của một Topic trong cơ sở dữ liệu")
    @PostMapping("/edit_comment/{id}")
    public String updateTopic(@PathVariable String id,
                              @ModelAttribute("userdto") UserDto userDto,
                              @ModelAttribute("topic") TopicDto topicDto) {
        Topic updateTopic = topicService.findTopicById(Integer.parseInt(id));
        if (updateTopic == null) {
            return "redirect:/error"; 
        }

        updateTopic.setBody(topicDto.getBody());
        topicService.update(updateTopic);
        return "redirect:/home"; 
    }
}
