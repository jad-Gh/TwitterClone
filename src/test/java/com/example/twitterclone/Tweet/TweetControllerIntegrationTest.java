package com.example.twitterclone.Tweet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
class TweetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepository;

    @Test
    void postTweet() throws Exception{
        Tweet tweet = new Tweet("hello");
        ResultActions resultActions = mockMvc.perform(
                post("/tweets/create")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tweet))).andDo(print());
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string(equalTo("Tweet Created Successfully")));

        List<Tweet> tweets = tweetRepository.findAll();
        List<Tweet> check = tweets.stream().filter(x->x.getText()==tweet.getText()).collect(Collectors.toList());
        assertThat(check.size() >0);
    }

//    @Test
//    void deleteTweet() throws Exception{
//        Tweet tweet = new Tweet("hello");
//
//        ResultActions resultActions = mockMvc.perform(
//                post("/tweets/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(tweet))).andDo(print());
//
//        resultActions.andExpect(status().isOk());
//        resultActions.andExpect(content().string(equalTo("Tweet Created Successfully")));
//
//        List<Tweet> tweets = tweetRepository.findAll();
//    }
}