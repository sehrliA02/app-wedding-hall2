package com.example.appweddinghall.controller;

import com.example.appweddinghall.payload.*;
import com.example.appweddinghall.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public HttpEntity<ApiResponse<UUID>> register(RegisterDTO registerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDTO));
    }

    @Override
    public HttpEntity<ApiResponse<TokenDTO>> confirm(UUID id, String code) {
        return ResponseEntity.accepted().body(authService.confirm(id, code));
    }

    @Override
    public HttpEntity<ApiResponse<TokenDTO>> login(LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @Override
    public HttpEntity<ApiResponse<TokenDTO>> refreshToken(RefreshTokenDTO refreshTokenDTO) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenDTO));
    }
}
