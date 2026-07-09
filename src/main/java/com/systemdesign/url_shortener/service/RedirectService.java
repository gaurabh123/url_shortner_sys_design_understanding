package com.systemdesign.url_shortener.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.systemdesign.url_shortener.domain.UrlMapping;
import com.systemdesign.url_shortener.repository.UrlRepository;

@Service
public class RedirectService {
    private final UrlRepository urlRepository;

    public RedirectService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public UrlMapping resolve(String shortCode){
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new IllegalArgumentException("short code not found: " + shortCode));

        if (mapping.isExpired(Instant.now())){
            throw new IllegalStateException("short code is expired: " + shortCode);
        }
        
        return mapping;
    }

}
