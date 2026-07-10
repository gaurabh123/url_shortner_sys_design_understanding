package com.systemdesign.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;

public record CreateShortUrlRequest(
        @NotBlank(message = "longUrl is required")
        String longUrl,

        @Pattern(
                regexp = "^[A-Za-z0-9_-]{3,32}$",
                message = "customAlias must be 3-32 characters and contain only letters, numbers, underscores, or hyphens"
        )
        String customAlias,

        Instant expiresAt
) {
}
