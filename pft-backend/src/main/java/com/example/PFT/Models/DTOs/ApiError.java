package com.example.PFT.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ApiError {
    private int status;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime timestamp;

    public ApiError(int status,String message){
        this.status=status;
        this.message=message;
        this.timestamp=LocalDateTime.now();
    }
}
