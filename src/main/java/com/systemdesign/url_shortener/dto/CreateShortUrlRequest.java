package com.systemdesign.url_shortener.dto;

import java.time.Instant;


public record CreateShortUrlRequest(
    String longUrl,
    String customAlias,
    Instant expiresAt
) {
    
}
