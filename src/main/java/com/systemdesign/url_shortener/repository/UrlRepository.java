package com.systemdesign.url_shortener.repository;

import com.systemdesign.url_shortener.domain.UrlMapping;

import java.util.Optional;

public interface UrlRepository {
    UrlMapping saveNew(UrlMapping mapping);

    Optional<UrlMapping> findByShortCode(String shortCode);
}