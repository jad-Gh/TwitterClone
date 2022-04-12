package com.example.twitterclone.Comment;


import com.example.twitterclone.CustomResponse.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<CustomResponse> commentOnTweet(@RequestBody CommentShape commentShape){
        commentService.addComment(commentShape.getText(),commentShape.getTweetId(),commentShape.getAppUserId());

        return ResponseEntity.ok().body(CustomResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Comment added Successfully")
                .build()
        );
    }
}

@Data
class CommentShape{
    Long tweetId;
    Long appUserId;
    String text;
}
