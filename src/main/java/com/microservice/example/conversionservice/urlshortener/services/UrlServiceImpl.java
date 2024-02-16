package com.microservice.example.conversionservice.urlshortener.services;


import com.google.common.hash.Hashing;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service // is it better to put component here???
public class UrlServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlRepository urlRepository;
    @Override
    public ShortUrl generateShortUrl(UrlDto urlDto) {
        if(!(urlDto.getLongUrl()).isEmpty()) { // deprecated
            String encodedUrl = encodeUrl(urlDto.getLongUrl());
            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setLongUrl(urlDto.getLongUrl());
            shortUrl.setCreationTime(LocalDateTime.now());
            shortUrl.setShortUrl(encodedUrl);
            shortUrl.setExpirationTime(getTheExpirationTime(urlDto.getExpirationDate(), shortUrl.getExpirationTime()));
            ShortUrl shortUrlToRet = persistShortUrl(shortUrl); // need to refactor
            return shortUrlToRet; // can it be null??
        }
        return null; // error handler class better to add
    }


    // saving Entity to db
    @Override
    public ShortUrl persistShortUrl(ShortUrl shortUrl) {
        ShortUrl urlToRet = urlRepository.save(shortUrl); // need to refactor
        return urlToRet;
    }

    // here we get expiration date;
    private LocalDateTime getTheExpirationTime(String expirationTimeDto, LocalDateTime entityCreationTime) {
        if(expirationTimeDto.isBlank()) {
            return entityCreationTime.plusSeconds(120);
        }
        LocalDateTime localDateTime = LocalDateTime.parse(expirationTimeDto);
        return localDateTime;
    }

    // here we will generate the short link
    private String encodeUrl(String longUrl) {
        String encodeUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodeUrl = Hashing.murmur3_32()
                .hashString(longUrl.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodeUrl;
    }


    // actually this need to be scheduled???
    @Override
    public void deleteGeneratedShortLink(ShortUrl shortUrl) {
       urlRepository.delete(shortUrl);
    }


    // If this method need to be async???
    @Scheduled(cron = "@hourly")
    public void deleteExpiredGeneratedShortLink() {
        Iterable<ShortUrl> shortUrlList = urlRepository.findAll();
        for (ShortUrl shortUrl : shortUrlList) {
            if(shortUrl.getExpirationTime().isBefore(LocalDateTime.now())) {
                urlRepository.delete(shortUrl);
            }
        }
    }

    @Override
    public ShortUrl getEncodeUrl(String shortUrl) {
        ShortUrl shortUrlToRet = urlRepository.findByShortUrl(shortUrl);
        return shortUrlToRet;
    }
}
