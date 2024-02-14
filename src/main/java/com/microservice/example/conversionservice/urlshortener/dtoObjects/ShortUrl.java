package com.microservice.example.conversionservice.urlshortener.dtoObjects;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShortUrl {
    @Id
    @GeneratedValue
    private long id;
    @Lob
    private String longUrl;
    private String ShortUrl;
    private LocalDateTime creationTime;
    private LocalDateTime expirationTime;
    public ShortUrl(long id, String longUrl, String shortUrl, LocalDateTime creationTime, LocalDateTime expirationTime) {
        super();
        this.id = id;
        this.longUrl = longUrl;
        ShortUrl = shortUrl;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }
}
