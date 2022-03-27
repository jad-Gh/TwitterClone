package com.example.twitterclone.Tweet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TweetServiceUnitTest {

    @Mock private TweetRepository tweetRepository;
    private TweetService tweetService;

    @BeforeEach
    void setUp() {
        tweetService = new TweetService(tweetRepository);
    }

    @Test
    void createTweet() {
        //given
        Tweet tweet = new Tweet("A tweet");
        //when
        tweetService.createTweet(tweet);
        //then
        ArgumentCaptor<Tweet> captor = ArgumentCaptor.forClass(Tweet.class);
        Mockito.verify(tweetRepository).save(captor.capture());
    }

    @Test
    void deleteTweet() {
        //given
        long id = 10;
//        BDDMockito.given(tweetRepository.existsById(id)).willReturn(true);
        //when
        tweetService.deleteTweet(id);
        //then
        Mockito.verify(tweetRepository).deleteById(id);
    }
}