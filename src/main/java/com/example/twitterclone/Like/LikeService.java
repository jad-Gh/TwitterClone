package com.example.twitterclone.Like;


import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.AppUser.AppUserRepository;
import com.example.twitterclone.Tweet.Tweet;
import com.example.twitterclone.Tweet.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;
    private final AppUserRepository appUserRepository;

    public void likeTweet(Long tweetId,Long userId){
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()->new RuntimeException("tweet not found"));
        AppUser user = appUserRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

        LkClass lkClass = new LkClass();
        lkClass.setLkdTweet(tweet);
        lkClass.setLkngUser(user);
        lkClass.setCreatedAt(LocalDateTime.now());

        likeRepository.save(lkClass);
    }

    public void unLikeTweet(Long likeId){
        likeRepository.findById(likeId).orElseThrow(()->new RuntimeException("Like not found"));

        likeRepository.deleteById(likeId);
    }
}
