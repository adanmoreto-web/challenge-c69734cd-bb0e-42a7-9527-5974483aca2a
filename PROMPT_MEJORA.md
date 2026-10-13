# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/prestamos/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/prestamos/infrastructure/exceptions/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banco/prestamos/infrastructure/controllers/LoanRequestControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/prestamos/infrastructure/adapters/JpaLoanRequestRepository.java` — `LoanRequest.requestId`: Se invoca `requestId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java` — `IdempotencyRecord.createdAt`: Se invoca `createdAt` sobre `IdempotencyRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java` — `IdempotencyRecord.ttlSeconds`: Se invoca `ttlSeconds` sobre `IdempotencyRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.requestId`: Se invoca `requestId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.customerId`: Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.amount`: Se invoca `amount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.termMonths`: Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.purpose`: Se invoca `purpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.status`: Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.rejectionReason`: Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.createdAt`: Se invoca `createdAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.updatedAt`: Se invoca `updatedAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.customerId`: Se invoca `customerId` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.amount`: Se invoca `amount` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.termMonths`: Se invoca `termMonths` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.purpose`: Se invoca `purpose` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequestRepository.findByCustomerId`: Se invoca `findByCustomerId` sobre `LoanRequestRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.customerId`: Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.amount`: Se invoca `amount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.termMonths`: Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.status`: Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequestAlreadyExistsException.getMessage`: Se invoca `getMessage` sobre `LoanRequestAlreadyExistsException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).

### Misión / candidato
Candidato con experiencia avanzada en desarrollo backend, enfocado en mejora continua de calidad de código.

### Reto
- Tema: aplicación de código limpio y eficiente
- Seniority: advanced-l2
- Tipo: practical
- Título: Mejora de calidad de código en un servicio de préstamos
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Revisión de código existente — objetivo: Identificar áreas de mejora en el código actual — entregable (NO resolver): Informe de revisión de código con áreas de mejora identificadas.
- Fase 2: Aplicación de mejoras — objetivo: Refactorizar el código para aplicar los principios de código limpio — entregable (NO resolver): Código refactorizado con documentación de cambios y justificaciones.
- Fase 3: Verificación de mejoras — objetivo: Verificar que las mejoras aplicadas cumplen con los criterios de calidad de código — entregable (NO resolver): Informe de verificación con resultados de pruebas y cumplimiento de principios.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.banco</groupId>
    <artifactId>prestamos</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>prestamos</name>
    <description>Servicio de gestión de préstamos bancarios</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi.version>2.5.0</springdoc-openapi.version>
        <lombok.version>1.18.30</lombok.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
            <version>2.2.224</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/banco/prestamos/PrestamosApplication.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: prestamos-service
  datasource:
    url: jdbc:h2:mem:prestamosdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  actuator:
    endpoints:
      web:
        exposure:
          include: health,info,metrics,loggers
    endpoint:
      health:
        show-details: always

server:
  port: 8080
  servlet:
    context-path: /api/prestamos

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true
    operationsSorter: method
    tagsSorter: alpha
    doc-expansion: none

logging:
  level:
    root: INFO
    com.banco.prestamos: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: INFO
    org.springframework.transaction: TRACE

