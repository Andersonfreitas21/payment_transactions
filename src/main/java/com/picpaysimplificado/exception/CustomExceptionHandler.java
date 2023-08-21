package com.picpaysimplificado.exception;

import com.picpaysimplificado.exception.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ApiResponse> handleResourceExistsException(DataIntegrityViolationException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidAuthenticationException.class)
    public final ResponseEntity<ApiResponse> handleInvalidAuthenticationException(InvalidAuthenticationException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotificationIsDownException.class)
    public final ResponseEntity<ApiResponse> handleNotificationIsDownException(NotificationIsDownException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ValueMismatchException.class)
    public final ResponseEntity<ApiResponse> handleValueMismatchException(ValueMismatchException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
