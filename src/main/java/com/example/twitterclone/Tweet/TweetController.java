package com.example.twitterclone.Tweet;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/tweets")
public class TweetController {

    private final TweetService tweetService;

    @GetMapping()
    public ResponseEntity<Map<String,Object>> getTweets(@RequestParam(value = "page",defaultValue = "0") int page,
                                                        @RequestParam(value = "size",defaultValue = "10") int size,
                                                        @RequestParam(value = "minDate",defaultValue = "1970-12-03T10:15:30") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   LocalDateTime minDate,
                                                        @RequestParam(value = "maxDate", defaultValue = "#{T(java.time.LocalDateTime).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   LocalDateTime maxDate,
                                                        @RequestParam(value = "text",defaultValue = "") String text

                                                 ){
        return ResponseEntity.ok().body(tweetService.getTweets(text,size,page,minDate,maxDate));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> postTweet(@RequestBody Tweet tweet){
        tweetService.createTweet(tweet);
        return ResponseEntity.ok().body("Tweet Created Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable("id") Long id){
        tweetService.deleteTweet(id);
        return ResponseEntity.ok().body("Tweet Deleted Successfully");
    }
}