// === ARCHIVO: src/main/java/com/banco/prestamos/domain/model/LoanRequest.java ===
package com.banco.prestamos.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanRequest(
    @NotNull
    UUID requestId,

    @NotNull
    @Positive
    Long customerId,

    @NotNull
    @Positive
    BigDecimal amount,

    @NotNull
    @Positive
    Integer termMonths,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0")
    BigDecimal interestRate,

    @NotNull
    @FutureOrPresent
    LocalDate requestDate,

    @NotNull
    LoanStatus status,

    String rejectionReason
) {
    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }

    public LoanRequest {
        if (amount != null && termMonths != null && interestRate != null) {
            BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP)
                    .divide(BigDecimal.valueOf(12), 10, BigDecimal.ROUND_HALF_UP);
            BigDecimal denominator = BigDecimal.ONE.subtract(
                    BigDecimal.ONE.divide(
                            BigDecimal.ONE.add(monthlyInterestRate).pow(termMonths),
                            10,
                            BigDecimal.ROUND_HALF_UP
                    )
            );
            BigDecimal monthlyPayment = amount.multiply(monthlyInterestRate)
                    .divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public LoanRequest withStatus(LoanStatus newStatus) {
        return new LoanRequest(
                this.requestId,
                this.customerId,
                this.amount,
                this.termMonths,
                this.interestRate,
                this.requestDate,
                newStatus,
                this.rejectionReason
        );
    }

    public LoanRequest withRejectionReason(String reason) {
        return new LoanRequest(
                this.requestId,
                this.customerId,
                this.amount,
                this.termMonths,
                this.interestRate,
                this.requestDate,
                LoanStatus.REJECTED,
                reason
        );
    }
}

// === ARCHIVO: src/main/java/com/banco/prestamos/domain/ports/LoanRequestRepository.java ===
package com.banco.prestamos.domain.ports;

import com.banco.prestamos.domain.model.LoanRequest;
import java.util.Optional;
import java.util.UUID;

public interface LoanRequestRepository {
    LoanRequest save(LoanRequest loanRequest);

    Optional<LoanRequest> findByRequestId(UUID requestId);

    Optional<LoanRequest> findByCustomerIdAndRequestId(Long customerId, UUID requestId);

    boolean existsByCustomerIdAndRequestId(Long customerId, UUID requestId);
}

// === ARCHIVO: src/main/java/com/banco/prestamos/domain/ports/IdempotencyService.java ===
package com.banco.prestamos.domain.ports;

import java.util.UUID;

public interface IdempotencyService {
    boolean isIdempotentKeyProcessed(String idempotencyKey);

    void markIdempotentKeyAsProcessed(String idempotencyKey, UUID requestId);
}

// === ARCHIVO: src/main/java/com/banco/prestamos/infrastructure/adapters/JpaLoanRequestRepository.java ===
package com.banco.prestamos.infrastructure.adapters;

