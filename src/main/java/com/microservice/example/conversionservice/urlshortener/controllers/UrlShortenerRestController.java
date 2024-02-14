package com.microservice.example.conversionservice.urlshortener.controllers;

import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UrlShortenerRestController {


    @PostMapping("/short-url")
    public String getUrl(@RequestBody UrlDto urlDto) {

        return "";
    }
}
