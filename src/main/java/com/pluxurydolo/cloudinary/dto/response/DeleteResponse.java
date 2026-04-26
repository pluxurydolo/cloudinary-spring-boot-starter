package com.pluxurydolo.cloudinary.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DeleteResponse(

    @JsonProperty("result")
    String result,

    @JsonProperty("message")
    String message,

    @JsonProperty("http_code")
    Integer httpCode,

    @JsonProperty("partial")
    Boolean partial
) {
}
