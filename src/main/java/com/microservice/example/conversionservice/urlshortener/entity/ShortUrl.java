package com.microservice.example.conversionservice.urlshortener.entity;



import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name  = "shorturls")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
public class ShortUrl {
    @Id
    @GeneratedValue
    private long id;
    private String longUrl;
    private String shortUrl;
    private LocalDateTime creationTime;
    private LocalDateTime expirationTime;
    public ShortUrl(long id, String longUrl, String shortUrl, LocalDateTime creationTime, LocalDateTime expirationTime) {
        super();
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }
}
