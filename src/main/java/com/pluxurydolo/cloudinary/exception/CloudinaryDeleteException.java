package com.pluxurydolo.cloudinary.exception;

import static java.lang.String.format;

public class CloudinaryDeleteException extends RuntimeException {
    public CloudinaryDeleteException(String publicId) {
        super(format("[cloudinary-starter] Произошла ошибка при удалении %s", publicId));
    }
}
