package com.example.twitterclone.CustomResponse;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {
    private LocalDateTime timeStamp;
    private int statusCode;
    private HttpStatus status;
    private String error;
    private String message;
    private Map<String,Object> data;
}
