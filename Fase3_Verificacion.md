# Fase 3: Verificación y Herramientas de Calidad

## 1. Ejecución de Herramientas de Análisis Estático

Yo ejecute las siguientes distintas herramientas mencionadas de análisis sobre el código.

### PMD (v7.17.0)

- **Comando:** `mvn pmd:pmd`
- **Resultado:** **0 violaciones** (`target/pmd.xml` limpio).
- **Acciones tomadas:** Se tuvo que eliminar algunos imports innecesarios en `LoanRequestController` y `GlobalExceptionHandler`.

### SpotBugs (v4.8.6)

- **Comando:** `mvn com.github.spotbugs:spotbugs-maven-plugin:spotbugs`
- **Resultado:** **Aprobado**.
- **Acciones tomadas:**
  - Se hizo inmutable el mapa de detalles en `GlobalExceptionHandler.ErrorResponse` para evitar exposición de representación interna (`EI_EXPOSE_REP`).
  - Se eliminaron comprobaciones redundantes de null.

### Checkstyle (v3.6.0)

- **Comando:** `mvn checkstyle:checkstyle -Dcheckstyle.config.location=google_checks.xml`
- **Resultado:** **Aprobado**. La estructura de clases es conforme a convenciones de diseño y nomenclatura Java.

### JaCoCo (v0.8.12)

- **Comando:** `mvn jacoco:prepare-agent test jacoco:report`
- **Resultado:**
  - `DefaultLoanRequestValidator`: **100% cobertura de líneas**.
  - `CreateLoanRequestUseCase`: **89% cobertura de líneas**.
  - `GlobalExceptionHandler`: **82% cobertura de líneas**.
  - `LoanRequestController`: **100% en DTOs y endpoints principales cubiertos**.

---

## 2. Ejecución de Pruebas Unitarias e Integración

- **Comando:** `mvn test`
- **Total de pruebas ejecutadas:** 15
- **Pruebas exitosas:** 15 (100%)
- **Fallos:** 0
- **Errores:** 0
- **Suites validadas:**
  1. `CreateLoanRequestUseCaseTest`: 8 de 8 tests aprobados.
  2. `LoanRequestControllerTest`: 7 de 7 tests aprobados.
