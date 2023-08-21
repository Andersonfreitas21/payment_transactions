package com.picpaysimplificado.exception;

public class NotificationIsDownException extends RuntimeException {
    public NotificationIsDownException(String message) {
        super(message);
    }
}
