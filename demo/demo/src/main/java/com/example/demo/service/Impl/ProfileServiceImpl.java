package com.example.demo.service.Impl;

import com.example.demo.model.Profile;
import com.example.demo.reponsitory.ProfileReponsitory;
import com.example.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileReponsitory profileReponsitory;

    @Override
    public void update(Profile profile) {
        profileReponsitory.save(profile);
    }
}
