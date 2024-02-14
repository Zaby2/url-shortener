package com.microservice.example.conversionservice.urlshortener.repositories;

import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<ShortUrl, Long> {
    public ShortUrl findByShortUrl(String shorUrl);
}
