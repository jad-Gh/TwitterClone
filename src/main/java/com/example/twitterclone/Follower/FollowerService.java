package com.example.twitterclone.Follower;


import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.AppUser.AppUserRepository;
import com.example.twitterclone.Tweet.Tweet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final AppUserRepository appUserRepository;

    public void follow(Long userId,Long toFollowId){
        AppUser toFollow = appUserRepository.findById(toFollowId).orElseThrow(()->new RuntimeException("User not found"));
        AppUser user = appUserRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

        Follower follower = new Follower();
        follower.setCreatedAt(LocalDateTime.now());
        follower.setFollower(user);
        follower.setFollowing(toFollow);

        followerRepository.save(follower);
    }

    public void unFollow(Long followId){
        followerRepository.deleteById(followId);
    }

}
