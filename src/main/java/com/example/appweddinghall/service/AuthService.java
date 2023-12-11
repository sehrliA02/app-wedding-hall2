package com.example.appweddinghall.service;

import com.example.appweddinghall.payload.*;

public interface AuthService {

    ApiResponse<String> register(RegisterDTO registerDTO);

    ApiResponse<TokenDTO> confirm(SmsDTO smsDTO);

    ApiResponse<TokenDTO> login(LoginDTO loginDTO);

    ApiResponse<TokenDTO> refreshToken(RefreshTokenDTO refreshTokenDTO);
}
