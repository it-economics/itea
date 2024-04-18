package com.ite.itea.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed.mappings}")
    private List<String> corsAllowedMappings;

    @Value("${cors.allowed.origins}")
    private String[] corsAllowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("corsAllowedMappings: " + corsAllowedMappings);
                corsAllowedMappings.forEach(mapping -> registry.addMapping(mapping).allowedOrigins(corsAllowedOrigins));
            }
        };
    }
}
