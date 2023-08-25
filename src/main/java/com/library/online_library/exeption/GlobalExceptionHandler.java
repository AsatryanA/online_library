package com.library.online_library.exeption;

import com.library.online_library.utils.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> handleRefreshTokenNotFoundException(HttpServletRequest req, RefreshTokenNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.FORBIDDEN, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleResourceNotFoundException(HttpServletRequest req, ResourceNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(CommonValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleCommonValidationException(HttpServletRequest req, CommonValidationException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleCommonValidationException(HttpServletRequest req, DataIntegrityViolationException e) {
        logError(req, e);
        var message = beautifyDuplicateMessage(e);
        return buildResponse(HttpStatus.CONFLICT, message, req.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, prettifyMessage(e), req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        logError(req, e);
        var errors = e.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> isNull(fieldError.getDefaultMessage()) ? "wrong value type" : fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.value(), req.getRequestURI(), errors));
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMultipartException(HttpServletRequest req, MultipartException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, prettifyMessage(e), req.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public void handleAccessDeniedException(HttpServletRequest req, AccessDeniedException e) {
        logError(req, e);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleRuntimeException(HttpServletRequest req, RuntimeException e) {
        logError(req, e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", req.getRequestURI());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleBadCredentialsException(HttpServletRequest req, RuntimeException e) {
        logError(req, e);
        return buildResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), req.getRequestURI());
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus httpCode, String message, String requestURI) {
        var errors = new HashMap<String, String>();
        errors.put("message", message);
        var apiError = new ApiError(httpCode.value(), requestURI, errors);
        return ResponseEntity.status(httpCode).body(apiError);
    }

    private void logError(HttpServletRequest req, Exception e) {
        log.error(e.getMessage());
        log.error("RequestURI {}", req.getRequestURI());
        e.printStackTrace();
    }

    private String beautifyDuplicateMessage(DataIntegrityViolationException e) {
        return e.getCause().getCause().getMessage().split("ERROR:")[1].split(" of")[0];
    }

    private String prettifyMessage(Exception e) {
        return nonNull(e.getMessage())
                ? e.getMessage().substring(e.getMessage().indexOf('.') + 1)
                : null;
    }

}
