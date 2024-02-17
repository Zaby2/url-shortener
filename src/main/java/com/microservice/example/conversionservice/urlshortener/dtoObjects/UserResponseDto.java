package com.microservice.example.conversionservice.urlshortener.dtoObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserResponseDto {
    private String longUrl;
    private String shortUrl;
    private LocalDateTime expirationDate;
    public UserResponseDto(String longUrl, String shortUrl, LocalDateTime expirationDate) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.expirationDate = expirationDate;
    }


}
