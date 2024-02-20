package com.microservice.example.conversionservice.urlshortener;

import com.microservice.example.conversionservice.urlshortener.controllers.UrlShortenerRestController;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.ShortUrl;
import com.microservice.example.conversionservice.urlshortener.dtoObjects.UrlDto;
import com.microservice.example.conversionservice.urlshortener.repositories.UrlRepository;
import com.microservice.example.conversionservice.urlshortener.services.UrlServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UrlShortenerApplicationTests {

	@Mock
	private UrlServiceImpl mockedUrlService;

	@Mock
	UrlRepository urlRepository;
	@Mock
	UrlDto urlDto;

	@InjectMocks
	UrlShortenerRestController urlShortenerRestController;

	@Test
	@DisplayName("Redirection - работает исправно")
	void urlServiceTest() {
		var urls = List.of(new ShortUrl(1000, "https://www.youtube.com/watch?v=CihfMVePlcQ&t=1732s","49e7f825", null , null));
		doReturn(urls.get(0)).when(this.mockedUrlService).getEncodeUrl("49e7f825");
		var responseEntity = this.urlShortenerRestController.redirectToUrl("49e7f825");
		assertNotNull(responseEntity);
	}

}
