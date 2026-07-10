package com.systemdesign.url_shortener.exception;

public class ShortCodeExpiredException extends RuntimeException {

    public ShortCodeExpiredException(String shortCode) {
        super("Short code is expired: " + shortCode);
    }
}