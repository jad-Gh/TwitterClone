package com.example.twitterclone.Comment;


import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.Tweet.Tweet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false,updatable = false)
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    AppUser owningUser;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    @JsonIgnore
    Tweet tweet;

}
