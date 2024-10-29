package com.example.demo.reponsitory;

import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProfileReponsitory extends JpaRepository<Profile,Integer> {
    Profile findProfileByUser(User user);
    Profile findProfileById(int id);
}
