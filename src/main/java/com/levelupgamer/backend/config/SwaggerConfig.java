package com.levelupgamer.backend.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
        .info(new Info().title("API Backend LevelUpGamer - Gestión de Contenido")
        .version("1.0.0")
        .description("Documentación de la API para la gestión de Artículos, Órdenes y Mensajes de Contacto.")
        );
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("API Productos LevelUpGamer")
            .version("1.0.0")
            .description("Documentación de la API de Productos para LevelUpGamer"));
    }

}
