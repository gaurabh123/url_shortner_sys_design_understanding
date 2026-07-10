package com.systemdesign.url_shortener.exception;

public class AliasAlreadyExistsException extends RuntimeException {

    public AliasAlreadyExistsException(String shortCode) {
        super("Short code already exists: " + shortCode);
    }
}