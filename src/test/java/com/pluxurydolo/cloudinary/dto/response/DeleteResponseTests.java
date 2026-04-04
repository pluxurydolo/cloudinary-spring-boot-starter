package com.pluxurydolo.cloudinary.dto.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteResponseTests {

    @Test
    void testIsSuccessful() {
        DeleteResponse okResult = deleteResponse("ok");
        DeleteResponse deletedResult = deleteResponse("deleted");
        DeleteResponse doneResult = deleteResponse("done");
        DeleteResponse nullResult = deleteResponse(null);

        assertThat(okResult.isSuccessful())
            .isTrue();
        assertThat(deletedResult.isSuccessful())
            .isTrue();
        assertThat(doneResult.isSuccessful())
            .isFalse();
        assertThat(nullResult.isSuccessful())
            .isFalse();
    }

    private static DeleteResponse deleteResponse(String result) {
        return new DeleteResponse(result, "message", 1, true);
    }
}
