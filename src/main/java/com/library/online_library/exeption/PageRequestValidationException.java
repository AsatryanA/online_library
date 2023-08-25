package com.library.online_library.exeption;

public class PageRequestValidationException extends RuntimeException {
    public PageRequestValidationException(String message) {
        super(message);
    }
}
