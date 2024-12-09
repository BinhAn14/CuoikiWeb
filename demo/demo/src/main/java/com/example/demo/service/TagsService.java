package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Tags;

import java.util.List;

@Service
public interface TagsService {
    List<Tags> getListTag();
}
