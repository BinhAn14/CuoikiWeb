package com.example.demo.controller.navbar;

import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.reponsitory.ProfileReponsitory;
import com.example.demo.service.ProfileService;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@SessionAttributes("userdto")
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileReponsitory profileReponsitory;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("profileuser")
    public ProfileDto profileDto() {
        return new ProfileDto();
    }


    @GetMapping("/profile")
    public String showprofile(@ModelAttribute("userdto") UserDto userDto, Model model) {
        User user = userService.getUserbyEmail(userDto.getEmail());
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        Profile profile = profileReponsitory.findProfileByUser(user);
        model.addAttribute("profile", profile);
        return "profile";
    }

    
    @PostMapping("/profile/{id}")
    public String getprofile(@SessionAttribute("userdto") UserDto userDto,
                             @PathVariable String id,
                             Model model,
                             @RequestBody @ModelAttribute("profile") ProfileDto profileDto,
                             RedirectAttributes redirectAttributes) {
        Profile profileupdate = profileReponsitory.findProfileById(Integer.parseInt(id));
        profileupdate.setAdress(profileDto.getAdress());
        profileupdate.setDateofBirth(profileDto.getDateofBirth());
        profileupdate.setGender(profileDto.getGender());
        profileupdate.setFirstName(profileDto.getFirstName());
        profileupdate.setLastName(profileDto.getLastName());
        profileService.update(profileupdate);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/profile";
    }
}
