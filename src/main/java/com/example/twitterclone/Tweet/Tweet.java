package com.example.twitterclone.Tweet;

import com.example.twitterclone.Comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    LocalDateTime createdAt;

    @OneToMany(mappedBy = "tweet")
    List<Comment> commentList;

    public Tweet(String text){
        this.text = text;
    }
}
