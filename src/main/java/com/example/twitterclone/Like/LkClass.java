package com.example.twitterclone.Like;


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
public class LkClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_liked_id")
    private AppUser lkngUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tweet_liked_id")
    private Tweet lkdTweet;
}
