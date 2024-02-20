package com.microservice.example.conversionservice.urlshortener.controllers;

import com.microservice.example.conversionservice.urlshortener.dtoObjects.ErrorResponseDto;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UserResponseDto;
import com.microservice.example.conversionservice.urlshortener.services.UrlServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


// error handling need to be fixed
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UrlShortenerRestController {

    @Autowired
    UrlServiceImpl urlService;

    @Autowired
    ErrorResponseDto errorResponseDto; // idk why I decided to make it like this

    @PostMapping("/short-url")
    public ResponseEntity<?> getUrl(@RequestBody UrlDto urlDto) {
        if(urlDto == null) { // this is sheet
            errorResponseDto.setErrorCode(404);
            errorResponseDto.setErrorDescription("Provide a link please");
            return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.OK);
        }
        ShortUrl shortUrlToRet = urlService.generateShortUrl(urlDto);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setShortUrl(shortUrlToRet.getShortUrl());
        userResponseDto.setLongUrl(shortUrlToRet.getLongUrl());
        userResponseDto.setExpirationDate(shortUrlToRet.getExpirationTime());
        return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToUrl(@PathVariable String shortUrl) {
        if(shortUrl.isEmpty()) {
            errorResponseDto.setErrorCode(404);
            errorResponseDto.setErrorDescription("No short link like this");
            //return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }
        return new RedirectView(urlService.getEncodeUrl(shortUrl).getLongUrl());
    }
}
