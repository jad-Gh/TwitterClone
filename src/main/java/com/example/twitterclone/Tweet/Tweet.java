package com.example.twitterclone.Tweet;

import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.Comment.Comment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
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
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    AppUser tweetedUser;

    public Tweet(String text){
        this.text = text;
    }
}
