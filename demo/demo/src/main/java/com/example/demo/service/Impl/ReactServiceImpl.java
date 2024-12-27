package com.example.demo.service.Impl;

import com.example.demo.model.Comment;
import com.example.demo.model.Reacts;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.reponsitory.CommentReponsitory;
import com.example.demo.reponsitory.ReactReponsitory;
import com.example.demo.service.ReactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReactServiceImpl implements ReactService {
    @Autowired
    private ReactReponsitory reactReponsitory;

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Override
    public void saveReactTopic(String namereact, Topic topic, User user) {
        reactReponsitory.save(new Reacts(
                namereact,
                user,
                topic,
                null
        ));
    }

    @Override
    public void saveReactComment(String namereact, Comment comment, User user) {
        reactReponsitory.save(new Reacts(
                namereact,
                user,
                null,
                comment
        ));
    }

    @Override
    public int countReact(Topic topic) {
        List<Reacts> listreact= reactReponsitory.findReactsByTopic(topic);
        int up=0,down=0;

        for(Reacts reacts:listreact){
            if(reacts.getName().equals("up")) up++;
            else down++;
        }
        return up-down;
    }

    @Override
    public int countReactComment(Comment comment) {
        List<Reacts> listreact= reactReponsitory.findReactsByComment(comment);
        int up=0,down=0;

        for(Reacts reacts:listreact){
            if(reacts.getName().equals("up")) up++;
            else down++;
        }
        return up-down;
    }

    @Override
    public void delete(Integer topicId) {
        reactReponsitory.removeAllByTopic_Id(topicId);
        for(Comment comment: commentReponsitory.getAllByTopic_Id(topicId)){
            reactReponsitory.removeAllByComment_Id(comment.getId());
        }
    }
}
