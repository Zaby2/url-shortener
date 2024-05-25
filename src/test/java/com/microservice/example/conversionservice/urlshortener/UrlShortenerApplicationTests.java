package com.microservice.example.conversionservice.urlshortener;

import com.microservice.example.conversionservice.urlshortener.controllers.UrlShortenerRestController;
import com.microservice.example.conversionservice.urlshortener.entity.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import com.microservice.example.conversionservice.urlshortener.services.UrlServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlShortenerApplicationTests {
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

}
