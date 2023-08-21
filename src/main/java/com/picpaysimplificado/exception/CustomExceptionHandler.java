package com.picpaysimplificado.exception;

import com.picpaysimplificado.exception.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidAuthorizationException.class)
    public final ResponseEntity<ApiResponse> handleInvalidAuthenticationException(InvalidAuthorizationException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotificationIsDownException.class)
    public final ResponseEntity<ApiResponse> handleNotificationIsDownException(NotificationIsDownException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
