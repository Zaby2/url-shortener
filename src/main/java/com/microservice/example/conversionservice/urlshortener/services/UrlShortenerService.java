package com.microservice.example.conversionservice.urlshortener.services;

import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import org.springframework.stereotype.Service;


public interface UrlShortenerService {
    public ShortUrl generateShortUrl(UrlDto urlDto);
    public ShortUrl persistShortUrl(ShortUrl shortUrl);

    private String encodeUrl(String longUrl) {
        return null;
    }

    public void deleteGeneratedShortLink(ShortUrl shortUrl);
    public ShortUrl getEncodeUrl(String longUrl);
}
