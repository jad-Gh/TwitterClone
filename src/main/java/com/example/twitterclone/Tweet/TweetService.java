package com.example.twitterclone.Tweet;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepo;

    public void createTweet(Tweet tweet){
        tweet.setCreatedAt(LocalDateTime.now());
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
