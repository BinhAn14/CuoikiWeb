package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Profile;
import com.example.demo.model.User;

@Repository
@EnableJpaRepositories
public interface ProfileReponsitory extends JpaRepository<Profile,Integer> {
    Profile findProfileByUser(User user);
    Profile findProfileById(int id);
}
