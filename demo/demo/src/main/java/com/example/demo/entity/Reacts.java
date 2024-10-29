package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reacts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String Name;

    @ManyToOne
    @JoinColumn(name= "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="topic_id",nullable = true)
    private  Topic topic;

    @ManyToOne
    @JoinColumn(name="comment_id",nullable = true)
    private  Comment comment;

    public Reacts(String name, User user, Topic topic, Comment comment) {
        Name = name;
        this.user = user;
        this.topic = topic;
        this.comment = comment;
    }


}
