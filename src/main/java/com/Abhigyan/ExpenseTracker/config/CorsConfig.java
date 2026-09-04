package com.Abhigyan.Expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Without this, the browser blocks requests from the frontend (a
// different origin - even a local file:// page counts as one) to this
// API. This class tells Spring: allow cross-origin requests to any
// /api/** endpoint, from any origin, using these HTTP methods.
//
// This is intentionally permissive (origins "*") because it's a local
// learning project. In a real production app you'd lock allowedOrigins
// down to your actual frontend's domain instead of "*".
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}