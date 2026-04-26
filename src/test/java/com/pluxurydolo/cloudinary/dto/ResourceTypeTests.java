package com.pluxurydolo.cloudinary.dto;

import org.junit.jupiter.api.Test;

import static com.pluxurydolo.cloudinary.dto.ResourceType.IMAGE;
import static com.pluxurydolo.cloudinary.dto.ResourceType.VIDEO;
import static com.pluxurydolo.cloudinary.dto.ResourceType.fromString;
import static org.assertj.core.api.Assertions.assertThat;

class ResourceTypeTests {

    @Test
    void testFromString() {
        ResourceType image = fromString("image");
        ResourceType video = fromString("video");
        ResourceType empty = fromString("idk");

        assertThat(image)
            .isEqualTo(IMAGE);
        assertThat(video)
            .isEqualTo(VIDEO);
        assertThat(empty)
            .isNull();
    }
}
