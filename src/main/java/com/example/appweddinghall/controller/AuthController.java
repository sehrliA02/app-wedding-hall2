package com.example.appweddinghall.controller;

import com.example.appweddinghall.payload.*;
import com.example.appweddinghall.utils.AppPathUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.appweddinghall.controller.AuthController.Path.*;

@RequestMapping(AuthController.Path.BASE)
public interface AuthController {
    interface Path {
        String BASE = AppPathUtil.BASE + "/auth";
        String REGISTER = "/register";
        String CONFIRM = "/confirm";
        String LOGIN = "/login";
        String REFRESH_TOKEN = "/refresh-token";
    }

    @PostMapping(REGISTER)
    HttpEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterDTO registerDTO);

    @PatchMapping(CONFIRM)
    ResponseEntity<ApiResponse<TokenDTO>> confirm(@RequestBody @Valid SmsDTO smsDTO);


    @PostMapping(LOGIN)
    HttpEntity<ApiResponse<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO);

    @GetMapping(REFRESH_TOKEN)
    HttpEntity<ApiResponse<TokenDTO>> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO);
}
