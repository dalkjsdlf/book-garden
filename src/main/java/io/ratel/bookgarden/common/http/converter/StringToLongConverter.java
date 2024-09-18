package io.ratel.bookgarden.common.http.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToLongConverter implements Converter<String, Long>{
    @Override
    public Long convert(String source) {
        try {
            return Long.parseLong(source);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Long value: " + source, e);
        }
    }
}
