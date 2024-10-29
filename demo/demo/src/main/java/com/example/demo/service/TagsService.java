package com.example.demo.service;

import com.example.demo.entity.Tags;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagsService {
    List<Tags> getListTag();
}
