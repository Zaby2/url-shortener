package com.microservice.example.conversionservice.urlshortener.dtoObjects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UrlDto {
    public UrlDto(String longUrl, String expirationDate) {
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
    }

    private String longUrl;
    private String expirationDate;
}
