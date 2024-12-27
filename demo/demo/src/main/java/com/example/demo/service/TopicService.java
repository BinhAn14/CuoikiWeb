package com.example.demo.service;

import com.example.demo.dto.TopicDto;
import com.example.demo.model.Topic;
import com.example.demo.model.User;

import org.springframework.stereotype.Service;



@Service
public interface TopicService {
    void save(TopicDto topicDto, User user);
    Topic findTopicById(Integer id);
    void delete(Integer id);
    void update(Topic topic);
}
