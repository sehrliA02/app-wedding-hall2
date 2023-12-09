package com.example.appweddinghall.payload;

import java.util.List;

public record ApiResponse<T>(String message, T data, boolean success) {
    public static <S> ApiResponse<S> success(S data) {
        return success(data, null);
    }

    public static <S> ApiResponse<S> success(S data, String msg) {
        return new ApiResponse<>(msg, data, true);
    }

    public static ApiResponse<List<ErrorDTO>> failed(List<ErrorDTO> errors) {
        return failed(errors, null);
    }

    public static ApiResponse<List<ErrorDTO>> failed(List<ErrorDTO> errors, String msg) {
        return new ApiResponse<>(msg, errors, false);
    }
}
