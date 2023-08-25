package com.library.online_library.exeption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends RuntimeException {

    private static final String RESOURCE_NOT_FOUND_MESSAGE = "%s not found: %s";
    private static final String RESOURCE_NOT_FOUND_BY_FIELD_MESSAGE = "%s not found: '%s' = '%s'";

    public ResourceNotFoundException(String resourceName, String fieldName, String value) {
        super(buildMessage(resourceName, fieldName, value));
    }

    public ResourceNotFoundException(String resourceName, Long id) {
        this(resourceName, "id", String.valueOf(id));
    }

    public ResourceNotFoundException(String resourceName, String message) {
        super(buildMessage(resourceName, message));
    }

    private static String buildMessage(String resourceName, String fieldName, String value) {
        resourceName = resourceName.replace("_", " ");
        var message = String.format(RESOURCE_NOT_FOUND_BY_FIELD_MESSAGE, resourceName, fieldName, value);
        log.warn(message);
        return message;
    }

    private static String buildMessage(String resourceName, String message) {
        resourceName = resourceName.replace("_", " ");
        message = String.format(RESOURCE_NOT_FOUND_MESSAGE, resourceName, message);
        log.warn(message);
        return message;
    }

}
