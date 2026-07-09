package com.systemdesign.url_shortener.repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.systemdesign.url_shortener.domain.UrlMapping;

public class InMemoryUrlRepository implements UrlRepository{
    private final ConcurrentMap<String, UrlMapping> mappings = new ConcurrentHashMap<>();
    
    @Override
    public UrlMapping saveNew(UrlMapping mapping) {
        UrlMapping existing = mappings.putIfAbsent(mapping.shortCode(), mapping);

        if (existing != null){
            throw new IllegalArgumentException("Short code already exists: " + mapping.shortCode());
        }
        return mapping;
    }

    @Override
    public Optional<UrlMapping> findByShortCode(String shortCode) {
        return Optional.ofNullable(mappings.get(shortCode));
    }

}
