package com.example.RestApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionDTO {
    private String apiPath;
    private int statusCode;
    private String message;
    private LocalDateTime time;
}
