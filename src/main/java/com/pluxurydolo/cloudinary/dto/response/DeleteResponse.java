package com.pluxurydolo.cloudinary.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.util.Locale.US;

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
    public boolean isSuccessful() {
        if (result == null) {
            return false;
        }

        return List.of("ok", "deleted")
            .contains(result.toLowerCase(US));
    }
}
