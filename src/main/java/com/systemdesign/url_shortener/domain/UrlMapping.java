package com.systemdesign.url_shortener.domain;

import java.time.Instant;

public record UrlMapping(
    String shortCode,
    String longUrl,
    Instant createdAt,
    Instant expiresAt

){
    public boolean isExpired(Instant now){
        return expiresAt != null && !expiresAt.isAfter(now); // this reads as:
        // there is an expiration time and expiration time is after now
    }

}