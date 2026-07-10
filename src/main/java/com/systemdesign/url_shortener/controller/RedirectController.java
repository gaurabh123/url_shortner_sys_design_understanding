package com.systemdesign.url_shortener.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.systemdesign.url_shortener.domain.UrlMapping;
import com.systemdesign.url_shortener.service.RedirectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    private final RedirectService redirectService;

    public RedirectController(RedirectService redirectService){
        this.redirectService = redirectService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode){
        UrlMapping mapping = redirectService.resolve(shortCode);

        return ResponseEntity
        .status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, mapping.longUrl())
        .build();

    }
    
}
