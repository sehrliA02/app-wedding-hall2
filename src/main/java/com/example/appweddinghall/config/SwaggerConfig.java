package com.example.appweddinghall.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@SecuritySchemes(value = {
        @SecurityScheme(
                name = "bearerAuth",
                description = "Tokenjon",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer",
                in = SecuritySchemeIn.HEADER
        )
})
@OpenAPIDefinition(security = {
        @SecurityRequirement(name = "bearerAuth"),
        @SecurityRequirement(name = "basicAuth")
})
@Configuration
public class SwaggerConfig {

}
