package com.pluxurydolo.cloudinary.client;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.pluxurydolo.cloudinary.dto.ResourceType;
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
import com.pluxurydolo.cloudinary.exception.CloudinaryVideoSecureUrlException;
import com.pluxurydolo.cloudinary.validator.ResponseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

import static com.pluxurydolo.cloudinary.dto.ResourceType.IMAGE;
import static com.pluxurydolo.cloudinary.dto.ResourceType.VIDEO;

public class CloudinaryClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudinaryClient.class);

    private final Cloudinary cloudinary;
    private final ResponseValidator responseValidator;
    private final ObjectMapper objectMapper;

    public CloudinaryClient(Cloudinary cloudinary, ResponseValidator responseValidator, ObjectMapper objectMapper) {
        this.cloudinary = cloudinary;
        this.responseValidator = responseValidator;
        this.objectMapper = objectMapper;
    }

    public Mono<String> uploadImage(UploadRequest request) {
        return uploadMedia(request, IMAGE)
            .map(UploadResponse::publicId)
            .doOnSuccess(_ -> LOGGER.info("apjz [cloudinary-starter] Изображение успешно загружено"))
            .onErrorResume(throwable -> {
                LOGGER.error("hzrd [cloudinary-starter] Произошла ошибка при загрузке изображения");
                return Mono.error(new CloudinaryUploadImageException(throwable));
            });
    }

    public Mono<String> uploadVideo(UploadRequest request) {
        return uploadMedia(request, VIDEO)
            .map(UploadResponse::publicId)
            .doOnSuccess(_ -> LOGGER.info("ehrv [cloudinary-starter] Видео успешно загружено"))
            .onErrorResume(throwable -> {
                LOGGER.error("dzra [cloudinary-starter] Произошла ошибка при загрузке видео");
                return Mono.error(new CloudinaryUploadVideoException(throwable));
            });
    }

    public Mono<String> getImageSecureUrl(ResourceRequest request) {
        return mediaSecureUrl(request, IMAGE)
            .doOnSuccess(_ -> LOGGER.info("ohlb [cloudinary-starter] secure_url для изображения успешно получен"))
            .onErrorResume(throwable -> {
                LOGGER.error("zynp [cloudinary-starter] Произошла ошибка при получении secure_url для изображения");
                return Mono.error(new CloudinaryImageSecureUrlException(throwable));
            });
    }

    public Mono<String> getVideoSecureUrl(ResourceRequest request) {
        return mediaSecureUrl(request, VIDEO)
            .doOnSuccess(_ -> LOGGER.info("bnvs [cloudinary-starter] secure_url для видео успешно получен"))
            .onErrorResume(throwable -> {
                LOGGER.error("jvsp [cloudinary-starter] Произошла ошибка при получении secure_url для видео");
                return Mono.error(new CloudinaryVideoSecureUrlException(throwable));
            });
    }

    public Mono<String> deleteImage(DeleteRequest request) {
        return deleteMedia(request, IMAGE)
            .map(DeleteResponse::result)
            .doOnSuccess(_ -> LOGGER.info("zgts [cloudinary-starter] Изображение успешно удалено"))
            .onErrorResume(throwable -> {
                LOGGER.error("nxpf [cloudinary-starter] Произошла ошибка при удалении изображения");
                return Mono.error(new CloudinaryDeleteImageException(throwable));
            });
    }

    public Mono<String> deleteVideo(DeleteRequest request) {
        return deleteMedia(request, VIDEO)
            .map(DeleteResponse::result)
            .doOnSuccess(_ -> LOGGER.info("ztrf [cloudinary-starter] Видео успешно удалено"))
            .onErrorResume(throwable -> {
                LOGGER.error("cfpy [cloudinary-starter] Произошла ошибка при удалении видео");
                return Mono.error(new CloudinaryDeleteVideoException(throwable));
            });
    }

    private Mono<UploadResponse> uploadMedia(UploadRequest request, ResourceType resourceType) {
        Uploader uploader = cloudinary.uploader();

        byte[] bytes = request.bytes();
        String folder = request.folder();
        String name = request.name();
        String type = resourceType.type();

        Map<String, String> requestParams = Map.of(
            "folder", folder,
            "resource_type", type,
            "public_id", name
        );

        return Mono.fromCallable(() -> uploader.upload(bytes, requestParams))
            .map(response -> objectMapper.convertValue(response, UploadResponse.class))
            .flatMap(response -> responseValidator.validateUploadResponse(name, response))
            .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<String> mediaSecureUrl(ResourceRequest request, ResourceType resourceType) {
        Api api = cloudinary.api();
        String publicId = request.publicId();
        String type = resourceType.type();

        Map<String, String> requestParams = Map.of("resource_type", type);

        return Mono.fromCallable(() -> api.resource(publicId, requestParams))
            .map(response -> objectMapper.convertValue(response, ResourceResponse.class))
            .flatMap(response -> responseValidator.validateResourceResponse(publicId, response))
            .map(ResourceResponse::secureUrl)
            .subscribeOn(Schedulers.boundedElastic());

    }

    private Mono<DeleteResponse> deleteMedia(DeleteRequest request, ResourceType resourceType) {
        Uploader uploader = cloudinary.uploader();
        String publicId = request.publicId();
        String type = resourceType.type();

        Map<String, Object> requestParams = Map.of(
            "invalidate", true,
            "resource_type", type
        );

        return Mono.fromCallable(() -> uploader.destroy(publicId, requestParams))
            .map(response -> objectMapper.convertValue(response, DeleteResponse.class))
            .flatMap(responseValidator::validateDeleteResponse)
            .subscribeOn(Schedulers.boundedElastic());
    }
}
