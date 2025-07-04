package com.swagger.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Management API")
                        .version("1.0")
                        .description("A simple RESTful application with CRUD operations for Customer data using OpenAPI and Swagger UI."));
    }
}
