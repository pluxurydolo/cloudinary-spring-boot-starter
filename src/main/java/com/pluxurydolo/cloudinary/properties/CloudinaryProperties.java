package com.pluxurydolo.cloudinary.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Name;

@ConfigurationProperties(prefix = "cloudinary")
public record CloudinaryProperties(

    @Name("cloud.name")
    String cloudName,

    @Name("api.key")
    String apiKey,

    @Name("api.secret")
    String apiSecret
) {
}
