package com.example.demo.service.Impl;

import com.example.demo.model.Tags;
import com.example.demo.reponsitory.TagsReponsitory;
import com.example.demo.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    @Autowired
    private TagsReponsitory tagsReponsitory;
    @Override
    public List<Tags> getListTag() {
        return tagsReponsitory.findAll();
    }
}
