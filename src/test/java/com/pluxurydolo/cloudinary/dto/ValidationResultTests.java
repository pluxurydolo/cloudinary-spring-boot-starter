package com.pluxurydolo.cloudinary.dto;

import org.junit.jupiter.api.Test;

import static com.pluxurydolo.cloudinary.dto.ValidationResult.FAILURE;
import static com.pluxurydolo.cloudinary.dto.ValidationResult.SUCCESS;
import static com.pluxurydolo.cloudinary.dto.ValidationResult.fromBoolean;
import static org.assertj.core.api.Assertions.assertThat;

class ValidationResultTests {

    @Test
    void testFromBoolean() {
        ValidationResult success = fromBoolean(true);
        ValidationResult failure = fromBoolean(false);

        assertThat(success)
            .isEqualTo(SUCCESS);
        assertThat(failure)
            .isEqualTo(FAILURE);
    }
}
