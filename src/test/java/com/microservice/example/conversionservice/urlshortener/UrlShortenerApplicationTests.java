package com.microservice.example.conversionservice.urlshortener;

import com.microservice.example.conversionservice.urlshortener.controllers.UrlShortenerRestController;
import com.microservice.example.conversionservice.urlshortener.entity.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import com.microservice.example.conversionservice.urlshortener.services.UrlServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UrlShortenerApplicationTests {
	@Autowired
	UrlRepository urlRepository;
	@Mock
	UrlRepository mockedUrlRepository;
	@InjectMocks
	private UrlServiceImpl mockedUrlService;
	UrlDto dto;
	ShortUrl expectedShortUrl;
	@BeforeEach
	public void setUp() {
		dto = new UrlDto();
		dto.setLongUrl("https://www.youtube.com/watch?v=CihfMVePlcQ&t=1732s");
		dto.setExpirationDate(String.valueOf(LocalDateTime.now()));
		expectedShortUrl = new ShortUrl();
		expectedShortUrl.setShortUrl("49e7f825");
		expectedShortUrl.setLongUrl(dto.getLongUrl());
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void urlServiceGetShortUrlTest() {
		ShortUrl actualShortUrl = mockedUrlService.generateShortUrl(dto);
		
		assertNotNull(actualShortUrl);
		assertEquals(actualShortUrl.getLongUrl(), expectedShortUrl.getLongUrl());
		verify(mockedUrlRepository).save(actualShortUrl);
	}



	@Test
	void generateShortUrlWithEmptyLongUrl() {
		UrlDto dto = new UrlDto();
		dto.setLongUrl(null);
		dto.setExpirationDate(String.valueOf(LocalDateTime.now()));

		ShortUrl actualShortUrl = mockedUrlService.generateShortUrl(dto);

		assertNull(actualShortUrl, "The returned ShortUrl should be null for an empty long URL");
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
		//verify(urlRepository).save(shortUrl1); need to be mocks
		//verify(urlRepository).save(shortUrl2); 
	}


}
