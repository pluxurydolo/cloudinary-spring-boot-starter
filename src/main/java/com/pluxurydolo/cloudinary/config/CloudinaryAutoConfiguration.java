package com.pluxurydolo.cloudinary.config;

import com.cloudinary.Cloudinary;
import com.pluxurydolo.cloudinary.client.CloudinaryClient;
import com.pluxurydolo.cloudinary.properties.CloudinaryProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@AutoConfiguration
@EnableConfigurationProperties(CloudinaryProperties.class)
public class CloudinaryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CloudinaryClient cloudinaryClient(Cloudinary cloudinary, ObjectMapper objectMapper) {
        return new CloudinaryClient(cloudinary, objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public Cloudinary cloudinary(CloudinaryProperties cloudinaryProperties) {
        String cloudName = cloudinaryProperties.cloudName();
        String apiKey = cloudinaryProperties.apiKey();
        String apiSecret = cloudinaryProperties.apiSecret();

        Map<String, String> config = Map.of(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        );

        return new Cloudinary(config);
    }
}
