package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dbo_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_display_name", nullable = false)
    private String UserDisplayName;

    @Column(name = "about_me", nullable = true)
    private String AboutMe;

    @Column(name = "views", nullable = false)
    private int Views;

    @Column(name = "topic_counts", nullable = false)
    private int TopicCounts;

    @Column(name = "password", nullable = false)
    private String Password;

    @Column(name = "creation_date", nullable = false)
    private String CreationDate;

    @Column(name = "role", nullable = false)
    private String Role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public User(String Email, String userDisplayName, String aboutMe, int views, int topicCounts, String password, String creationDate, String role) {
        this.email = Email;
        this.UserDisplayName = userDisplayName;
        this.AboutMe = aboutMe;
        this.Views = views;
        this.TopicCounts = topicCounts;
        this.Password = password;
        this.CreationDate = creationDate;
        this.Role = role;
    }
}

