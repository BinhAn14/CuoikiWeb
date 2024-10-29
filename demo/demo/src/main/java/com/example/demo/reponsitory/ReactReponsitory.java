package com.example.demo.reponsitory;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Reacts;
import com.example.demo.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ReactReponsitory extends JpaRepository<Reacts,Integer> {
    List<Reacts> findReactsByTopic(Topic topic);
    List<Reacts> findReactsByComment(Comment comment);
    void removeAllByTopic_Id(Integer topicId);
    void removeAllByComment_Id(Integer commentId);
}
