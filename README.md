# Implementación de manejo de errores y reintentos en un sistema de procesamiento de pagos

El sistema de procesamiento de pagos debe manejar correctamente los errores y reintentos para garantizar la consistencia y la disponibilidad del servicio. El sistema interactúa con múltiples actores, incluyendo el originador de créditos, el motor antifraude, el buró de riesgos, y el core bancario. El objetivo es asegurar que los pagos se procesen correctamente incluso en presencia de fallos temporales o errores transitorios. Los umbrales del dominio incluyen un throughput de 1 500 solicitudes/segundo en hora pico y una latencia máxima de 2 segundos para la confirmación del pago.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E technologiesWork 202610091709 |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

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

### Fase 1: Identificación de puntos de falla

**Objetivo:** Identificar los puntos de falla en el sistema de procesamiento de pagos y definir las estrategias de manejo de errores.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza el flujo de procesamiento de pagos e identifica los posibles puntos de falla.
- Define las estrategias de manejo de errores para cada punto de falla identificado.

**Entregable:** Documento que describe los puntos de falla y las estrategias de manejo de errores.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes tipos de errores que pueden ocurrir en cada componente del sistema.
- Piensa en cómo los errores pueden afectar la consistencia y la disponibilidad del servicio.

</details>

### Fase 2: Implementación de reintentos

**Objetivo:** Implementar la lógica de reintentos para manejar los errores transitorios en el sistema de procesamiento de pagos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementa la lógica de reintentos para cada punto de falla identificado en la fase anterior.
- Define los criterios de reintento, incluyendo el número máximo de intentos y el tiempo de espera entre intentos.

**Entregable:** Código que implementa la lógica de reintentos para manejar los errores transitorios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes tipos de errores transitorios que pueden ocurrir en el sistema.
- Piensa en cómo los reintentos pueden afectar la latencia y el throughput del servicio.

</details>

### Fase 3: Evaluación y optimización

**Objetivo:** Evaluar la implementación de manejo de errores y reintentos y optimizar el rendimiento del sistema.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Evalúa la implementación de manejo de errores y reintentos en términos de consistencia, disponibilidad y rendimiento.
- Identifica áreas de mejora y optimiza la implementación para mejorar el rendimiento del sistema.

**Entregable:** Reporte que evalúa la implementación de manejo de errores y reintentos y propone mejoras.

<details>
<summary>Pistas de conocimiento</summary>

- Considera métricas como la consistencia, la disponibilidad y el rendimiento del sistema.
- Piensa en cómo puedes optimizar la implementación para mejorar el rendimiento sin comprometer la consistencia y la disponibilidad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los puntos de falla en el sistema de procesamiento de pagos y cómo se manejan?
- **paraQueSirve**: ¿Para qué sirven las estrategias de manejo de errores y reintentos en el sistema de procesamiento de pagos?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir en el sistema de procesamiento de pagos y cómo se mitigan?
- **comoSeUsa**: ¿Cómo se implementa la lógica de reintentos en el sistema de procesamiento de pagos?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de manejo de errores y reintentos en el sistema de procesamiento de pagos?

## Criterios de Evaluacion

- Identificación correcta de los puntos de falla en el sistema de procesamiento de pagos.
- Definición de estrategias de manejo de errores adecuadas para cada punto de falla.
- Implementación efectiva de la lógica de reintentos para manejar los errores transitorios.
- Evaluación y optimización del rendimiento del sistema con métricas concretas.

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
