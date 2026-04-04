package com.pluxurydolo.cloudinary.dto.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteResponseTests {

    @Test
    void testIsSuccessful() {
        boolean okResult = deleteResponse("ok").isSuccessful();
        boolean deletedResult = deleteResponse("deleted").isSuccessful();
        boolean doneResult = deleteResponse("done").isSuccessful();
        boolean nullResult = deleteResponse(null).isSuccessful();

        assertThat(okResult)
            .isTrue();
        assertThat(deletedResult)
            .isTrue();
        assertThat(doneResult)
            .isFalse();
        assertThat(nullResult)
            .isFalse();
    }

    private static DeleteResponse deleteResponse(String result) {
        return new DeleteResponse(result, "message", 1, true);
    }
}
