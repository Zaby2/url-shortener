package com.microservice.example.conversionservice.urlshortener.controllers;

import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UserResponseDto;
import com.microservice.example.conversionservice.urlshortener.services.UrlServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UrlShortenerRestController {

    @Autowired
    UrlServiceImpl urlService;


    // Error handlers need to be created
    // Create bean for ResponseEntity for durability
    @PostMapping("/short-url")
    public ResponseEntity<UserResponseDto> getUrl(@RequestBody UrlDto urlDto) {
        ShortUrl shortUrlToRet = urlService.generateShortUrl(urlDto);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setShortUrl(shortUrlToRet.getShortUrl());
        userResponseDto.setLongUrl(shortUrlToRet.getLongUrl());
        userResponseDto.setExpirationDate(shortUrlToRet.getExpirationTime());
        return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK);
    }
}
