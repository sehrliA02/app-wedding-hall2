package com.example.appweddinghall.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.appweddinghall.exception.MyBadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;

@Configuration
public class JWTProvider {

    @Value("${app.jwt.access.key}")
    private String accessKey;

    @Value("${app.jwt.access.expiration}")
    private long accessExpiration;

    @Value("${app.jwt.refresh.key}")
    private String refreshKey;

    @Value("${app.jwt.refresh.expiration}")
    private long refreshExpiration;
    private static final String MY_ISSUER = "myIssuer";


    public String getSubjectFromAccessToken(String token) {
        return getSubjectFromToken(token, accessKey);
    }

    public String getSubjectFromRefreshToken(String token) {
        return getSubjectFromToken(token, refreshKey);
    }

    public String generateRefreshToken(String subject) {
        return token(subject, refreshKey, refreshExpiration);
    }

    public String generateAccessToken(String subject) {
        return token(subject, accessKey, accessExpiration);
    }

    private String token(String subject, String secret, long expiration) {
        return JWT
                .create()
                .withIssuer(MY_ISSUER)
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date())
                .sign(Algorithm.HMAC256(secret));
    }

    private String getSubjectFromToken(String token, String secret) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(MY_ISSUER).build();

        try {
            DecodedJWT verify = jwtVerifier.verify(token);

            return verify.getSubject();
        } catch (JWTVerificationException e) {
            throw new MyBadRequestException("Token not verified");
        }

    }

    public boolean isExpiredAccessToken(String accessToken) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(accessKey)).withIssuer(MY_ISSUER).build();

        try {
            DecodedJWT verify = jwtVerifier.verify(accessToken);

            if (verify.getExpiresAt().after(new Date()))
                return true;
        } catch (JWTVerificationException ignored) {
        }

        return false;
    }
}
