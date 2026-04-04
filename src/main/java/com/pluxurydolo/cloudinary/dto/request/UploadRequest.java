package com.pluxurydolo.cloudinary.dto.request;

public record UploadRequest(
    byte[] bytes,
    String folder,
    String name
) {
}
