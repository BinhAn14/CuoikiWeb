package com.example.demo.reponsitory;

import com.example.demo.entity.relationship.TopicAndTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TopicAndTagsRepository extends JpaRepository<TopicAndTags,Integer> {
}
