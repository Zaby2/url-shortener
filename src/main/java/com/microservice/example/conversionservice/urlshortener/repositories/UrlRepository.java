package com.microservice.example.conversionservice.urlshortener.repositories;

import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<ShortUrl, Long> {
    public ShortUrl findByShortUrl(String shorUrl);
}
