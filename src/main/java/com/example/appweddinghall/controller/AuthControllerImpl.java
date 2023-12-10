package com.example.appweddinghall.controller;

import com.example.appweddinghall.exception.VerifyException;
import com.example.appweddinghall.payload.*;
import com.example.appweddinghall.service.AuthService;
import com.example.appweddinghall.service.SmsService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final SmsService smsService;

    @Override
    public HttpEntity<ApiResponse<String>> register(RegisterDTO registerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDTO));
    }

    @Override
    public ResponseEntity<ApiResponse<TokenDTO>> confirm(@Valid SmsDTO smsDTO) {
        try {
            if (smsService.isMessageMatching(smsDTO)) {
                return ResponseEntity.accepted().body(authService.confirm(smsDTO));
            } else return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("Failed verification",new TokenDTO(null,null),false));
        } catch (VerifyException e) {
            throw new RuntimeException(e);
        }
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
