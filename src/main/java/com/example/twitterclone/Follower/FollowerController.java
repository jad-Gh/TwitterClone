package com.example.twitterclone.Follower;


import com.example.twitterclone.CustomResponse.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/follow")
@AllArgsConstructor
public class FollowerController {

    private final FollowerService followerService;
    
    @PostMapping()
    public ResponseEntity<CustomResponse> followUser(@RequestBody FollowInput followInput){
        followerService.follow(followInput.getUserId(),followInput.getToFollowId());
        return ResponseEntity.ok().body(
          CustomResponse.builder().timeStamp(LocalDateTime.now())
                  .message("User followed successfully")
                  .statusCode(HttpStatus.OK.value())
                  .status(HttpStatus.OK).build()
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomResponse> followUser(@PathVariable(name = "id") Long id){
        followerService.unFollow(id);
        return ResponseEntity.ok().body(
                CustomResponse.builder().timeStamp(LocalDateTime.now())
                        .message("User unFollowed successfully")
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK).build()
        );
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FollowInput{
    private Long userId;
    private Long toFollowId;
}