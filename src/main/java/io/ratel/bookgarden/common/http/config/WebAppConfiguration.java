package io.ratel.bookgarden.common.http.config;

import io.ratel.bookgarden.common.http.converter.StringToLongConverter;
import io.ratel.bookgarden.common.http.converter.StringToYnConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLongConverter()); // 수동 등록도 가능
        registry.addConverter(new StringToYnConverter());
    }
}
