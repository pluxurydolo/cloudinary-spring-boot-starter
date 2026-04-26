package com.pluxurydolo.cloudinary.validator;

import com.pluxurydolo.cloudinary.dto.ValidationResult;
import com.pluxurydolo.cloudinary.dto.response.DeleteResponse;
import com.pluxurydolo.cloudinary.dto.response.ResourceResponse;
import com.pluxurydolo.cloudinary.dto.response.UploadResponse;
import com.pluxurydolo.cloudinary.exception.CloudinaryValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static com.pluxurydolo.cloudinary.dto.ValidationResult.FAILURE;
import static com.pluxurydolo.cloudinary.dto.ValidationResult.fromBoolean;
import static java.util.Locale.US;

public class ResponseValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseValidator.class);

    public Mono<UploadResponse> validateUploadResponse(String resourceName, UploadResponse response) {
        String publicId = response.publicId();
        String secureUrl = response.secureUrl();

        boolean publicIdValid = publicId != null && !publicId.isEmpty();
        LOGGER.info("qgco [cloudinary-starter] Результат валидации поля public_id {}: {}", resourceName, publicIdValid);

        boolean secureUrlValid = secureUrl != null && !secureUrl.isEmpty();
        LOGGER.info("zegw [cloudinary-starter] Результат валидации поля secure_url {}: {}", resourceName, secureUrlValid);

        ValidationResult validationResult = fromBoolean(publicIdValid && secureUrlValid);

        if (validationResult == FAILURE) {
            return Mono.error(CloudinaryValidationException::new);
        }

        return Mono.just(response);
    }

    public Mono<ResourceResponse> validateResourceResponse(String resourceName, ResourceResponse response) {
        String publicId = response.publicId();
        String secureUrl = response.secureUrl();

        boolean publicIdValid = publicId != null && !publicId.isEmpty();
        LOGGER.info("flzd [cloudinary-starter] Результат валидации поля public_id {}: {}", resourceName, publicIdValid);

        boolean secureUrlValid = secureUrl != null && !secureUrl.isEmpty();
        LOGGER.info("skkf [cloudinary-starter] Результат валидации поля secure_url {}: {}", resourceName, secureUrlValid);

        ValidationResult validationResult = fromBoolean(publicIdValid && secureUrlValid);

        if (validationResult == FAILURE) {
            return Mono.error(CloudinaryValidationException::new);
        }

        return Mono.just(response);
    }

    public Mono<DeleteResponse> validateDeleteResponse(DeleteResponse response) {
        Optional<String> resultOrNull = Optional.ofNullable(response.result());

        boolean resultValid = resultOrNull.map(result -> result.toLowerCase(US))
            .map(result -> List.of("ok", "deleted").contains(result))
            .orElse(false);

        ValidationResult validationResult = fromBoolean(resultValid);
        LOGGER.error("efrd [cloudinary-starter] Результат валидации поля result: {}", validationResult);

        if (validationResult == FAILURE) {
            return Mono.error(CloudinaryValidationException::new);
        }

        return Mono.just(response);
    }
}
