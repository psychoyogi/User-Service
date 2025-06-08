package com.psychoyogi.User.Service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Directory API")
                        .version("1.0.0")
                        .description("RESTful API for user directory management with search capabilities")
                        .contact(new Contact()
                                .name("Development Team")
                                .email("dev@example.com")));
    }
}