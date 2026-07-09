package com.systemdesign.url_shortener.dto;

import java.time.Instant;

public record CreateShortUrlResponse(
    String shortCode,
    String shortUrl,
    String longUrl,
    Instant expiresAt
    
) {
    
}
