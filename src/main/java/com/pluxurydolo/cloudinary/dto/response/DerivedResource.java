package com.pluxurydolo.cloudinary.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DerivedResource(

    @JsonProperty("public_id")
    String publicId,

    @JsonProperty("format")
    String format,

    @JsonProperty("version")
    Long version,

    @JsonProperty("version_id")
    String versionId,

    @JsonProperty("width")
    Integer width,

    @JsonProperty("height")
    Integer height,

    @JsonProperty("bytes")
    Long bytes,

    @JsonProperty("url")
    String url,

    @JsonProperty("secure_url")
    String secureUrl,

    @JsonProperty("transformation")
    String transformation
) {
}
