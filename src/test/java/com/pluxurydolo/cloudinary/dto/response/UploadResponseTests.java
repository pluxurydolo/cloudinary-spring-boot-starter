package com.pluxurydolo.cloudinary.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.pluxurydolo.cloudinary.dto.ResourceType.IMAGE;
import static org.assertj.core.api.Assertions.assertThat;

class UploadResponseTests {

    @Test
    void testIsSuccessful() {
        boolean validResult = uploadResponse("publicId", "secureUrl").isSuccessful();
        boolean publicIdIsNullResult = uploadResponse(null, "secureUrl").isSuccessful();
        boolean publicIdIsEmptyResult = uploadResponse("", "secureUrl").isSuccessful();
        boolean secureUrlIsNullResult = uploadResponse("publicId", null).isSuccessful();
        boolean secureUrlIsEmptyResult = uploadResponse("publicId", "").isSuccessful();

        assertThat(validResult)
            .isTrue();
        assertThat(publicIdIsNullResult)
            .isFalse();
        assertThat(publicIdIsEmptyResult)
            .isFalse();
        assertThat(secureUrlIsNullResult)
            .isFalse();
        assertThat(secureUrlIsEmptyResult)
            .isFalse();
    }

    private static UploadResponse uploadResponse(String publicId, String secureUrl) {
        return new UploadResponse(
            "assetId",
            publicId,
            1L,
            "versionId",
            "singature",
            1,
            1,
            "format",
            IMAGE,
            "createdAt",
            List.of(),
            1L,
            "type",
            "etag",
            true,
            "url",
            secureUrl,
            "originalFilename",
            Map.of(),
            "folder"
        );
    }
}
