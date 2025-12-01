package com.levelupgamer.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Backend LevelUpGamer - Gestión de Contenido y Compras") 
                .version("1.0.0")
                .description("Documentación de la API para la gestión de Artículos (Blog), Carrito de Compras, Órdenes y Mensajes de Contacto. Permite la administración completa del sitio web.")
            );
    }
}