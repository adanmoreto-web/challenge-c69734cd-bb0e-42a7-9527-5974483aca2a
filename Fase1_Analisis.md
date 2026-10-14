# Fase 1: Revisión y Análisis del Código Existente

## 1. Identificación de Violaciones de Principios

### SOLID

- **`LoanRequestController`:**
  Este tiene una concentración de muchas responsabilidades como: manejo HTTP, validación de idempotencia, marcado de idempotencia y acceso directo a persistencia.
- **`CreateLoanRequestUseCase`:**
  Este tiene una mezcla en la lógica de negocio, persistencia, idempotencia y reglas de validación numérica acopladas de forma estática en la misma clase.

### DRY

- **Duplicación de validación de idempotencia:**
  Yo revise que tanto `LoanRequestController` como `CreateLoanRequestUseCase` validan `idempotencyService.isIdempotentKeyProcessed()` y ejecutan `markIdempotentKeyAsProcessed()`. La lógica de idempotencia está duplicada en la capa web y en la capa de aplicación.

### KISS & YAGNI

- **Cálculo muerto en `LoanRequest`:**
  Se tiene un record `LoanRequest`que calcula en su constructor una fórmula compleja de cuota mensual (`monthlyPayment`) con `interestRate`, pero el resultado es una variable local que nunca se guarda ni se retorna.

## 2. Propuesta de Mejoras Específicas para Fase 2

1. **Centralizar Idempotencia:** Se debe delegar la validación y registro de idempotencia al caso de uso.
2. **Encapsular Reglas de Validación:** Se debe extraer las validaciones de montos y plazos a un validador de dominio independiente o política de préstamos.
3. **Limpiar `LoanRequest`:** Se tiene que eliminar el cálculo de cuota mensual y el campo `interestRate` no requerido.
