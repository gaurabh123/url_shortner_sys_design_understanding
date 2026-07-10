package com.systemdesign.url_shortener.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemdesign.url_shortener.domain.UrlMapping;
import com.systemdesign.url_shortener.dto.CreateShortUrlRequest;
import com.systemdesign.url_shortener.dto.CreateShortUrlResponse;
import com.systemdesign.url_shortener.service.UrlShorteningService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/urls")
public class UrlController {
    private final UrlShorteningService urlShorteningService;

    public UrlController(UrlShorteningService urlShorteningService){
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping
    public ResponseEntity<CreateShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request){
        UrlMapping mapping = urlShorteningService.createShortUrl(request);

        String shortUrl = "http://localhost:8080/" + mapping.shortCode();

        CreateShortUrlResponse response = new CreateShortUrlResponse(
            mapping.shortCode(),
            shortUrl,
            mapping.longUrl(),
            mapping.expiresAt()
        );

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);

    }
    
}
