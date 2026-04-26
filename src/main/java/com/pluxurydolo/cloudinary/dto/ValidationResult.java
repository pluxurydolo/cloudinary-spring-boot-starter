package com.pluxurydolo.cloudinary.dto;

public enum ValidationResult {
    SUCCESS,
    FAILURE;

    public static ValidationResult fromBoolean(boolean value) {
        if (value) {
            return SUCCESS;
        }

        return FAILURE;
    }
}
