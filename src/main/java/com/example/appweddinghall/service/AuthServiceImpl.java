package com.example.appweddinghall.service;

import com.example.appweddinghall.entity.User;
import com.example.appweddinghall.exception.MyBadRequestException;
import com.example.appweddinghall.exception.MyConflictException;
import com.example.appweddinghall.exception.MyNotFoundException;
import com.example.appweddinghall.mapper.UserMapper;
import com.example.appweddinghall.payload.*;
import com.example.appweddinghall.repository.RoleRepository;
import com.example.appweddinghall.repository.UserRepository;
import com.example.appweddinghall.security.JWTProvider;
import com.example.appweddinghall.security.Principle;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record AuthServiceImpl(UserRepository userRepository,
                              UserMapper userMapper,
                              PasswordEncoder passwordEncoder,
                              RoleRepository roleRepository,
                              JWTProvider jwtProvider,
                              CodeGenerateService codeGenerateService
        /*SenderService senderService*/) implements AuthService, UserDetailsService {

    @Override
    public ApiResponse<UUID> register(RegisterDTO registerDTO) {

        if (registerDTO.password().equals(registerDTO.prePassword()))
            throw new MyBadRequestException("Passwords are not equals");

        if (userRepository.existsByPhone(registerDTO.phone()))
            throw new MyConflictException("This phone already registered!");

        User user = userMapper.toUserFromRegisterDTO(registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(roleRepository.findByName("USER").orElseThrow()));
        user.setCode(codeGenerateService.generate());
        user.setEnabled(false);

        userRepository.save(user);

        // todo :: send sms to phone number

        return ApiResponse.success(user.getId(), "User saved successfully");
    }

    @Override
    public ApiResponse<TokenDTO> confirm(UUID id, String code) {
        User user = userRepository.findById(id).orElseThrow(() -> new MyNotFoundException("User not found by id"));

        if (user.isEnabled())
            throw new MyBadRequestException("User already confirmed!");

        if (!user.getCode().equals(code))
            throw new MyBadRequestException("Code is wrong!");

        user.setEnabled(true);
        userRepository.save(user);

        String accessToken = jwtProvider.generateAccessToken(user.getId().toString());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId().toString());

        return ApiResponse.success(TokenDTO.instance(accessToken, refreshToken));
    }

    @Override
    public ApiResponse<TokenDTO> login(LoginDTO loginDTO) {
        User user = userRepository.findByPhone(loginDTO.phone()).orElseThrow(() -> new MyNotFoundException("User not found by phone number"));

        if (passwordEncoder.matches(loginDTO.password(), user.getPassword()))
            throw new MyBadRequestException("Password is wrong!");

        String accessToken = jwtProvider.generateAccessToken(user.getId().toString());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId().toString());

        return ApiResponse.success(TokenDTO.instance(accessToken, refreshToken));
    }

    @Override
    public ApiResponse<TokenDTO> refreshToken(RefreshTokenDTO refreshTokenDTO) {
        String subjectFromAccessToken = jwtProvider.getSubjectFromAccessToken(refreshTokenDTO.accessToken());
        String subjectFromRefreshToken = jwtProvider.getSubjectFromRefreshToken(refreshTokenDTO.refreshToken());

        if (!subjectFromAccessToken.equals(subjectFromRefreshToken))
            throw new MyBadRequestException("Tokens are not match");

        if (jwtProvider.isExpiredAccessToken(refreshTokenDTO.accessToken()))
            throw new MyBadRequestException("Access token not expired yet!");

        String accessToken = jwtProvider.generateAccessToken(subjectFromAccessToken);
        String refreshToken = jwtProvider.generateRefreshToken(subjectFromAccessToken);

        return ApiResponse.success(TokenDTO.instance(accessToken, refreshToken));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(username).orElseThrow(() -> new UsernameNotFoundException("User not found by phone number"));
        return new Principle(user);
    }
}
