package com.banco.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para el servicio de préstamos.
 * <p>
 * Esta aplicación sigue una arquitectura hexagonal/clean con separación clara entre:
 * <ul>
 *   <li>Dominio: lógica de negocio pura (paquete domain)</li>
 *   <li>Aplicación: casos de uso (paquete application)</li>
 *   <li>Infraestructura: adaptadores para persistencia, API REST, etc. (paquete infrastructure)</li>
 * </ul>
 * <p>
 * La aplicación es idempotente respecto al número de solicitud de préstamo y garantiza consistencia
 * en la persistencia mediante el uso de transacciones y validaciones.
 */
@SpringBootApplication
public class PrestamosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }
}