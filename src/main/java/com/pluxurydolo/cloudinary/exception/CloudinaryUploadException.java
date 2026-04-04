package com.pluxurydolo.cloudinary.exception;

import static java.lang.String.format;

public class CloudinaryUploadException extends RuntimeException {
    public CloudinaryUploadException(String name) {
        super(format("[cloudinary-starter] Произошла ошибка при загрузке ресурса %s", name));
    }
}
