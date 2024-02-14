package com.microservice.example.conversionservice.urlshortener.services;


import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import org.springframework.util.StringUtils;

public class UrlServiceImpl implements UrlShortenerService {
    @Override
    public ShortUrl generateShortUrl(UrlDto urlDto) {
        if(!(urlDto.getLongUrl()).isEmpty()) { // deprecated
            String encodedUrl = encodeUrl(urlDto.getLongUrl());
        }
        return null;
    }

    @Override
    public ShortUrl persistShortUrl(ShortUrl shortUrl) {
        return null;
    }


    // here we will generate the short link
    @Override
    public String encodeUrl(String longUrl) {
        return null;
    }



    @Override
    public void deleteGeneratedShortLink(ShortUrl shortUrl) {

    }

    @Override
    public ShortUrl getEncodeUrl(ShortUrl shortUrl) {
        return null;
    }
}
