package com.pluxurydolo.cloudinary.exception;

import static java.lang.String.format;

public class CloudinaryResourceException extends RuntimeException {
    public CloudinaryResourceException(String publicId) {
        super(format("[cloudinary-starter] Произошла ошибка при получении ресурса %s", publicId));
    }
}
