package com.example.demo.service;

import com.example.demo.dto.ProfileDto;
import com.example.demo.model.Profile;
import com.example.demo.model.User;

import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    void update(Profile profile);
}
