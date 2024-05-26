package com.microservice.example.conversionservice.urlshortener;

import com.microservice.example.conversionservice.urlshortener.entity.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UrlShortenerRepositoryTests {

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
    void repositoryFindByShortUrlTest() {
        ShortUrl shortUrl1 = new ShortUrl();
        ShortUrl shortUrl2 = new ShortUrl();
        shortUrl1.setLongUrl("111111");
        shortUrl2.setLongUrl("222222");
        shortUrl1.setShortUrl("12");
        shortUrl2.setShortUrl("22");

        urlRepository.save(shortUrl1);
        urlRepository.save(shortUrl2);


        ShortUrl result = urlRepository.findByShortUrl("12");

        assertNotNull(result, "In this case shouldn't be null");
        assertEquals(shortUrl1.getLongUrl(), result.getLongUrl());

    }

    @Test
    public void saveAllTest() {
        ShortUrl savedUrl = urlRepository.save(shortUrl);

        assertNotNull(savedUrl);
        assertEquals(savedUrl.getShortUrl(), shortUrl.getShortUrl());
    }

    @Test
    public void deleteByShortUrlTest() {
        ShortUrl savedUrl = urlRepository.save(shortUrl);

        urlRepository.delete(shortUrl);
        List<ShortUrl> urlRepositoryAll = (List<ShortUrl>) urlRepository.findAll();

        assertEquals(0, urlRepositoryAll.size());
    }
}
