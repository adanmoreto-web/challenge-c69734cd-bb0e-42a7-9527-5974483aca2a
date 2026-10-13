# Mejora de calidad de código en un servicio de préstamos

En el dominio de préstamos de un banco digital, un servicio backend gestiona solicitudes de préstamos. El servicio debe ser idempotente respecto al número de solicitud y garantizar consistencia en la persistencia de datos. El objetivo es aplicar principios de código limpio para mejorar la calidad del servicio existente.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | aplicación de código limpio y eficiente |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Revisión de código existente

**Objetivo:** Identificar áreas de mejora en el código actual

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza el código del servicio de préstamos en busca de violaciones a los principios KISS, SOLID, YAGNI y DRY.
- Identifica al menos tres áreas donde se puedan aplicar mejoras.

**Entregable:** Informe de revisión de código con áreas de mejora identificadas.

<details>
<summary>Pistas de conocimiento</summary>

- Cada principio tiene un propósito específico en la mejora de la calidad del código.
- Busca patrones comunes que violen estos principios.

</details>

### Fase 2: Aplicación de mejoras

**Objetivo:** Refactorizar el código para aplicar los principios de código limpio

**Tiempo estimado:** 6 horas

**Instrucciones:**

- Refactoriza el código del servicio de préstamos para aplicar los principios KISS, SOLID, YAGNI y DRY.
- Documenta los cambios realizados y justifica cada una de las mejoras.

**Entregable:** Código refactorizado con documentación de cambios y justificaciones.

<details>
<summary>Pistas de conocimiento</summary>

- Cada cambio debe tener una justificación clara basada en los principios de código limpio.
- Utiliza ejemplos concretos del dominio para ilustrar las mejoras.

</details>

### Fase 3: Verificación de mejoras

**Objetivo:** Verificar que las mejoras aplicadas cumplen con los criterios de calidad de código

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Verifica que el código refactorizado cumpla con los principios de código limpio.
- Realiza pruebas unitarias y de integración para asegurar que las mejoras no introducen nuevos errores.

**Entregable:** Informe de verificación con resultados de pruebas y cumplimiento de principios.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de análisis estático para verificar el cumplimiento de los principios.
- Asegúrate de que las pruebas cubran todos los casos de uso del servicio de préstamos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los principios de código limpio y por qué son importantes?
- **paraQueSirve**: ¿Para qué sirve aplicar los principios de código limpio en un servicio de préstamos?
- **comoSeUsa**: ¿Cómo se aplican los principios de código limpio en el código existente?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar los principios de código limpio?
- **queDecisionesImplica**: ¿Qué decisiones implica refactorizar el código para seguir los principios de código limpio?

## Criterios de Evaluacion

- Identificación de áreas de mejora en el código existente.
- Aplicación correcta de los principios de código limpio en el refactoring.
- Documentación clara y justificación de los cambios realizados.
- Verificación del cumplimiento de los principios mediante pruebas y herramientas de análisis.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
