package com.example.Ecommerce.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    /*private final String cloudName= "";


    private final String apiKey= "";

    private final String apiSecret="";*/

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dukeltx3y");
        config.put("api_key", "779584887399741");
        config.put("api_secret", "7bPHNxm2dC9iU4YvVflMp2u1hS8");
        return new Cloudinary(config);
    }
}
