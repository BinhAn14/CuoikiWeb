package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tags;

@Repository
@EnableJpaRepositories
public interface TagsReponsitory extends JpaRepository<Tags,Integer> {
}
