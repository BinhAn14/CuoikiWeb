package com.example.demo.reponsitory;

import com.example.demo.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TagsReponsitory extends JpaRepository<Tags,Integer> {
}
