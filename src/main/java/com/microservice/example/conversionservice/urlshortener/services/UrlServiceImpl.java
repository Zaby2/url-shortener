package com.microservice.example.conversionservice.urlshortener.services;


import com.google.common.hash.Hashing;
import com.microservice.example.conversionservice.urlshortener.UrlShortenerApplication;
import com.microservice.example.conversionservice.urlshortener.entity.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.logging.Logger;


// where we need to add @Cacheable???
@Service
public class UrlServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlRepository urlRepository;
    @Override
    public ShortUrl generateShortUrl(UrlDto urlDto) {

        if((urlDto.getLongUrl()) != null) {
            String encodedUrl = encodeUrl(urlDto.getLongUrl());
            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setLongUrl(urlDto.getLongUrl());
            shortUrl.setCreationTime(LocalDateTime.now());
            shortUrl.setShortUrl(encodedUrl);
            shortUrl.setExpirationTime(getTheExpirationTime(urlDto.getExpirationDate(), shortUrl.getCreationTime()));
            ShortUrl shortUrlToRet = persistShortUrl(shortUrl); // need to refactor
            return shortUrl; // can it be null??
        }
        return null;
    }


    // saving Entity to db
    @Override
    public ShortUrl persistShortUrl(ShortUrl shortUrl) {
        ShortUrl urlToRet = urlRepository.save(shortUrl); // need to refactor
        return urlToRet;
    }

    // here we get expiration date;
    private LocalDateTime getTheExpirationTime(String expirationTimeDto, LocalDateTime entityCreationTime) {
        if(expirationTimeDto == null) {
            return entityCreationTime.plusSeconds(120);
        }
        LocalDateTime localDateTime = LocalDateTime.parse(expirationTimeDto);
        return localDateTime;
    }


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


    @Scheduled(cron = "@hourly")
    @CacheEvict("shortUrl")
    public void deleteExpiredGeneratedShortLink() {
        Iterable<ShortUrl> shortUrlList = urlRepository.findAll();
        for (ShortUrl shortUrl : shortUrlList) {
            if(shortUrl.getExpirationTime().isBefore(LocalDateTime.now())) {
                urlRepository.delete(shortUrl);
            }
        }
    }

    @Override
    @Cacheable("shortUrl")
    public ShortUrl getEncodeUrl(String shortUrl) {
        final Logger log = (Logger) LoggerFactory.getLogger(UrlShortenerApplication.class);
        log.info("i");
        ShortUrl shortUrlToRet = urlRepository.findByShortUrl(shortUrl);
        return shortUrlToRet;
    }
}
