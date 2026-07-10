package com.systemdesign.url_shortener.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.systemdesign.url_shortener.domain.UrlMapping;
import com.systemdesign.url_shortener.repository.UrlRepository;
import com.systemdesign.url_shortener.exception.ShortCodeExpiredException;
import com.systemdesign.url_shortener.exception.ShortCodeNotFoundException;

@Service
public class RedirectService {
    private final UrlRepository urlRepository;

    public RedirectService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public UrlMapping resolve(String shortCode){
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));

        if (mapping.isExpired(Instant.now())){
            throw new ShortCodeExpiredException(shortCode);
        }

        return mapping;
    }

}
