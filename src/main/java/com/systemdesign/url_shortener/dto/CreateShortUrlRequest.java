package com.systemdesign.url_shortener.dto;

import java.time.Instant;
import jakarta.validation.constraints.NotBlank;

public record CreateShortUrlRequest(
    @NotBlank(message = "longUrl is required")
    String longUrl,
    String customAlias,
    Instant expiresAt
) {
    
}
