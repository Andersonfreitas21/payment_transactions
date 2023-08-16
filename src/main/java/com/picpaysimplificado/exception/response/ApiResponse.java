package com.picpaysimplificado.exception.response;

public record ApiResponse(String message, Object data) {
    public static ApiResponse API_RESPONSE_NOT_FOUND =
            new ApiResponse(null, null);
}
