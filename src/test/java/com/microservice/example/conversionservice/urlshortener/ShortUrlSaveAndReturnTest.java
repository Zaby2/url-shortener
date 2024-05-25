package com.microservice.example.conversionservice.urlshortener;

import com.microservice.example.conversionservice.urlshortener.entity.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ShortUrlSaveAndReturnTest {
    
    @Autowired
    UrlRepository urlRepository;
    ShortUrl shortUrl;
    @BeforeEach
    public void setUp() {
        shortUrl = ShortUrl.builder().
                shortUrl("12")
                .longUrl("12212121")
                .build();
    }
    @Test
    public void saveAllTest() {
        ShortUrl savedUrl = urlRepository.save(shortUrl);

        assertNotNull(savedUrl);
        assertEquals(savedUrl.getShortUrl(), shortUrl.getShortUrl());
    }
}
