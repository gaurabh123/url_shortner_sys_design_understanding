package com.systemdesign.url_shortener.service;

import com.systemdesign.url_shortener.domain.UrlMapping;
import com.systemdesign.url_shortener.dto.CreateShortUrlRequest;
import com.systemdesign.url_shortener.exception.InvalidUrlException;
import com.systemdesign.url_shortener.generator.ShortCodeGenerator;
import com.systemdesign.url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;

@Service
public class UrlShorteningService {
    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlShorteningService(UrlRepository urlRepository, ShortCodeGenerator shortCodeGenerator) {
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public UrlMapping createShortUrl(CreateShortUrlRequest request) {
        validateLongUrl(request.longUrl());
        validateExpiration(request.expiresAt());

        String shortCode;

        if (request.customAlias() != null && !request.customAlias().isBlank()) {
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

    private void validateLongUrl(String longUrl) {
        URI uri;

        try {
            uri = URI.create(longUrl);
        } catch (IllegalArgumentException ex) {
            throw new InvalidUrlException("longUrl must be a valid URL");
        }

        String scheme = uri.getScheme();

        if (!uri.isAbsolute() || uri.getHost() == null) {
            throw new InvalidUrlException("longUrl must be an absolute URL");
        }

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            throw new InvalidUrlException("longUrl must use http or https");
        }
    }

    private void validateExpiration(Instant expiresAt) {
        if (expiresAt != null && !expiresAt.isAfter(Instant.now())) {
            throw new InvalidUrlException("expiresAt must be in the future");
        }
    }
}