import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaLoanRequestRepository implements LoanRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoanRequest save(LoanRequest loanRequest) {
        if (loanRequest.requestId() == null) {
            throw new IllegalArgumentException("El identificador de solicitud no puede ser nulo");
        }
        
        LoanRequest existing = entityManager.find(
            LoanRequest.class, 
            loanRequest.requestId()
        );
        
        if (existing != null) {
            entityManager.merge(loanRequest);
        } else {
            entityManager.persist(loanRequest);
        }
        
        entityManager.flush();
        entityManager.clear();
        
        return entityManager.find(LoanRequest.class, loanRequest.requestId());
    }

    @Override
    public Optional<LoanRequest> findByRequestId(UUID requestId) {
        if (requestId == null) {
            return Optional.empty();
        }
        
        LoanRequest result = entityManager.find(LoanRequest.class, requestId);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<LoanRequest> findByCustomerIdAndRequestId(Long customerId, UUID requestId) {
        if (customerId == null || requestId == null) {
            return Optional.empty();
        }
        
        var query = entityManager.createQuery(
            "SELECT lr FROM LoanRequest lr WHERE lr.customerId = :customerId AND lr.requestId = :requestId",
            LoanRequest.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("requestId", requestId);
        
        var results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public boolean existsByCustomerIdAndRequestId(Long customerId, UUID requestId) {
        if (customerId == null || requestId == null) {
            return false;
        }
        
        var query = entityManager.createQuery(
            "SELECT COUNT(lr) FROM LoanRequest lr WHERE lr.customerId = :customerId AND lr.requestId = :requestId",
            Long.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("requestId", requestId);
        
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }
}

// === ARCHIVO: src/main/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCase.java ===
package com.banco.prestamos.application.usecases;

import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.domain.ports.IdempotencyService;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class CreateLoanRequestUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateLoanRequestUseCase.class);
    
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("50000.00");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 60;

    private final LoanRequestRepository loanRequestRepository;
    private final IdempotencyService idempotencyService;

    public CreateLoanRequestUseCase(
            LoanRequestRepository loanRequestRepository,
            IdempotencyService idempotencyService) {
        this.loanRequestRepository = loanRequestRepository;
        this.idempotencyService = idempotencyService;
    }

    @Transactional
    public LoanRequest execute(
            Long customerId,
            String idempotencyKey,
            BigDecimal amount,
            Integer termMonths,
            String purpose) {
        
        logger.info("Iniciando procesamiento de solicitud de préstamo para cliente: {}", customerId);
        
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia es obligatoria");
        }
        
        if (idempotencyService.isIdempotentKeyProcessed(idempotencyKey)) {
            logger.warn("Clave de idempotencia ya procesada: {}", idempotencyKey);
            throw new LoanRequestAlreadyExistsException(
                "Ya existe una solicitud procesada con esta clave de idempotencia"
            );
        }
        
        validateLoanParameters(amount, termMonths, purpose);
        
        UUID requestId = UUID.randomUUID();
        LocalDate requestDate = LocalDate.now();
        
        LoanRequest loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            purpose,
            LoanStatus.PENDING,
            requestDate,
            null
        );
        
        LoanRequest savedRequest = loanRequestRepository.save(loanRequest);
        
        idempotencyService.markIdempotentKeyAsProcessed(idempotencyKey, requestId);
        
        logger.info("Solicitud de préstamo creada exitosamente con ID: {}", requestId);
        
        return savedRequest;
    }

    private void validateLoanParameters(BigDecimal amount, Integer termMonths, String purpose) {
        if (amount == null) {
            throw new IllegalArgumentException("El monto del préstamo es obligatorio");
        }
        
        if (amount.compareTo(MIN_AMOUNT) < 0) {
            throw new IllegalArgumentException(
                String.format("El monto mínimo del préstamo es %s", MIN_AMOUNT)
            );
        }
        
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException(
                String.format("El monto máximo del préstamo es %s", MAX_AMOUNT)
            );
        }
        
        if (termMonths == null) {
            throw new IllegalArgumentException("El plazo del préstamo es obligatorio");
        }
        
        if (termMonths < MIN_TERM_MONTHS || termMonths > MAX_TERM_MONTHS) {
            throw new IllegalArgumentException(
                String.format(
                    "El plazo debe estar entre %d y %d meses",
                    MIN_TERM_MONTHS,
                    MAX_TERM_MONTHS
                )
            );
        }
        
        if (purpose == null || purpose.isBlank()) {
            throw new IllegalArgumentException("El propósito del préstamo es obligatorio");
        }
    }
}

// === ARCHIVO: src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java ===
package com.banco.prestamos.infrastructure.adapters;

import com.banco.prestamos.domain.ports.IdempotencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdempotencyServiceImpl implements IdempotencyService {

    private static final Logger logger = LoggerFactory.getLogger(IdempotencyServiceImpl.class);
    private static final long DEFAULT_TTL_SECONDS = 86400L;

    private final ConcurrentHashMap<String, IdempotencyRecord> idempotencyStore;

    public IdempotencyServiceImpl() {
        this.idempotencyStore = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isIdempotentKeyProcessed(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return false;
        }
        
        IdempotencyRecord record = idempotencyStore.get(idempotencyKey);
        
        if (record == null) {
            return false;
        }
        
        if (isRecordExpired(record)) {
            logger.debug("Registro de idempotencia expirado para clave: {}", idempotencyKey);
            idempotencyStore.remove(idempotencyKey);
            return false;
        }
        
        logger.debug("Clave de idempotencia encontrada: {}", idempotencyKey);
        return true;
    }

    @Override
    public void markIdempotentKeyAsProcessed(String idempotencyKey, UUID requestId) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
        
        if (requestId == null) {
            throw new IllegalArgumentException("El identificador de solicitud no puede ser nulo");
        }
        
        IdempotencyRecord record = new IdempotencyRecord(
            requestId,
            Instant.now(),
            DEFAULT_TTL_SECONDS
        );
        
        idempotencyStore.put(idempotencyKey, record);
        logger.info("Clave de idempotencia marcada como procesada: {} -> {}", idempotencyKey, requestId);
    }

    private boolean isRecordExpired(IdempotencyRecord record) {
        Instant expirationTime = record.createdAt().plusSeconds(record.ttlSeconds());
        return Instant.now().isAfter(expirationTime);
    }

    private record IdempotencyRecord(
        UUID requestId,
        Instant createdAt,
        long ttlSeconds
    ) {}
}

