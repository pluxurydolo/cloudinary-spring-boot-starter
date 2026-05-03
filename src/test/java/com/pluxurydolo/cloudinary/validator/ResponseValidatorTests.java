package com.pluxurydolo.cloudinary.validator;

import com.pluxurydolo.cloudinary.dto.response.DeleteResponse;
import com.pluxurydolo.cloudinary.dto.response.ResourceResponse;
import com.pluxurydolo.cloudinary.dto.response.UploadResponse;
import com.pluxurydolo.cloudinary.exception.CloudinaryValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

@ExtendWith(MockitoExtension.class)
class ResponseValidatorTests {
    private static final ResponseValidator VALIDATOR = new ResponseValidator();

    @Mock
    private UploadResponse uploadResponse;

    @Mock
    private ResourceResponse resourceResponse;

    @Mock
    private DeleteResponse deleteResponse;

    @Test
    void testValidateUploadResponse() {
        when(uploadResponse.publicId())
            .thenReturn("publicId");
        when(uploadResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<UploadResponse> result = VALIDATOR.validateUploadResponse("resourceName", uploadResponse);

        create(result)
            .expectNext(uploadResponse)
            .verifyComplete();
    }

    @Test
    void testValidateUploadResponseWhenPublicIdIsEmpty() {
        when(uploadResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<UploadResponse> result = VALIDATOR.validateUploadResponse("resourceName", uploadResponse);

        create(result)
            .verifyErrorMatches(throwable -> throwable.getClass().equals(CloudinaryValidationException.class));
    }

    @Test
    void testValidateUploadResponseWhenSecureUrlIsEmpty() {
        when(uploadResponse.publicId())
            .thenReturn("publicId");

        Mono<UploadResponse> result = VALIDATOR.validateUploadResponse("resourceName", uploadResponse);

        create(result)
            .verifyErrorMatches(throwable -> throwable.getClass().equals(CloudinaryValidationException.class));
    }

    @Test
    void testValidateResourceResponse() {
        when(resourceResponse.publicId())
            .thenReturn("publicId");
        when(resourceResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<ResourceResponse> result = VALIDATOR.validateResourceResponse("resourceName", resourceResponse);

        create(result)
            .expectNext(resourceResponse)
            .verifyComplete();
    }

    @Test
    void testValidateResourceResponseWhenPublicIdIsEmpty() {
        when(resourceResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<ResourceResponse> result = VALIDATOR.validateResourceResponse("resourceName", resourceResponse);

        create(result)
            .verifyErrorMatches(throwable -> throwable.getClass().equals(CloudinaryValidationException.class));
    }

    @Test
    void testValidateResourceResponseWhenSecureUrlIsEmpty() {
        when(resourceResponse.publicId())
            .thenReturn("publicId");

        Mono<ResourceResponse> result = VALIDATOR.validateResourceResponse("resourceName", resourceResponse);

        create(result)
            .verifyErrorMatches(throwable -> throwable.getClass().equals(CloudinaryValidationException.class));
    }

    @Test
    void testValidateDeleteResponse() {
        when(deleteResponse.result())
            .thenReturn("ok");

        Mono<DeleteResponse> result = VALIDATOR.validateDeleteResponse(deleteResponse);

        create(result)
            .expectNext(deleteResponse)
            .verifyComplete();
    }

    @Test
    void testValidateDeleteResponseWhenResultIsEmpty() {
        Mono<DeleteResponse> result = VALIDATOR.validateDeleteResponse(deleteResponse);

        create(result)
            .verifyErrorMatches(throwable -> throwable.getClass().equals(CloudinaryValidationException.class));
    }

    @Test
    void testValidateDeleteResponseWhenResultIsInvalid() {
        when(deleteResponse.result())
            .thenReturn("idk");

        Mono<DeleteResponse> result = VALIDATOR.validateDeleteResponse(deleteResponse);

        create(result)
            .verifyErrorMatches(throwable -> throwable.getClass().equals(CloudinaryValidationException.class));
    }
}
