package com.example.appweddinghall.payload;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDTO(@NotBlank String accessToken,
                              @NotBlank String refreshToken) {
}
