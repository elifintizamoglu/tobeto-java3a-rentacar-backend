package com.tobeto.rentacar.business.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // İzin verilecek API yollarını belirtin
                .allowedOrigins("http://localhost:4200")  // İzin verilecek origin (frontend URL)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // İzin verilecek HTTP metotları
                .allowCredentials(true);

    }
}
