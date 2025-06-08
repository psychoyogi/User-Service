package com.psychoyogi.User.Service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")                      // allow all origins
                .allowedMethods("*")                      // allow all HTTP methods
                .allowedHeaders("*")                      // allow all headers
                .allowCredentials(false);                 // disable credentials to avoid conflicts with "*"
    }

}