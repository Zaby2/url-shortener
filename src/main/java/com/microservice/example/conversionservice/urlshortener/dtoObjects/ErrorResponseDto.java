package com.microservice.example.conversionservice.urlshortener.dtoObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ErrorResponseDto {
    private int errorCode;
    private String errorDescription;
}
