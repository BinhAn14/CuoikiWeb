package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.Topic;
import com.example.demo.model.User;

import javax.transaction.Transactional;

@Service
@Transactional
public interface ReactService {
    void saveReactTopic(String namereact, Topic topic, User user);
    void saveReactComment(String namereact, Comment comment, User user);
    int countReact(Topic topic);
    int countReactComment(Comment comment);
    void delete(Integer topicId);
}
