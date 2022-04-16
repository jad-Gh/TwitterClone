package com.example.twitterclone.Like;


import com.example.twitterclone.CustomResponse.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/like")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping()
    public ResponseEntity<CustomResponse> likeTweet(@RequestBody LikeInput likeInput) {
        likeService.likeTweet(likeInput.getTweetId(), likeInput.getUserId());
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message("Tweet liked successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> unLikeTweet(@PathVariable(name = "id") Long likeId) {
        likeService.unLikeTweet(likeId);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message("Tweet unliked successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class LikeInput {
    private Long tweetId;
    private Long userId;
}
