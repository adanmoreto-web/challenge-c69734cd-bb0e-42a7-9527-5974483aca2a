package com.banco.prestamos.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Préstamos")
                        .version("1.0.0")
                        .description("""
                                Servicio backend para la gestión de solicitudes de préstamos bancarios.
                                
                                Esta API permite crear, consultar y gestionar solicitudes de préstamos
                                con soporte para idempotencia y validación de datos.
                                
                                ## Características principales
                                
                                - Creación de solicitudes de préstamo con validación
                                - Consultas por ID de solicitud o cliente
                                - Soporte de idempotencia para evitar duplicados
                                - Documentación automática con OpenAPI 3.0
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@banco.com")
                                .url("https://www.banco.com"))
                        .license(new License()
                                .name("Licencia Proprietaria")
                                .url("https://www.banco.com/legal")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token de autenticación JWT")));
    }
}