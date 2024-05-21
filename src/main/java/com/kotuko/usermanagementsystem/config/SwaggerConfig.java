package com.kotuko.usermanagementsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springLegalRemitOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Kotuko API")
                        .description("Kotuko Application")
                        .version("v0.0.1"));
    }
}