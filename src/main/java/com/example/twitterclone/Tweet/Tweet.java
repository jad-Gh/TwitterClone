package com.example.twitterclone.Tweet;

import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.Comment.Comment;
import com.example.twitterclone.Like.LkClass;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "tweet")
    private List<Comment> commentList;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser tweetedUser;

    @OneToMany(mappedBy = "lkdTweet")
    private List<LkClass> lkList;

    public Tweet(String text){
        this.text = text;
    }
}
