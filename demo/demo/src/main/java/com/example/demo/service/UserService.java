package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(UserDto userDto);
    Boolean checkPasswordUser(String email,String password);
    Boolean checkUserbyEmail(String email);
    User getUserbyEmail(String email);
}
