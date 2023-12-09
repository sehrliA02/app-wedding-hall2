package com.example.appweddinghall.service;

import com.example.appweddinghall.payload.*;

import java.util.UUID;

public interface AuthService {

    ApiResponse<UUID> register(RegisterDTO registerDTO);

    ApiResponse<TokenDTO> confirm(UUID id, String code);

    ApiResponse<TokenDTO> login(LoginDTO loginDTO);

    ApiResponse<TokenDTO> refreshToken(RefreshTokenDTO refreshTokenDTO);
}
