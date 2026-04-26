package com.pluxurydolo.cloudinary.client;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.api.ApiResponse;
import com.pluxurydolo.cloudinary.dto.request.DeleteRequest;
import com.pluxurydolo.cloudinary.dto.request.ResourceRequest;
import com.pluxurydolo.cloudinary.dto.request.UploadRequest;
import com.pluxurydolo.cloudinary.dto.response.DeleteResponse;
import com.pluxurydolo.cloudinary.dto.response.ResourceResponse;
import com.pluxurydolo.cloudinary.dto.response.UploadResponse;
import com.pluxurydolo.cloudinary.exception.CloudinaryDeleteImageException;
import com.pluxurydolo.cloudinary.exception.CloudinaryDeleteVideoException;
import com.pluxurydolo.cloudinary.exception.CloudinaryImageSecureUrlException;
import com.pluxurydolo.cloudinary.exception.CloudinaryUploadImageException;
import com.pluxurydolo.cloudinary.exception.CloudinaryUploadVideoException;
import com.pluxurydolo.cloudinary.exception.CloudinaryValidationException;
import com.pluxurydolo.cloudinary.exception.CloudinaryVideoSecureUrlException;
import com.pluxurydolo.cloudinary.validator.ResponseValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

@ExtendWith(MockitoExtension.class)
class CloudinaryClientTests {

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private ResponseValidator responseValidator;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Uploader uploader;

    @Mock
    private Api api;

    @Mock
    private UploadResponse uploadResponse;

    @Mock
    private ResourceResponse resourceResponse;

    @Mock
    private DeleteResponse deleteResponse;

    @Mock
    private ApiResponse apiResponse;

    @InjectMocks
    private CloudinaryClient cloudinaryClient;

