package com.example.demo.service;
import com.example.demo.model.Profile;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    void update(Profile profile);
}
