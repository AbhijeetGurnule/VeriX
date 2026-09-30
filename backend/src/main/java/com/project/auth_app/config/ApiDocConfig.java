package com.project.auth_app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "VeriX build by Abhijeet Gurnule",
                description = "Generic authentication application that can be used with any application",
                contact = @Contact(
                        name = "Abhijeet Gurnule",
                        url = "https://verix.onrender.com",
                        email = "support@verix.com"
                ),
                version = "1.0",
                summary = "This app is very useful if you don't want to create auth app from scratch."
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer", // ex. Authorization: Bearer <token>
        bearerFormat = "JWT"
)
public class ApiDocConfig {

}
