package com.example.appweddinghall.security;

import com.example.appweddinghall.controller.AuthController;
import com.example.appweddinghall.repository.UserRepository;
import com.example.appweddinghall.utils.AppConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (!request.getRequestURI().startsWith(AuthController.Path.BASE)) {
            String authorization = request.getHeader(AppConstant.AUTH_HEADER);

            if (authorization != null && authorization.startsWith(AppConstant.AUTH_TYPE)) {

                String token = authorization.substring(AppConstant.AUTH_TYPE.length());
                UUID userId = UUID.fromString(jwtProvider.getSubjectFromAccessToken(token));

                userRepository.findById(userId).ifPresent(user -> {
                    Principle principal = new Principle(user);

                    var authentication = new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            principal.getAuthorities()
                    );

                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            }
        }

        filterChain.doFilter(request, response);
    }
}
