package com.example.twitterclone.Tweet;


import com.example.twitterclone.CustomResponse.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CustomResponse> getTweets(@RequestParam(value = "page",defaultValue = "0") int page,
                                                        @RequestParam(value = "size",defaultValue = "10") int size,
                                                        @RequestParam(value = "minDate",defaultValue = "1970-12-03T10:15:30")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   LocalDateTime minDate,
                                                        @RequestParam(value = "maxDate", defaultValue = "#{T(java.time.LocalDateTime).now()}")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   LocalDateTime maxDate,
                                                        @RequestParam(value = "text",defaultValue = "") String text
                                                 ){
        Map<String,Object> result = tweetService.getTweets(text,size,page,minDate,maxDate);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now()).status(HttpStatus.OK).statusCode(HttpStatus.OK.value())
                .data(result).build()
        );
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CustomResponse> postTweet(@RequestBody PostTweetInput postTweetInput){
        tweetService.createTweet(postTweetInput.getText(),postTweetInput.getUserId());
        return ResponseEntity.ok().body( CustomResponse.builder()
                                                            .timeStamp(LocalDateTime.now())
                                                            .status(HttpStatus.OK)
                                                            .statusCode(HttpStatus.OK.value())
                                                            .message("Tweet Created Successfully")
                                                            .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteTweet(@PathVariable("id") Long id){
        tweetService.deleteTweet(id);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now()).status(HttpStatus.OK).statusCode(HttpStatus.OK.value())
                .message("Tweet Deleted SuccessFully").build()
        );
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PostTweetInput {
    Long userId;
    String text;
}
