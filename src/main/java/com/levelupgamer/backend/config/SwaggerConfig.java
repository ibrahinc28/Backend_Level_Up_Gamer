package com.levelupgamer.backend.config;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("API Productos LevelUpGamer")
            .version("1.0.0")
            .description("Documentación de la API de Productos para LevelUpGamer"));
    }

}