// === ARCHIVO: src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java ===
package com.banco.prestamos.infrastructure.controllers;

import com.banco.prestamos.application.usecases.CreateLoanRequestUseCase;
import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.domain.ports.IdempotencyService;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loan-requests")
@Tag(name = "Loan Requests", description = "API para la gestión de solicitudes de préstamos")
public class LoanRequestController {

    private final CreateLoanRequestUseCase createLoanRequestUseCase;
    private final LoanRequestRepository loanRequestRepository;
    private final IdempotencyService idempotencyService;

    public LoanRequestController(
            CreateLoanRequestUseCase createLoanRequestUseCase,
            LoanRequestRepository loanRequestRepository,
            IdempotencyService idempotencyService) {
        this.createLoanRequestUseCase = createLoanRequestUseCase;
        this.loanRequestRepository = loanRequestRepository;
        this.idempotencyService = idempotencyService;
    }

    public record CreateLoanRequestRequest(
            @NotNull(message = "El ID del cliente es obligatorio")
            @Positive(message = "El ID del cliente debe ser positivo")
            Long customerId,

            @NotNull(message = "El monto es obligatorio")
            @Positive(message = "El monto debe ser mayor a cero")
            BigDecimal amount,

            @NotNull(message = "El plazo en meses es obligatorio")
            @Positive(message = "El plazo debe ser mayor a cero")
            Integer termMonths,

            @NotBlank(message = "El propósito del préstamo es obligatorio")
            String purpose,

            @NotBlank(message = "La clave de idempotencia es obligatoria")
            String idempotencyKey
    ) {}

    public record LoanRequestResponse(
            UUID requestId,
            Long customerId,
            BigDecimal amount,
            Integer termMonths,
            String purpose,
            LoanStatus status,
            String rejectionReason,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        public static LoanRequestResponse fromDomain(LoanRequest loanRequest) {
            return new LoanRequestResponse(
                    loanRequest.requestId(),
                    loanRequest.customerId(),
                    loanRequest.amount(),
                    loanRequest.termMonths(),
                    loanRequest.purpose(),
                    loanRequest.status(),
                    loanRequest.rejectionReason(),
                    loanRequest.createdAt(),
                    loanRequest.updatedAt()
            );
        }
    }

