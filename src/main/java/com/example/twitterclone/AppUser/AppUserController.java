package com.example.twitterclone.AppUser;

import com.example.twitterclone.CustomResponse.CustomResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping()
    public ResponseEntity<CustomResponse> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Map<String,Object> result = appUserService.findUsers(page,size);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(result).build());
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> addUser(@RequestBody AppUser appUser){
        appUserService.addUser(appUser);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("User created Successfully").build()
        );
    }

    @PostMapping("/edit")
    public ResponseEntity<CustomResponse> editUser(@RequestBody AppUser appUser){
        appUserService.editUser(appUser);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("User updated Successfully").build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable("id") Long id){
        appUserService.deleteUser(id);
        return ResponseEntity.ok().body(CustomResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("User Deleted Successfully").build()
        );
    }

}
