package com.pluxurydolo.cloudinary.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.pluxurydolo.cloudinary.dto.ResourceType.IMAGE;
import static org.assertj.core.api.Assertions.assertThat;

class ResourceResponseTests {

    @Test
    void testIsSuccessful() {
        boolean validResult = resourceResponse("publicId", "secureUrl").isSuccessful();
        boolean publicIdIsNullResult = resourceResponse(null, "secureUrl").isSuccessful();
        boolean publicIdIsEmptyResult = resourceResponse("", "secureUrl").isSuccessful();
        boolean secureUrlIsNullResult = resourceResponse("publicId", null).isSuccessful();
        boolean secureUrlIsEmptyResult = resourceResponse("publicId", "").isSuccessful();

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

    private static ResourceResponse resourceResponse(String publicId, String secureUrl) {
        return new ResourceResponse(
            "assetId",
            publicId,
            1L,
            "versionId",
            "signature",
            1,
            1,
            "format",
            IMAGE,
            "createdAt",
            List.of(),
            1L,
            "type",
            "etag",
            "url",
            secureUrl,
            "backupUrl",
            Map.of(),
            Map.of(),
            Map.of(),
            List.of(),
            List.of(),
            "accessMode",
            new Object(),
            true,
            Map.of(),
            "originalFilename",
            "folder",
            Map.of()
        );
    }
}