    @Test
    void testUploadImage() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(UploadResponse.class)))
            .thenReturn(uploadResponse);
        when(responseValidator.validateUploadResponse(anyString(), any()))
            .thenReturn(Mono.just(uploadResponse));
        when(uploadResponse.publicId())
            .thenReturn("publicId");

        Mono<String> result = cloudinaryClient.uploadImage(uploadRequest());

        create(result)
            .expectNext("publicId")
            .verifyComplete();
    }

    @Test
    void testUploadImageWhenValidationFailed() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(UploadResponse.class)))
            .thenReturn(uploadResponse);
        when(responseValidator.validateUploadResponse(anyString(), any()))
            .thenReturn(Mono.error(new CloudinaryValidationException()));

        Mono<String> result = cloudinaryClient.uploadImage(uploadRequest());

        create(result)
            .expectError(CloudinaryUploadImageException.class)
            .verify();
    }

    @Test
    void testUploadVideo() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(UploadResponse.class)))
            .thenReturn(uploadResponse);
        when(responseValidator.validateUploadResponse(anyString(), any()))
            .thenReturn(Mono.just(uploadResponse));
        when(uploadResponse.publicId())
            .thenReturn("publicId");

        Mono<String> result = cloudinaryClient.uploadVideo(uploadRequest());

        create(result)
            .expectNext("publicId")
            .verifyComplete();
    }

    @Test
    void testUploadVideoWhenValidationFailed() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(UploadResponse.class)))
            .thenReturn(uploadResponse);
        when(responseValidator.validateUploadResponse(anyString(), any()))
            .thenReturn(Mono.error(new CloudinaryValidationException()));

        Mono<String> result = cloudinaryClient.uploadVideo(uploadRequest());

        create(result)
            .expectError(CloudinaryUploadVideoException.class)
            .verify();
    }

    @Test
    void testGetImageSecureUrl() throws Exception {
        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(apiResponse);
        when(objectMapper.convertValue(any(), eq(ResourceResponse.class)))
            .thenReturn(resourceResponse);
        when(responseValidator.validateResourceResponse(anyString(), any()))
            .thenReturn(Mono.just(resourceResponse));
        when(resourceResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<String> result = cloudinaryClient.getImageSecureUrl(getSecureUrlRequest());

        create(result)
            .expectNext("secureUrl")
            .verifyComplete();
    }

    @Test
    void testGetImageSecureUrlWhenValidationFailed() throws Exception {
        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(apiResponse);
        when(objectMapper.convertValue(any(), eq(ResourceResponse.class)))
            .thenReturn(resourceResponse);
        when(responseValidator.validateResourceResponse(anyString(), any()))
            .thenReturn(Mono.error(new CloudinaryValidationException()));

        Mono<String> result = cloudinaryClient.getImageSecureUrl(getSecureUrlRequest());

        create(result)
            .expectError(CloudinaryImageSecureUrlException.class)
            .verify();
    }

    @Test
    void testGetVideoSecureUrl() throws Exception {
        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(apiResponse);
        when(objectMapper.convertValue(any(), eq(ResourceResponse.class)))
            .thenReturn(resourceResponse);
        when(responseValidator.validateResourceResponse(anyString(), any()))
            .thenReturn(Mono.just(resourceResponse));
        when(resourceResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<String> result = cloudinaryClient.getVideoSecureUrl(getSecureUrlRequest());

        create(result)
            .expectNext("secureUrl")
            .verifyComplete();
    }

    @Test
    void testGetVideoSecureUrlWhenValidationFailed() throws Exception {
        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(apiResponse);
        when(objectMapper.convertValue(any(), eq(ResourceResponse.class)))
            .thenReturn(resourceResponse);
        when(responseValidator.validateResourceResponse(anyString(), any()))
            .thenReturn(Mono.error(new CloudinaryValidationException()));

        Mono<String> result = cloudinaryClient.getVideoSecureUrl(getSecureUrlRequest());

        create(result)
            .expectError(CloudinaryVideoSecureUrlException.class)
            .verify();
    }

    @Test
    void testDeleteImage() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(DeleteResponse.class)))
            .thenReturn(deleteResponse);
        when(responseValidator.validateDeleteResponse(any()))
            .thenReturn(Mono.just(deleteResponse));
        when(deleteResponse.result())
            .thenReturn("ok");

        Mono<String> result = cloudinaryClient.deleteImage(deleteRequest());

        create(result)
            .expectNext("ok")
            .verifyComplete();
    }

    @Test
    void testDeleteImageWhenValidationFailed() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(DeleteResponse.class)))
            .thenReturn(deleteResponse);
        when(responseValidator.validateDeleteResponse(any()))
            .thenReturn(Mono.error(new CloudinaryValidationException()));

        Mono<String> result = cloudinaryClient.deleteImage(deleteRequest());

        create(result)
            .expectError(CloudinaryDeleteImageException.class)
            .verify();
    }

    @Test
    void testDeleteVideo() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(DeleteResponse.class)))
            .thenReturn(deleteResponse);
        when(responseValidator.validateDeleteResponse(any()))
            .thenReturn(Mono.just(deleteResponse));
        when(deleteResponse.result())
            .thenReturn("ok");

        Mono<String> result = cloudinaryClient.deleteVideo(deleteRequest());

        create(result)
            .expectNext("ok")
            .verifyComplete();
    }

    @Test
    void testDeleteVideoWhenValidationFailed() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(DeleteResponse.class)))
            .thenReturn(deleteResponse);
        when(responseValidator.validateDeleteResponse(any()))
            .thenReturn(Mono.error(new CloudinaryValidationException()));

        Mono<String> result = cloudinaryClient.deleteVideo(deleteRequest());

        create(result)
            .expectError(CloudinaryDeleteVideoException.class)
            .verify();
    }

    private static UploadRequest uploadRequest() {
        return new UploadRequest(new byte[]{}, "folder", "name");
    }

    private static ResourceRequest getSecureUrlRequest() {
        return new ResourceRequest("publicId");
    }

    private static DeleteRequest deleteRequest() {
        return new DeleteRequest("publicId");
    }
}
