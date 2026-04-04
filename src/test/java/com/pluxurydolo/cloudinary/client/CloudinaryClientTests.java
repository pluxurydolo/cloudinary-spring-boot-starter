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
import com.pluxurydolo.cloudinary.exception.CloudinaryDeleteException;
import com.pluxurydolo.cloudinary.exception.CloudinaryResourceException;
import com.pluxurydolo.cloudinary.exception.CloudinaryUploadException;
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
        when(uploadResponse.isSuccessful())
            .thenReturn(true);
        when(uploadResponse.publicId())
            .thenReturn("publicId");

        Mono<String> result = cloudinaryClient.uploadImage(uploadRequest());

        create(result)
            .expectNext("publicId")
            .verifyComplete();
    }

    @Test
    void testUploadImageWhenResponseIsUnsuccessful() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(UploadResponse.class)))
            .thenReturn(uploadResponse);
        when(uploadResponse.isSuccessful())
            .thenReturn(false);

        Mono<String> result = cloudinaryClient.uploadImage(uploadRequest());

        create(result)
            .expectError(CloudinaryUploadException.class)
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
        when(uploadResponse.isSuccessful())
            .thenReturn(true);
        when(uploadResponse.publicId())
            .thenReturn("publicId");

        Mono<String> result = cloudinaryClient.uploadVideo(uploadRequest());

        create(result)
            .expectNext("publicId")
            .verifyComplete();
    }

    @Test
    void testUploadVideoWhenResponseIsUnsuccessful() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(UploadResponse.class)))
            .thenReturn(uploadResponse);
        when(uploadResponse.isSuccessful())
            .thenReturn(false);

        Mono<String> result = cloudinaryClient.uploadVideo(uploadRequest());

        create(result)
            .expectError(CloudinaryUploadException.class)
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
        when(resourceResponse.isSuccessful())
            .thenReturn(true);
        when(resourceResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<String> result = cloudinaryClient.getImageSecureUrl(getSecureUrlRequest());

        create(result)
            .expectNext("secureUrl")
            .verifyComplete();
    }

    @Test
    void testGetImageSecureUrlWhenResponseIsUnsuccessful() throws Exception {
        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(apiResponse);
        when(objectMapper.convertValue(any(), eq(ResourceResponse.class)))
            .thenReturn(resourceResponse);
        when(resourceResponse.isSuccessful())
            .thenReturn(false);

        Mono<String> result = cloudinaryClient.getImageSecureUrl(getSecureUrlRequest());

        create(result)
            .expectError(CloudinaryResourceException.class)
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
        when(resourceResponse.isSuccessful())
            .thenReturn(true);
        when(resourceResponse.secureUrl())
            .thenReturn("secureUrl");

        Mono<String> result = cloudinaryClient.getVideoSecureUrl(getSecureUrlRequest());

        create(result)
            .expectNext("secureUrl")
            .verifyComplete();
    }

    @Test
    void testGetVideoSecureUrlWhenResponseIsUnsuccessful() throws Exception {
        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(apiResponse);
        when(objectMapper.convertValue(any(), eq(ResourceResponse.class)))
            .thenReturn(resourceResponse);
        when(resourceResponse.isSuccessful())
            .thenReturn(false);

        Mono<String> result = cloudinaryClient.getVideoSecureUrl(getSecureUrlRequest());

        create(result)
            .expectError(CloudinaryResourceException.class)
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
        when(deleteResponse.isSuccessful())
            .thenReturn(true);
        when(deleteResponse.result())
            .thenReturn("ok");

        Mono<String> result = cloudinaryClient.deleteImage(deleteRequest());

        create(result)
            .expectNext("ok")
            .verifyComplete();
    }

    @Test
    void testDeleteImageWhenResponseIsUnsuccessful() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(DeleteResponse.class)))
            .thenReturn(deleteResponse);
        when(deleteResponse.isSuccessful())
            .thenReturn(false);

        Mono<String> result = cloudinaryClient.deleteImage(deleteRequest());

        create(result)
            .expectError(CloudinaryDeleteException.class)
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
        when(deleteResponse.isSuccessful())
            .thenReturn(true);
        when(deleteResponse.result())
            .thenReturn("ok");

        Mono<String> result = cloudinaryClient.deleteVideo(deleteRequest());

        create(result)
            .expectNext("ok")
            .verifyComplete();
    }

    @Test
    void testDeleteVideoWhenResponseIsUnsuccessful() throws IOException {
        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(Map.of());
        when(objectMapper.convertValue(any(), eq(DeleteResponse.class)))
            .thenReturn(deleteResponse);
        when(deleteResponse.isSuccessful())
            .thenReturn(false);

        Mono<String> result = cloudinaryClient.deleteVideo(deleteRequest());

        create(result)
            .expectError(CloudinaryDeleteException.class)
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
