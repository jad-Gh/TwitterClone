package com.example.twitterclone.Comment;


import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.AppUser.AppUserRepository;
import com.example.twitterclone.Tweet.Tweet;
import com.example.twitterclone.Tweet.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final AppUserRepository appUserRepository;

    public void addComment(String text,Long tweetId,Long appUserId){
        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreatedAt(LocalDateTime.now());

        AppUser appUser = appUserRepository
                .findById(appUserId).orElseThrow(()->new UsernameNotFoundException("User not found"));

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()->new RuntimeException("Tweet not found"));

        comment.setOwningUser(appUser);
        comment.setTweet(tweet);

        commentRepository.save(comment);
    }


}
