package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Topic;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

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
