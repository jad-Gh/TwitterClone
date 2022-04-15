package com.example.twitterclone.Tweet;

import com.example.twitterclone.AppUser.AppUser;
import com.example.twitterclone.AppUser.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepo;
    private final AppUserRepository appUserRepository;

    public void createTweet(String text,Long userId){
        Tweet tweet =new Tweet();
        tweet.setText(text);
        tweet.setCreatedAt(LocalDateTime.now());

        AppUser appUser = appUserRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found"));
        tweet.setTweetedUser(appUser);
        tweetRepo.save(tweet);
    }

    public void deleteTweet(Long id){
        tweetRepo.deleteById(id);
    }

    public Map<String,Object> getTweets(String text,int pageSize,int page,LocalDateTime minDate,LocalDateTime maxDate){
        Pageable paging = PageRequest.of(page,pageSize, Sort.by(Sort.Direction.ASC,"createdAt"));
        Page<Tweet> tweets = tweetRepo.findByTextContainingIgnoreCaseAndCreatedAtBetween(text,minDate,maxDate,paging);
        List<Tweet> tweetList = tweets.getContent();
        Map<String,Object> result = new HashMap<>();
        result.put("Current Page",tweets.getNumber());
        result.put("Total Pages",tweets.getTotalPages());
        result.put("Total Elements",tweets.getTotalElements());
        result.put("Current Elements",tweets.getNumberOfElements());
        result.put("data",tweetList);
        return result;
    }

}
