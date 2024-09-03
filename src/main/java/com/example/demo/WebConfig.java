package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allows CORS on all endpoints
                        .allowedOrigins("http://localhost:3000") // Allows requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows these HTTP methods
                        .allowedHeaders("*") // Allows all headers
                        .allowCredentials(true); // Allows credentials
            }
        };
    }
}