    @PostMapping
    @Operation(summary = "Crear una nueva solicitud de préstamo", description = "Crea una nueva solicitud de préstamo con validación de idempotencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud de préstamo creada exitosamente",
                    content = @Content(schema = @Schema(implementation = LoanRequestResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "La solicitud ya existe (violación de idempotencia)"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<LoanRequestResponse> createLoanRequest(
            @Valid @RequestBody CreateLoanRequestRequest request) {

        if (idempotencyService.isIdempotentKeyProcessed(request.idempotencyKey())) {
            throw new LoanRequestAlreadyExistsException(
                    "Ya existe una solicitud procesada con la clave de idempotencia: " + request.idempotencyKey()
            );
        }

        LoanRequest createdLoanRequest = createLoanRequestUseCase.execute(
                request.customerId(),
                request.amount(),
                request.termMonths(),
                request.purpose()
        );

        idempotencyService.markIdempotentKeyAsProcessed(
                request.idempotencyKey(),
                createdLoanRequest.requestId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(LoanRequestResponse.fromDomain(createdLoanRequest));
    }

    @GetMapping("/{requestId}")
    @Operation(summary = "Obtener una solicitud de préstamo por ID", description = "Recupera los detalles de una solicitud de préstamo específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada",
                    content = @Content(schema = @Schema(implementation = LoanRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<LoanRequestResponse> getLoanRequestById(
            @Parameter(description = "ID de la solicitud de préstamo", required = true)
            @PathVariable UUID requestId) {

        return loanRequestRepository.findByRequestId(requestId)
                .map(loanRequest -> ResponseEntity.ok(LoanRequestResponse.fromDomain(loanRequest)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Obtener solicitudes de préstamo por ID de cliente", description = "Lista todas las solicitudes de préstamo de un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes del cliente",
                    content = @Content(schema = @Schema(implementation = LoanRequestResponse.class)))
    })
    public ResponseEntity<List<LoanRequestResponse>> getLoanRequestsByCustomerId(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable Long customerId) {

        List<LoanRequest> loanRequests = loanRequestRepository.findByCustomerId(customerId);
        List<LoanRequestResponse> responses = loanRequests.stream()
                .map(LoanRequestResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/customer/{customerId}/request/{requestId}")
    @Operation(summary = "Obtener solicitud específica de un cliente", description = "Verifica si existe una solicitud específica para un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada para este cliente")
    })
    public ResponseEntity<Void> getLoanRequestByCustomerAndRequestId(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable Long customerId,
            @Parameter(description = "ID de la solicitud", required = true)
            @PathVariable UUID requestId) {

        boolean exists = loanRequestRepository.existsByCustomerIdAndRequestId(customerId, requestId);

        if (exists) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

// === ARCHIVO: src/main/java/com/banco/prestamos/infrastructure/config/OpenApiConfig.java ===
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

// === ARCHIVO: src/main/java/com/banco/prestamos/infrastructure/exceptions/GlobalExceptionHandler.java ===
package com.banco.prestamos.infrastructure.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public record ErrorResponse(
            int status,
            String error,
            String message,
            String path,
            LocalDateTime timestamp,
            Map<String, String> details
    ) {}

    @ExceptionHandler(LoanRequestAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleLoanRequestAlreadyExists(
            LoanRequestAlreadyExistsException ex, WebRequest request) {

        logger.warn("Intento de crear solicitud duplicada: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor inválido",
                        (existing, replacement) -> existing
                ));

        logger.warn("Errores de validación: {}", errors);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Los datos de entrada contienen errores de validación",
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {

        logger.warn("Argumento ilegal: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElement(
            NoSuchElementException ex, WebRequest request) {

        logger.warn("Elemento no encontrado: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {

        logger.error("Error interno del servidor: ", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ha ocurrido un error inesperado. Por favor, contacte al administrador.",
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

// === ARCHIVO: src/main/java/com/banco/prestamos/infrastructure/exceptions/LoanRequestAlreadyExistsException.java ===
package com.banco.prestamos.infrastructure.exceptions;

import java.util.UUID;

public class LoanRequestAlreadyExistsException extends RuntimeException {

    private final Long customerId;
    private final UUID requestId;
    private final String idempotencyKey;

    public LoanRequestAlreadyExistsException(Long customerId, UUID requestId, String idempotencyKey) {
        super(buildMessage(customerId, requestId, idempotencyKey));
        this.customerId = customerId;
        this.requestId = requestId;
        this.idempotencyKey = idempotencyKey;
        validateInvariant();
    }

    public LoanRequestAlreadyExistsException(Long customerId, UUID requestId, String idempotencyKey, Throwable cause) {
        super(buildMessage(customerId, requestId, idempotencyKey), cause);
        this.customerId = customerId;
        this.requestId = requestId;
        this.idempotencyKey = idempotencyKey;
        validateInvariant();
    }

    private static String buildMessage(Long customerId, UUID requestId, String idempotencyKey) {
        return String.format(
            "Ya existe una solicitud de prestamo para el cliente %d con requestId %s (idempotencyKey: %s)",
            customerId,
            requestId != null ? requestId.toString() : "null",
            idempotencyKey != null ? idempotencyKey : "null"
        );
    }

    private void validateInvariant() {
        if (customerId == null) {
            throw new IllegalArgumentException("El customerId no puede ser null en LoanRequestAlreadyExistsException");
        }
        if (requestId == null && (idempotencyKey == null || idempotencyKey.isBlank())) {
            throw new IllegalArgumentException("Se requiere al menos requestId o idempotencyKey para LoanRequestAlreadyExistsException");
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getErrorCode() {
        return "LOAN_REQUEST_DUPLICATE";
    }
}

// === ARCHIVO: src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java ===
package com.banco.prestamos.application.usecases;

import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.domain.ports.IdempotencyService;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para CreateLoanRequestUseCase")
class CreateLoanRequestUseCaseTest {

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private IdempotencyService idempotencyService;

    private CreateLoanRequestUseCase createLoanRequestUseCase;

    @BeforeEach
    void setUp() {
        createLoanRequestUseCase = new CreateLoanRequestUseCase(loanRequestRepository, idempotencyService);
    }

    @Test
    @DisplayName("Debe crear una solicitud de prestamo exitosamente cuando es idempotente")
    void execute_DeberiaCrearSolicitud_CuandoEsIdempotente() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String idempotencyKey = "idem-001";
        UUID requestId = UUID.randomUUID();

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(false);
        when(loanRequestRepository.existsByCustomerIdAndRequestId(customerId, requestId)).thenReturn(false);
        when(loanRequestRepository.save(any(LoanRequest.class))).thenAnswer(invocation -> {
            LoanRequest saved = invocation.getArgument(0);
            return saved.withStatus(LoanStatus.PENDING);
        });

        LoanRequest result = createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey);

        assertNotNull(result);
        assertEquals(customerId, result.customerId());
        assertEquals(amount, result.amount());
        assertEquals(termMonths, result.termMonths());
        assertEquals(LoanStatus.PENDING, result.status());
        verify(idempotencyService).markIdempotentKeyAsProcessed(eq(idempotencyKey), any(UUID.class));
    }

    @Test
    @DisplayName("Debe rechazar solicitud duplicada cuando ya existe para el mismo cliente y requestId")
    void execute_DeberiaRechazar_CuandoSolicitudDuplicada() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String idempotencyKey = "idem-002";
        UUID requestId = UUID.randomUUID();

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(true);
        when(loanRequestRepository.findByCustomerIdAndRequestId(customerId, requestId))
            .thenReturn(Optional.of(new LoanRequest(
                requestId,
                customerId,
                amount,
                termMonths,
                LoanStatus.PENDING,
                LocalDate.now(),
                null,
                idempotencyKey
            )));

        LoanRequestAlreadyExistsException exception = assertThrows(
            LoanRequestAlreadyExistsException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey)
        );

        assertEquals(customerId, exception.getCustomerId());
        assertEquals(requestId, exception.getRequestId());
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar cuando el monto es menor o igual a cero")
    void execute_DeberiaRechazar_CuandoMontoInvalido() {
        Long customerId = 12345L;
        BigDecimal invalidAmount = BigDecimal.ZERO;
        Integer termMonths = 24;
        String idempotencyKey = "idem-003";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(customerId, invalidAmount, termMonths, idempotencyKey)
        );

        assertTrue(exception.getMessage().contains("monto") || exception.getMessage().contains("amount"));
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar cuando el plazo es menor o igual a cero")
    void execute_DeberiaRechazar_CuandoPlazoInvalido() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer invalidTerm = 0;
        String idempotencyKey = "idem-004";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, invalidTerm, idempotencyKey)
        );

        assertTrue(exception.getMessage().contains("plazo") || exception.getMessage().contains("term"));
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar cuando el customerId es null")
    void execute_DeberiaRechazar_CuandoCustomerIdNulo() {
        Long nullCustomerId = null;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String idempotencyKey = "idem-005";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(nullCustomerId, amount, termMonths, idempotencyKey)
        );

        assertTrue(exception.getMessage().contains("cliente") || exception.getMessage().contains("customer"));
    }

    @Test
    @DisplayName("Debe rechazar cuando la clave de idempotencia es nula o vacia")
    void execute_DeberiaRechazar_CuandoIdempotencyKeyInvalido() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String emptyIdempotencyKey = "";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, termMonths, emptyIdempotencyKey)
        );

        assertTrue(exception.getMessage().contains("idempotency") || exception.getMessage().contains("clave"));
    }

    @Test
    @DisplayName("Debe guardar la solicitud con la clave de idempotencia correcta")
    void execute_DeberiaGuardarConIdempotencyKey_Correcto() {
        Long customerId = 67890L;
        BigDecimal amount = new BigDecimal("100000.00");
        Integer termMonths = 36;
        String idempotencyKey = "idem-006-unique";

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(false);
        when(loanRequestRepository.existsByCustomerIdAndRequestId(anyLong(), any(UUID.class))).thenReturn(false);
        when(loanRequestRepository.save(any(LoanRequest.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<LoanRequest> requestCaptor = ArgumentCaptor.forClass(LoanRequest.class);
        createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey);

        verify(loanRequestRepository).save(requestCaptor.capture());
        LoanRequest capturedRequest = requestCaptor.getValue();
        assertEquals(idempotencyKey, capturedRequest.idempotencyKey());
    }

    @Test
    @DisplayName("Debe rechazar cuando la clave de idempotencia ya fue procesada previamente")
    void execute_DeberiaRechazar_CuandoIdempotencyKeyYaProcesada() {
        Long customerId = 11111L;
        BigDecimal amount = new BigDecimal("25000.00");
        Integer termMonths = 12;
        String idempotencyKey = "idem-007-processed";
        UUID existingRequestId = UUID.randomUUID();

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(true);
        when(loanRequestRepository.findByCustomerIdAndRequestId(eq(customerId), eq(existingRequestId)))
            .thenReturn(Optional.of(new LoanRequest(
                existingRequestId,
                customerId,
                amount,
                termMonths,
                LoanStatus.APPROVED,
                LocalDate.now(),
                null,
                idempotencyKey
            )));

        LoanRequestAlreadyExistsException exception = assertThrows(
            LoanRequestAlreadyExistsException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey)
        );

        assertEquals(idempotencyKey, exception.getIdempotencyKey());
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }
}

// === ARCHIVO: src/test/java/com/banco/prestamos/infrastructure/controllers/LoanRequestControllerTest.java ===
package com.banco.prestamos.infrastructure.controllers;

import com.banco.prestamos.application.usecases.CreateLoanRequestUseCase;
import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de integracion para LoanRequestController")
class LoanRequestControllerTest {

    @Mock
    private CreateLoanRequestUseCase createLoanRequestUseCase;

    @InjectMocks
    private LoanRequestController loanRequestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Debe retornar 201 Created cuando la solicitud se crea exitosamente")
    void createLoanRequest_DeberiaRetornar201_CuandoExitoso() throws Exception {
        Long customerId = 54321L;
        BigDecimal amount = new BigDecimal("75000.00");
        Integer termMonths = 30;
        String idempotencyKey = "key-create-success";
        UUID requestId = UUID.randomUUID();

        LoanRequest loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            LoanStatus.PENDING,
            LocalDate.now(),
            null,
            idempotencyKey
        );

        when(createLoanRequestUseCase.execute(eq(customerId), eq(amount), eq(termMonths), eq(idempotencyKey)))
            .thenReturn(loanRequest);

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/v1/loan-requests/" + requestId))
            .andExpect(jsonPath("$.requestId").value(requestId.toString()))
            .andExpect(jsonPath("$.customerId").value(customerId))
            .andExpect(jsonPath("$.status").value("PENDING"));

        verify(createLoanRequestUseCase).execute(customerId, amount, termMonths, idempotencyKey);
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el monto es invalido")
    void createLoanRequest_DeberiaRetornar400_CuandoMontoInvalido() throws Exception {
        Long customerId = 54321L;
        BigDecimal invalidAmount = BigDecimal.ZERO;
        Integer termMonths = 30;
        String idempotencyKey = "key-invalid-amount";

        when(createLoanRequestUseCase.execute(eq(customerId), eq(invalidAmount), eq(termMonths), eq(idempotencyKey)))
            .thenThrow(new IllegalArgumentException("El monto debe ser mayor a cero"));

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", invalidAmount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.message").value("El monto debe ser mayor a cero"));
    }

    @Test
    @DisplayName("Debe retornar 409 Conflict cuando la solicitud ya existe (idempotencia)")
    void createLoanRequest_DeberiaRetornar409_CuandoSolicitudDuplicada() throws Exception {
        Long customerId = 99999L;
        BigDecimal amount = new BigDecimal("100000.00");
        Integer termMonths = 48;
        String idempotencyKey = "key-duplicate";
        UUID existingRequestId = UUID.randomUUID();

        when(createLoanRequestUseCase.execute(eq(customerId), eq(amount), eq(termMonths), eq(idempotencyKey)))
            .thenThrow(new LoanRequestAlreadyExistsException(customerId, existingRequestId, idempotencyKey));

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.error").value("LOAN_REQUEST_DUPLICATE"))
            .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando falta el campo customerId")
    void createLoanRequest_DeberiaRetornar400_CuandoFaltaCustomerId() throws Exception {
        Map<String, Object> requestBody = Map.of(
            "amount", new BigDecimal("50000.00"),
            "termMonths", 24,
            "idempotencyKey", "key-missing-customer"
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el JSON esta mal formado")
    void createLoanRequest_DeberiaRetornar400_CuandoJsonInvalido() throws Exception {
        String invalidJson = "{customerId: 12345, amount: }";

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Debe usar la clave de idempotencia del header si esta presente")
    void createLoanRequest_DeberiaUsarIdempotencyKey_DelHeader() throws Exception {
        Long customerId = 11111L;
        BigDecimal amount = new BigDecimal("30000.00");
        Integer termMonths = 18;
        String headerIdempotencyKey = "header-key-123";
        UUID requestId = UUID.randomUUID();

        LoanRequest loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            LoanStatus.PENDING,
            LocalDate.now(),
            null,
            headerIdempotencyKey
        );

        when(createLoanRequestUseCase.execute(eq(customerId), eq(amount), eq(termMonths), eq(headerIdempotencyKey)))
            .thenReturn(loanRequest);

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", headerIdempotencyKey)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.requestId").value(requestId.toString()));

        verify(createLoanRequestUseCase).execute(customerId, amount, termMonths, headerIdempotencyKey);
    }

    @Test
    @DisplayName("Debe retornar 500 cuando ocurre un error inesperado")
    void createLoanRequest_DeberiaRetornar500_CuandoErrorInesperado() throws Exception {
        Long customerId = 22222L;
        BigDecimal amount = new BigDecimal("40000.00");
        Integer termMonths = 20;
        String idempotencyKey = "key-error";

        when(createLoanRequestUseCase.execute(anyLong(), any(BigDecimal.class), anyInt(), anyString()))
            .thenThrow(new RuntimeException("Error de base de datos"));

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.error").value("INTERNAL_ERROR"));
    }
}
```
