package com.systemdesign.url_shortener.service;

import java.time.Instant;

import com.systemdesign.url_shortener.domain.UrlMapping;
import com.systemdesign.url_shortener.dto.CreateShortUrlRequest;
import com.systemdesign.url_shortener.generator.ShortCodeGenerator;
import com.systemdesign.url_shortener.repository.UrlRepository;

public class UrlShorteningService {
    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlShorteningService(UrlRepository urlRepository, ShortCodeGenerator shortCodeGenerator){
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public UrlMapping createShortUrl(CreateShortUrlRequest request){
        String shortCode;

        if (request.customAlias() != null && !request.customAlias().isBlank()){
            shortCode = request.customAlias();
        } else {
            shortCode = shortCodeGenerator.generate();
        }

        UrlMapping mapping = new UrlMapping(
            shortCode,
            request.longUrl(),
            Instant.now(),
            request.expiresAt()
        );

        return urlRepository.saveNew(mapping);
    }
    
}
