package com.pluxurydolo.cloudinary.configuration;

import com.cloudinary.Cloudinary;
import com.pluxurydolo.cloudinary.client.CloudinaryClient;
import com.pluxurydolo.cloudinary.properties.CloudinaryProperties;
import com.pluxurydolo.cloudinary.validator.ResponseValidator;
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
    public CloudinaryClient cloudinaryClient(
        Cloudinary cloudinary,
        ResponseValidator responseValidator,
        ObjectMapper objectMapper
    ) {
        return new CloudinaryClient(cloudinary, responseValidator, objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ResponseValidator responseValidator() {
        return new ResponseValidator();
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
