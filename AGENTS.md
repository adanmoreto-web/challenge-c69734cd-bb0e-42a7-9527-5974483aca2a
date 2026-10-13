# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Mejora de calidad de código en un servicio de préstamos**.

| | |
|---|---|
| Tema | aplicación de código limpio y eficiente |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0
- com.h2database:h2 2.2.224
- org.projectlombok:lombok 1.18.30

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Revisión de código existente**: Informe de revisión de código con áreas de mejora identificadas.
- **Fase 2 — Aplicación de mejoras**: Código refactorizado con documentación de cambios y justificaciones.
- **Fase 3 — Verificación de mejoras**: Informe de verificación con resultados de pruebas y cumplimiento de principios.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (30)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/prestamos/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/prestamos/infrastructure/exceptions/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banco/prestamos/infrastructure/controllers/LoanRequestControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/prestamos/infrastructure/adapters/JpaLoanRequestRepository.java` — `LoanRequest.requestId`
      Se invoca `requestId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java` — `IdempotencyRecord.createdAt`
      Se invoca `createdAt` sobre `IdempotencyRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java` — `IdempotencyRecord.ttlSeconds`
      Se invoca `ttlSeconds` sobre `IdempotencyRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.requestId`
      Se invoca `requestId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.customerId`
      Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.amount`
      Se invoca `amount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.termMonths`
      Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.purpose`
      Se invoca `purpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.status`
      Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.rejectionReason`
      Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.createdAt`
      Se invoca `createdAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequest.updatedAt`
      Se invoca `updatedAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.customerId`
      Se invoca `customerId` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.amount`
      Se invoca `amount` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.termMonths`
      Se invoca `termMonths` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `CreateLoanRequestRequest.purpose`
      Se invoca `purpose` sobre `CreateLoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java` — `LoanRequestRepository.findByCustomerId`
      Se invoca `findByCustomerId` sobre `LoanRequestRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.customerId`
      Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.amount`
      Se invoca `amount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.termMonths`
      Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.status`
      Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequestAlreadyExistsException.getMessage`
      Se invoca `getMessage` sobre `LoanRequestAlreadyExistsException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java` — `LoanRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (15)

- `pom.xml`
- `src/main/java/com/banco/prestamos/PrestamosApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/banco/prestamos/domain/model/LoanRequest.java`
- `src/main/java/com/banco/prestamos/domain/ports/LoanRequestRepository.java`
- `src/main/java/com/banco/prestamos/domain/ports/IdempotencyService.java`
- `src/main/java/com/banco/prestamos/infrastructure/adapters/JpaLoanRequestRepository.java`
- `src/main/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCase.java`
- `src/main/java/com/banco/prestamos/infrastructure/adapters/IdempotencyServiceImpl.java`
- `src/main/java/com/banco/prestamos/infrastructure/controllers/LoanRequestController.java`
- `src/main/java/com/banco/prestamos/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/banco/prestamos/infrastructure/exceptions/GlobalExceptionHandler.java`
- `src/main/java/com/banco/prestamos/infrastructure/exceptions/LoanRequestAlreadyExistsException.java`
- `src/test/java/com/banco/prestamos/application/usecases/CreateLoanRequestUseCaseTest.java`
- `src/test/java/com/banco/prestamos/infrastructure/controllers/LoanRequestControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/banco/prestamos/domain`
- `src/main/java/com/banco/prestamos/domain/model`
- `src/main/java/com/banco/prestamos/domain/ports`
- `src/main/java/com/banco/prestamos/application`
- `src/main/java/com/banco/prestamos/application/usecases`
- `src/main/java/com/banco/prestamos/infrastructure`
- `src/main/java/com/banco/prestamos/infrastructure/adapters`
- `src/main/java/com/banco/prestamos/infrastructure/controllers`
- `src/main/java/com/banco/prestamos/infrastructure/config`
- `src/main/java/com/banco/prestamos/infrastructure/exceptions`
- `src/main/resources`
- `src/test/java/com/banco/prestamos`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).
- Mision: Candidato con experiencia avanzada en desarrollo backend, enfocado en mejora continua de calidad de código.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
