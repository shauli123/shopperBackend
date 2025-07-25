package com.example.shopperBackend.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
    public class SwaggerConfig {
        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .group("public")
                    .pathsToMatch("/**")
                    .build();
        }
    }

