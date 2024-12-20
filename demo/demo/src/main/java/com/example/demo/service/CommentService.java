package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Topic;
import com.example.demo.model.User;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface CommentService {
    void save(CommentDto commentDto, User user, Topic topic);
    int countComment(Topic topic);
    Comment findCommentbyId(Integer id);
    void delete(Integer topicId);

}
