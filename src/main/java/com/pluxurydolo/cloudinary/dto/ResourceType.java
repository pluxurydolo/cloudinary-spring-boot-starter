package com.pluxurydolo.cloudinary.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ResourceType {
    IMAGE("image"),
    VIDEO("video");

    private final String type;

    ResourceType(String type) {
        this.type = type;
    }

    @JsonValue
    public String type() {
        return type;
    }

    @JsonCreator
    public static ResourceType fromString(String type) {
        if (type == null) {
            return null;
        }

        return Arrays.stream(values())
            .filter(resourceType -> resourceType.type().equalsIgnoreCase(type))
            .findFirst()
            .orElse(null);
    }
}
