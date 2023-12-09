package com.example.appweddinghall.controller;

import com.example.appweddinghall.payload.*;
import com.example.appweddinghall.utils.AppPathUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.appweddinghall.controller.AuthController.Path.*;

@RequestMapping(AuthController.Path.BASE)
public interface AuthController {
    interface Path {
        String BASE = AppPathUtil.BASE + "/auth";
        String REGISTER = "/register";
        String CONFIRM = "/confirm/{id}/{code}";
        String LOGIN = "/login";
        String REFRESH_TOKEN = "/refresh-token";
    }

    @PostMapping(REGISTER)
    HttpEntity<ApiResponse<UUID>> register(@Valid @RequestBody RegisterDTO registerDTO);

    @PatchMapping(CONFIRM)
    HttpEntity<ApiResponse<TokenDTO>> confirm(@PathVariable UUID id,
                                              @PathVariable String code);
    @PostMapping(LOGIN)
    HttpEntity<ApiResponse<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO);

    @GetMapping(REFRESH_TOKEN)
    HttpEntity<ApiResponse<TokenDTO>> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO);
}
