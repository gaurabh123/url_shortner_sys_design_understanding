package com.systemdesign.url_shortener.generator;

import java.util.concurrent.atomic.AtomicLong;

public class SequentialShortCodeGenerator implements ShortCodeGenerator{
    
    private final AtomicLong counter = new AtomicLong(1);
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public String generate(){
        long nextValue = counter.getAndIncrement();
        return encodeBase62(nextValue);
    }

    private String encodeBase62(long value){
        StringBuilder encoded = new StringBuilder();
        while (value > 0) {
            int remainder = (int) (value % BASE62.length());
            encoded.append(BASE62.charAt(remainder));
            value = value / BASE62.length();
        }
        return encoded.reverse().toString();
    }
}
