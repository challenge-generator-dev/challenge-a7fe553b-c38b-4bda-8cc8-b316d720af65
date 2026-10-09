# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/domain/ports/CreditOriginatorPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/ports/FraudEnginePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/ports/RiskBureauPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/ports/BankCorePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentRequest.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/FraudEngineAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/FraudEngineAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/RiskBureauAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/RiskBureauAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/BankCoreAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/BankCoreAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/rest/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/application/usecase/PaymentProcessingUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/rest/PaymentControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/rest/PaymentControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/ports/RiskBureauPort.java` — `RiskLevel.containsScore`: Se invoca `containsScore` sobre `RiskLevel`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.externalReference`: Se invoca `externalReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.fraudCheckReference`: Se invoca `fraudCheckReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.riskBureauReference`: Se invoca `riskBureauReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.bankCoreReference`: Se invoca `bankCoreReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.failureReason`: Se invoca `failureReason` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.retryCount`: Se invoca `retryCount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.processedAt`: Se invoca `processedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.clientId`: Se invoca `clientId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java` — `Payment.merchantId`: Se invoca `merchantId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `RiskBureauPort.enrichWithRiskData`: Se invoca `enrichWithRiskData` sobre `RiskBureauPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `BankCorePort.authorizePayment`: Se invoca `authorizePayment` sobre `BankCorePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `Payment.retryCount`: Se invoca `retryCount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `PaymentProcessingException.getErrorCode`: Se invoca `getErrorCode` sobre `PaymentProcessingException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `CreditOriginatorPort.findById`: Se invoca `findById` sobre `CreditOriginatorPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `Payment.externalReference`: Se invoca `externalReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `Payment.maximumAllowedAmount`: Se invoca `maximumAllowedAmount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `Payment.clientCreditScore`: Se invoca `clientCreditScore` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java` — `PaymentProcessingException.getErrorCode`: Se invoca `getErrorCode` sobre `PaymentProcessingException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/FraudEngineAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/FraudEngineAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/FraudEngineAdapter.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/RiskBureauAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/RiskBureauAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/BankCoreAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/BankCoreAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.userId`: Se invoca `userId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java` — `Payment.updatedAt`: Se invoca `updatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/usecase/PaymentProcessingUseCaseTest.java` — `RiskBureauPort.assessRisk`: Se invoca `assessRisk` sobre `RiskBureauPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/rest/PaymentControllerTest.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/rest/PaymentControllerTest.java` — `PaymentController.getPaymentById`: Se invoca `getPaymentById` sobre `PaymentController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/rest/PaymentControllerTest.java` — `PaymentController.getPaymentsByStatus`: Se invoca `getPaymentsByStatus` sobre `PaymentController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

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
Chapter Backend, Especialidad Desarrollo de software, Tecnología Java, Senior L2

### Brecha de conocimiento
Comprueba manejo de errores y reintentos.

### Reto
- Tema: E2E technologiesWork 202610091709
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de manejo de errores y reintentos en un sistema de procesamiento de pagos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de puntos de falla — objetivo: Identificar los puntos de falla en el sistema de procesamiento de pagos y definir las estrategias de manejo de errores. — entregable (NO resolver): Documento que describe los puntos de falla y las estrategias de manejo de errores.
- Fase 2: Implementación de reintentos — objetivo: Implementar la lógica de reintentos para manejar los errores transitorios en el sistema de procesamiento de pagos. — entregable (NO resolver): Código que implementa la lógica de reintentos para manejar los errores transitorios.
- Fase 3: Evaluación y optimización — objetivo: Evaluar la implementación de manejo de errores y reintentos y optimizar el rendimiento del sistema. — entregable (NO resolver): Reporte que evalúa la implementación de manejo de errores y reintentos y propone mejoras.

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

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de procesamiento de pagos con manejo de errores y reintentos</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
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

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-micrometer</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- PostgreSQL -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.9.1</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
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

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pragma.payments")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: payments-service
  datasource:
    url: jdbc:postgresql://localhost:5432/payments_db
    username: payments_user
    password: payments_pass
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: false

server:
  port: 8080
  servlet:
    context-path: /api/v1

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
    ratelimiters:
      enabled: true

resilience4j:
  circuitbreaker:
    configs:
      default:
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        registerHealthIndicator: true
    instances:
      riskBureau:
        baseConfig: default
        failureRateThreshold: 30
        waitDurationInOpenState: 10s
      bankCore:
        baseConfig: default
        failureRateThreshold: 40
        waitDurationInOpenState: 8s
      fraudEngine:
        baseConfig: default
        failureRateThreshold: 25
        waitDurationInOpenState: 12s
      creditOriginator:
        baseConfig: default
        failureRateThreshold: 35
        waitDurationInOpenState: 7s

  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 500ms
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - com.pragma.payments.domain.exception.RiskBureauException
          - com.pragma.payments.domain.exception.FraudEngineException
    instances:
      riskBureau:
        baseConfig: default
        maxAttempts: 4
        waitDuration: 1s
      bankCore:
        baseConfig: default
        maxAttempts: 3
        waitDuration: 800ms
      fraudEngine:
        baseConfig: default
        maxAttempts: 5
        waitDuration: 1s
      creditOriginator:
        baseConfig: default
        maxAttempts: 3
        waitDuration: 600ms

  bulkhead:
    configs:
      default:
        maxConcurrentCalls: 25
        maxWaitDuration: 0
    instances:
      riskBureau:
        baseConfig: default
        maxConcurrentCalls: 15
      bankCore:
        baseConfig: default
        maxConcurrentCalls: 20
      fraudEngine:
        baseConfig: default
        maxConcurrentCalls: 10
      creditOriginator:
        baseConfig: default
        maxConcurrentCalls: 18

logging:
  level:
    com.pragma.payments: INFO
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    io.github.resilience4j: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: ./logs/payments-service.log
    max-size: 10MB
    max-history: 7

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Payment.java ===
package com.pragma.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Payment(
    UUID id,
    @NotNull
    @Size(min = 1, max = 50)
    String accountId,
    @NotNull
    @Positive
    BigDecimal amount,
    @NotNull
    PaymentStatus status,
    @NotNull
    @Size(min = 3, max = 3)
    String currency,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    @Size(max = 255)
    String fraudCheckReference,
    @Size(max = 255)
    String riskBureauReference,
    @Size(max = 255)
    String bankCoreReference,
    int retryCount,
    LocalDateTime lastRetryAt
) {
    public Payment {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
        if (retryCount < 0) {
            retryCount = 0;
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            newStatus,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public Payment withRetry() {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.retryCount + 1,
            LocalDateTime.now()
        );
    }

    public Payment withFraudCheckReference(String reference) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            reference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public Payment withRiskBureauReference(String reference) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            reference,
            this.bankCoreReference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public Payment withBankCoreReference(String reference) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            this.riskBureauReference,
            reference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public boolean isRetryAllowed(int maxRetries) {
        return this.retryCount < maxRetries && this.status != PaymentStatus.COMPLETED && this.status != PaymentStatus.FAILED;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/ports/CreditOriginatorPort.java ===
package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface CreditOriginatorPort {
    /**
     * Envía una solicitud de crédito al originador de créditos para su procesamiento.
     * @param payment El pago que contiene la información necesaria para procesar el crédito.
     * @return Mono que emite el pago actualizado con la referencia del originador.
     * @throws com.pragma.payments.domain.exception.PaymentProcessingException si ocurre un error durante el procesamiento.
     */
    Mono<Payment> processCredit(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/ports/FraudEnginePort.java ===
package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface FraudEnginePort {
    /**
     * Realiza una verificación antifraude para el pago dado.
     * @param payment El pago que se debe verificar.
     * @return Mono que emite el pago actualizado con la referencia de la verificación antifraude.
     * @throws com.pragma.payments.domain.exception.FraudEngineException si ocurre un error durante la verificación.
     */
    Mono<Payment> checkForFraud(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/PaymentStatus.java ===
package com.pragma.payments.domain.model;

public enum PaymentStatus {
    PENDING,
    FRAUD_CHECK_IN_PROGRESS,
    FRAUD_CHECK_COMPLETED,
    RISK_ASSESSMENT_IN_PROGRESS,
    RISK_ASSESSMENT_COMPLETED,
    CREDIT_PROCESSING_IN_PROGRESS,
    CREDIT_PROCESSING_COMPLETED,
    BANK_CORE_PROCESSING_IN_PROGRESS,
    COMPLETED,
    FAILED
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/ports/RiskBureauPort.java ===
package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el buró de riesgos.
 * Define la abstracción que el dominio usa para consultar información de riesgo crediticio.
 * La implementación concreta se encuentra en la capa de infraestructura.
 */
public interface RiskBureauPort {

    /**
     * Consulta el buró de riesgos para obtener la calificación de riesgo del cliente.
     * @param payment el pago con información del cliente a evaluar
     * @return Mono con el pago actualizado incluyendo la referencia del buró de riesgos
     */
    Mono<Payment> consultRiskBureau(Payment payment);

    /**
     * Verifica si el cliente cumple con los requisitos mínimos de riesgo.
     * @param payment el pago a verificar
     * @return Mono que emite true si el riesgo es aceptable, false en caso contrario
     */
    Mono<Boolean> isRiskAcceptable(Payment payment);

    /**
     * Obtiene el puntaje de riesgo del cliente desde el buró.
     * @param payment el pago con información del cliente
     * @return Mono con el puntaje de riesgo (0-1000)
     */
    Mono<Integer> getRiskScore(Payment payment);

    /**
     * Enum interno para categorizar el nivel de riesgo.
     */
    enum RiskLevel {
        LOW(0, 300),
        MEDIUM(301, 600),
        HIGH(601, 800),
        VERY_HIGH(801, 1000);

        private final int minScore;
        private final int maxScore;

        RiskLevel(int minScore, int maxScore) {
            this.minScore = minScore;
            this.maxScore = maxScore;
        }

        public boolean containsScore(int score) {
            return score >= minScore && score <= maxScore;
        }

        public static RiskLevel fromScore(int score) {
            for (RiskLevel level : values()) {
                if (level.containsScore(score)) {
                    return level;
                }
            }
            return VERY_HIGH;
        }
    }

    /**
     * Exception específica para errores del buró de riesgos.
     */
    class RiskBureauException extends RuntimeException {
        private final String customerId;
        private final PaymentStatus lastStatus;

        public RiskBureauException(String message, String customerId, PaymentStatus lastStatus) {
            super(message);
            this.customerId = customerId;
            this.lastStatus = lastStatus;
        }

        public RiskBureauException(String message, Throwable cause, String customerId, PaymentStatus lastStatus) {
            super(message, cause);
            this.customerId = customerId;
            this.lastStatus = lastStatus;
        }

        public String getCustomerId() {
            return customerId;
        }

        public PaymentStatus getLastStatus() {
            return lastStatus;
        }
    }

    /**
     * Resultado de la consulta de riesgo con detalles adicionales.
     */
    record RiskConsultationResult(
        String customerId,
        int riskScore,
        RiskLevel riskLevel,
        boolean isAcceptable,
        String bureauReference,
        long consultationTimestamp
    ) {
        public static RiskConsultationResult create(String customerId, int score, String reference) {
            RiskLevel level = RiskLevel.fromScore(score);
            return new RiskConsultationResult(
                customerId,
                score,
                level,
                level == RiskLevel.LOW || level == RiskLevel.MEDIUM,
                reference,
                System.currentTimeMillis()
            );
        }

        public boolean requiresManualApproval() {
            return riskLevel == RiskLevel.HIGH || riskLevel == RiskLevel.VERY_HIGH;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/ports/BankCorePort.java ===
package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el core bancario.
 * Define la abstracción que el dominio usa para procesar pagos a través del sistema bancario.
 * La implementación concreta se encuentra en la capa de infraestructura.
 */
public interface BankCorePort {

    /**
     * Procesa el pago a través del core bancario.
     * @param payment el pago a procesar
     * @return Mono con el pago actualizado incluyendo la referencia del core bancario
     */
    Mono<Payment> processPayment(Payment payment);

    /**
     * Consulta el estado de una transacción en el core bancario.
     * @param payment el pago con la referencia del core bancario
     * @return Mono con el estado actual del pago
     */
    Mono<PaymentStatus> queryTransactionStatus(Payment payment);

    /**
     * Reversa un pago previamente procesado.
     * @param payment el pago a reversar
     * @return Mono con el pago en estado de reversa
     */
    Mono<Payment> reversePayment(Payment payment);

    /**
     * Confirma que el pago fue completado exitosamente.
     * @param payment el pago a confirmar
     * @return Mono con el pago confirmado
     */
    Mono<Payment> confirmPayment(Payment payment);

    /**
     * Verifica la disponibilidad del core bancario.
     * @return Mono que emite true si el sistema está disponible
     */
    Mono<Boolean> isAvailable();

    /**
     * Enum para los tipos de operación bancaria.
     */
    enum BankOperationType {
        DEBIT,
        CREDIT,
        TRANSFER,
        REVERSAL,
        REFUND
    }

    /**
     * Exception específica para errores del core bancario.
     */
    class BankCoreException extends RuntimeException {
        private final String transactionReference;
        private final PaymentStatus lastStatus;
        private final BankOperationType operationType;

        public BankCoreException(String message, String transactionReference, 
                                  PaymentStatus lastStatus, BankOperationType operationType) {
            super(message);
            this.transactionReference = transactionReference;
            this.lastStatus = lastStatus;
            this.operationType = operationType;
        }

        public BankCoreException(String message, Throwable cause, String transactionReference,
                                  PaymentStatus lastStatus, BankOperationType operationType) {
            super(message, cause);
            this.transactionReference = transactionReference;
            this.lastStatus = lastStatus;
            this.operationType = operationType;
        }

        public String getTransactionReference() {
            return transactionReference;
        }

        public PaymentStatus getLastStatus() {
            return lastStatus;
        }

        public BankOperationType getOperationType() {
            return operationType;
        }

        public boolean isRetryable() {
            return this.getCause() instanceof java.io.IOException ||
                   this.getCause() instanceof java.util.concurrent.TimeoutException;
        }
    }

    /**
     * Resultado de la operación bancaria con detalles de la transacción.
     */
    record BankTransactionResult(
        String transactionReference,
        PaymentStatus status,
        BankOperationType operationType,
        long processedAt,
        String authorizationCode,
        String responseCode,
        String responseMessage
    ) {
        public boolean isSuccessful() {
            return status == PaymentStatus.APPROVED || status == PaymentStatus.COMPLETED;
        }

        public boolean requiresReversal() {
            return status == PaymentStatus.FAILED || status == PaymentStatus.REJECTED;
        }

        public static BankTransactionResult pending(String reference, BankOperationType operationType) {
            return new BankTransactionResult(
                reference,
                PaymentStatus.PENDING,
                operationType,
                System.currentTimeMillis(),
                null,
                "PENDING",
                "Transaction is being processed"
            );
        }

        public static BankTransactionResult success(String reference, BankOperationType operationType,
                                                     String authCode, String responseCode) {
            return new BankTransactionResult(
                reference,
                PaymentStatus.APPROVED,
                operationType,
                System.currentTimeMillis(),
                authCode,
                responseCode,
                "Transaction approved"
            );
        }

        public static BankTransactionResult failure(String reference, BankOperationType operationType,
                                                     String responseCode, String message) {
            return new BankTransactionResult(
                reference,
                PaymentStatus.FAILED,
                operationType,
                System.currentTimeMillis(),
                null,
                responseCode,
                message
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/PaymentProcessingException.java ===
package com.pragma.payments.domain.exception;


import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;

/**
 * Excepción personalizada para errores en el procesamiento de pagos.
 * Representa errores genéricos que pueden ocurrir durante el flujo de procesamiento.
 */
public class PaymentProcessingException extends RuntimeException {

    private final String paymentId;
    private final PaymentStatus currentStatus;
    private final PaymentStatus targetStatus;
    private final String failureReason;
    private final boolean retryable;

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.currentStatus = null;
        this.targetStatus = null;
        this.failureReason = message;
        this.retryable = false;
    }

    public PaymentProcessingException(String message, String paymentId) {
        super(message);
        this.paymentId = paymentId;
        this.currentStatus = null;
        this.targetStatus = null;
        this.failureReason = message;
        this.retryable = false;
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.currentStatus = null;
        this.targetStatus = null;
        this.failureReason = message;
        this.retryable = determineRetryability(cause);
    }

    public PaymentProcessingException(String message, String paymentId, 
                                       PaymentStatus currentStatus, PaymentStatus targetStatus) {
        super(message);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = message;
        this.retryable = false;
    }

    public PaymentProcessingException(String message, Throwable cause, String paymentId,
                                       PaymentStatus currentStatus, PaymentStatus targetStatus) {
        super(message, cause);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = message;
        this.retryable = determineRetryability(cause);
    }

    public PaymentProcessingException(String message, String paymentId, 
                                       PaymentStatus currentStatus, PaymentStatus targetStatus,
                                       String failureReason, boolean retryable) {
        super(message);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = failureReason;
        this.retryable = retryable;
    }

    public PaymentProcessingException(String message, Throwable cause, String paymentId,
                                       PaymentStatus currentStatus, PaymentStatus targetStatus,
                                       String failureReason, boolean retryable) {
        super(message, cause);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = failureReason;
        this.retryable = retryable;
    }

    private static boolean determineRetryability(Throwable cause) {
        if (cause == null) {
            return false;
        }
        String exceptionClassName = cause.getClass().getName();
        return exceptionClassName.contains("TimeoutException") ||
               exceptionClassName.contains("ConnectionException") ||
               exceptionClassName.contains("SocketException") ||
               exceptionClassName.contains("ServiceUnavailableException");
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getCurrentStatus() {
        return currentStatus;
    }

    public PaymentStatus getTargetStatus() {
        return targetStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public boolean isRetryable() {
        return retryable;
    }

    public boolean hasPaymentId() {
        return paymentId != null && !paymentId.isBlank();
    }

    public boolean hasStatusInformation() {
        return currentStatus != null || targetStatus != null;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentProcessingException: ").append(super.getMessage());
        if (paymentId != null) {
            sb.append(" | PaymentId: ").append(paymentId);
        }
        if (currentStatus != null) {
            sb.append(" | CurrentStatus: ").append(currentStatus);
        }
        if (targetStatus != null) {
            sb.append(" | TargetStatus: ").append(targetStatus);
        }
        sb.append(" | Retryable: ").append(retryable);
        return sb.toString();
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Payment Processing Error ===\n");
        sb.append("Message: ").append(super.getMessage()).append("\n");
        if (paymentId != null) {
            sb.append("Payment ID: ").append(paymentId).append("\n");
        }
        if (currentStatus != null) {
            sb.append("Current Status: ").append(currentStatus).append("\n");
        }
        if (targetStatus != null) {
            sb.append("Target Status: ").append(targetStatus).append("\n");
        }
        if (failureReason != null) {
            sb.append("Failure Reason: ").append(failureReason).append("\n");
        }
        sb.append("Retryable: ").append(retryable).append("\n");
        if (getCause() != null) {
            sb.append("Caused by: ").append(getCause().getClass().getName());
            sb.append(": ").append(getCause().getMessage()).append("\n");
        }
        return sb.toString();
    }

    public static PaymentProcessingException retryable(String message, String paymentId) {
        return new PaymentProcessingException(
            message,
            (Throwable) null,
            paymentId,
            null,
            null,
            message,
            true
        );
    }

    public static PaymentProcessingException nonRetryable(String message, String paymentId) {
        return new PaymentProcessingException(
            message,
            (Throwable) null,
            paymentId,
            null,
            null,
            message,
            false
        );
    }

    public static PaymentProcessingException withStatus(String message, String paymentId,
                                                         PaymentStatus current, PaymentStatus target) {
        return new PaymentProcessingException(
            message,
            paymentId,
            current,
            target,
            message,
            false
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/FraudEngineException.java ===
package com.pragma.payments.domain.exception;

public class FraudEngineException extends RuntimeException {

    private final String fraudCheckReference;
    private final String paymentId;

    public FraudEngineException(String message) {
        super(message);
        this.fraudCheckReference = null;
        this.paymentId = null;
    }

    public FraudEngineException(String message, String paymentId) {
        super(message);
        this.fraudCheckReference = null;
        this.paymentId = paymentId;
    }

    public FraudEngineException(String message, String paymentId, String fraudCheckReference) {
        super(message);
        this.paymentId = paymentId;
        this.fraudCheckReference = fraudCheckReference;
    }

    public FraudEngineException(String message, Throwable cause) {
        super(message, cause);
        this.fraudCheckReference = null;
        this.paymentId = null;
    }

    public FraudEngineException(String message, Throwable cause, String paymentId) {
        super(message, cause);
        this.paymentId = paymentId;
        this.fraudCheckReference = null;
    }

    public FraudEngineException(String message, Throwable cause, String paymentId, String fraudCheckReference) {
        super(message, cause);
        this.paymentId = paymentId;
        this.fraudCheckReference = fraudCheckReference;
    }

    public String getFraudCheckReference() {
        return fraudCheckReference;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public boolean hasFraudCheckReference() {
        return fraudCheckReference != null && !fraudCheckReference.isBlank();
    }

    public boolean hasPaymentId() {
        return paymentId != null && !paymentId.isBlank();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/RiskBureauException.java ===
package com.pragma.payments.domain.exception;

public class RiskBureauException extends RuntimeException {

    private final String riskReference;
    private final String paymentId;
    private final String customerId;

    public RiskBureauException(String message) {
        super(message);
        this.riskReference = null;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, String paymentId) {
        super(message);
        this.riskReference = null;
        this.paymentId = paymentId;
        this.customerId = null;
    }

    public RiskBureauException(String message, String paymentId, String customerId) {
        super(message);
        this.riskReference = null;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public RiskBureauException(String message, String paymentId, String customerId, String riskReference) {
        super(message);
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.riskReference = riskReference;
    }

    public RiskBureauException(String message, Throwable cause) {
        super(message, cause);
        this.riskReference = null;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, Throwable cause, String paymentId) {
        super(message, cause);
        this.paymentId = paymentId;
        this.riskReference = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, Throwable cause, String paymentId, String customerId) {
        super(message, cause);
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.riskReference = null;
    }

    public RiskBureauException(String message, Throwable cause, String paymentId, String customerId, String riskReference) {
        super(message, cause);
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.riskReference = riskReference;
    }

    public String getRiskReference() {
        return riskReference;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean hasRiskReference() {
        return riskReference != null && !riskReference.isBlank();
    }

    public boolean hasPaymentId() {
        return paymentId != null && !paymentId.isBlank();
    }

    public boolean hasCustomerId() {
        return customerId != null && !customerId.isBlank();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentRequest.java ===
package com.pragma.payments.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de procesamiento de pago")
public record PaymentRequest(

    @Schema(description = "Identificador único del pago", example = "PAY-2024-001234")
    @NotBlank(message = "El identificador del pago es requerido")
    @Size(min = 5, max = 50, message = "El identificador debe tener entre 5 y 50 caracteres")
    String paymentId,

    @Schema(description = "Monto del pago", example = "1500.00")
    @NotNull(message = "El monto del pago es requerido")
    @Positive(message = "El monto debe ser mayor a cero")
    Double amount,

    @Schema(description = "Código de moneda ISO 4217", example = "USD")
    @NotBlank(message = "La moneda es requerida")
    @Size(min = 3, max = 3, message = "El código de moneda debe tener 3 caracteres")
    String currency,

    @Schema(description = "Identificador del cliente", example = "CUST-98765")
    @NotBlank(message = "El identificador del cliente es requerido")
    @Size(min = 5, max = 30, message = "El identificador del cliente debe tener entre 5 y 30 caracteres")
    String customerId,

    @Schema(description = "Método de pago", example = "CREDIT_CARD")
    @NotBlank(message = "El método de pago es requerido")
    String paymentMethod,

    @Schema(description = "Descripción del pago", example = "Compra en tienda en línea")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String description,

    @Schema(description = "Número de referencia externo", example = "REF-EXT-2024-56789")
    @Size(max = 50, message = "La referencia externa no puede exceder 50 caracteres")
    String externalReference
) {
    public PaymentRequest {
        if (currency != null) {
            currency = currency.toUpperCase();
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/rest/dto/PaymentResponse.java ===
package com.pragma.payments.infrastructure.rest.dto;


import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(
    UUID paymentId,
    String externalReference,
    BigDecimal amount,
    String currency,
    PaymentStatus status,
    String fraudCheckReference,
    String riskBureauReference,
    String bankCoreReference,
    String failureReason,
    int retryCount,
    LocalDateTime createdAt,
    LocalDateTime processedAt,
    String clientId,
    String merchantId
) {
    public static PaymentResponse fromPayment(com.pragma.payments.domain.model.Payment payment) {
        return new PaymentResponse(
            payment.id(),
            payment.externalReference(),
            payment.amount(),
            payment.currency(),
            payment.status(),
            payment.fraudCheckReference().orElse(null),
            payment.riskBureauReference().orElse(null),
            payment.bankCoreReference().orElse(null),
            payment.failureReason().orElse(null),
            payment.retryCount(),
            payment.createdAt(),
            payment.processedAt().orElse(null),
            payment.clientId(),
            payment.merchantId()
        );
    }

    public static PaymentResponse pending(UUID paymentId, String externalReference, BigDecimal amount, 
                                          String currency, String clientId, String merchantId) {
        return new PaymentResponse(
            paymentId,
            externalReference,
            amount,
            currency,
            PaymentStatus.PENDING,
            null,
            null,
            null,
            null,
            0,
            LocalDateTime.now(),
            null,
            clientId,
            merchantId
        );
    }

    public boolean isSuccessful() {
        return status == PaymentStatus.APPROVED || status == PaymentStatus.COMPLETED;
    }

    public boolean isFailed() {
        return status == PaymentStatus.FAILED || status == PaymentStatus.REJECTED;
    }

    public boolean isPending() {
        return status == PaymentStatus.PENDING || status == PaymentStatus.PROCESSING;
    }

    public boolean canRetry() {
        return (status == PaymentStatus.FAILED || status == PaymentStatus.TIMEOUT) 
               && retryCount < 3;
    }

    public PaymentResponse withUpdatedStatus(PaymentStatus newStatus) {
        return new PaymentResponse(
            this.paymentId,
            this.externalReference,
            this.amount,
            this.currency,
            newStatus,
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.failureReason,
            this.retryCount,
            this.createdAt,
            this.processedAt,
            this.clientId,
            this.merchantId
        );
    }

    public PaymentResponse withProcessedAt(LocalDateTime processedAt) {
        return new PaymentResponse(
            this.paymentId,
            this.externalReference,
            this.amount,
            this.currency,
            this.status,
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.failureReason,
            this.retryCount,
            this.createdAt,
            processedAt,
            this.clientId,
            this.merchantId
        );
    }

    public PaymentResponse withFailureReason(String reason) {
        return new PaymentResponse(
            this.paymentId,
            this.externalReference,
            this.amount,
            this.currency,
            this.status,
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            reason,
            this.retryCount,
            this.createdAt,
            this.processedAt,
            this.clientId,
            this.merchantId
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/usecase/PaymentProcessingUseCase.java ===
package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.ports.BankCorePort;
import com.pragma.payments.domain.ports.CreditOriginatorPort;
import com.pragma.payments.domain.ports.FraudEnginePort;
import com.pragma.payments.domain.ports.RiskBureauPort;
import com.pragma.payments.infrastructure.rest.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class PaymentProcessingUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessingUseCase.class);
    private static final int MAX_RETRIES = 3;
    private static final Duration RETRY_BACKOFF = Duration.ofSeconds(2);

    private final CreditOriginatorPort creditOriginatorPort;
    private final FraudEnginePort fraudEnginePort;
    private final RiskBureauPort riskBureauPort;
    private final BankCorePort bankCorePort;

    public PaymentProcessingUseCase(
            CreditOriginatorPort creditOriginatorPort,
            FraudEnginePort fraudEnginePort,
            RiskBureauPort riskBureauPort,
            BankCorePort bankCorePort) {
        this.creditOriginatorPort = creditOriginatorPort;
        this.fraudEnginePort = fraudEnginePort;
        this.riskBureauPort = riskBureauPort;
        this.bankCorePort = bankCorePort;
    }

    public Mono<PaymentResponse> processPayment(Payment payment) {
        log.info("Iniciando procesamiento de pago: {} con monto {} {}", 
                payment.id(), payment.amount(), payment.currency());

        return executePaymentPipeline(payment)
            .doOnSuccess(result -> log.info("Pago {} procesado exitosamente con estado: {}", 
                    result.paymentId(), result.status()))
            .doOnError(error -> log.error("Error en procesamiento de pago {}: {}", 
                    payment.id(), error.getMessage(), error))
            .onErrorResume(error -> handlePaymentError(payment, error));
    }

    private Mono<PaymentResponse> executePaymentPipeline(Payment payment) {
        return creditOriginatorPort.processCredit(payment)
            .flatMap(this::validateWithFraudEngine)
            .flatMap(this::enrichWithRiskBureau)
            .flatMap(this::authorizeWithBankCore)
            .map(PaymentResponse::fromPayment)
            .timeout(Duration.ofSeconds(2), Mono.error(
                new PaymentProcessingException("Timeout en procesamiento de pago", "TIMEOUT")));
    }

    private Mono<Payment> validateWithFraudEngine(Payment payment) {
        log.debug("Validando pago {} con motor antifraude", payment.id());
        return fraudEnginePort.checkForFraud(payment)
            .doOnNext(result -> log.debug("Resultado de antifraude para {}: {}", 
                    result.id(), result.status()));
    }

    private Mono<Payment> enrichWithRiskBureau(Payment payment) {
        log.debug("Enriqueciendo pago {} con datos del buró de riesgos", payment.id());
        return riskBureauPort.enrichWithRiskData(payment)
            .doOnNext(result -> log.debug("Datos de riesgo obtenidos para {}", result.id()))
            .onErrorResume(error -> {
                log.warn("Error al obtener datos de riesgo para {}, continuando: {}", 
                        payment.id(), error.getMessage());
                return Mono.just(payment);
            });
    }

    private Mono<Payment> authorizeWithBankCore(Payment payment) {
        log.debug("Autorizando pago {} con core bancario", payment.id());
        return bankCorePort.authorizePayment(payment)
            .doOnNext(result -> log.debug("Autorización bancaria para {}: {}", 
                    result.id(), result.status()));
    }

    private Mono<PaymentResponse> handlePaymentError(Payment payment, Throwable error) {
        String errorCode = extractErrorCode(error);
        String errorMessage = error.getMessage();

        log.warn("Manejando error para pago {}: código={}, mensaje={}", 
                payment.id(), errorCode, errorMessage);

        if (payment.isRetryAllowed(MAX_RETRIES)) {
            log.info("Pago {} elegible para reintento. Retry count: {}", 
                    payment.id(), payment.retryCount() + 1);
            return retryPayment(payment, errorCode, errorMessage);
        }

        log.error("Pago {} no elegible para más reintentos. Marcando como fallido.", payment.id());
        Payment failedPayment = payment
            .withStatus(PaymentStatus.FAILED)
            .withFailureReason(errorMessage);
        return Mono.just(PaymentResponse.fromPayment(failedPayment));
    }

    private Mono<PaymentResponse> retryPayment(Payment payment, String errorCode, String errorMessage) {
        Payment retryPayment = payment.withRetry();

        return Mono.delay(RETRY_BACKOFF)
            .flatMap(tick -> {
                log.info("Ejecutando reintento {} para pago {}", 
                        retryPayment.retryCount(), retryPayment.id());
                return executePaymentPipeline(retryPayment);
            })
            .retryWhen(Retry.backoff(MAX_RETRIES, RETRY_BACKOFF)
                .filter(throwable -> isRetryableError(throwable))
                .doBeforeRetry(retrySignal -> {
                    log.warn("Reintento {} para pago {} después de error: {}",
                            retrySignal.totalRetries() + 1,
                            payment.id(),
                            retrySignal.failure().getMessage());
                })
                .onRetryExhaustedThrow((spec, signal) -> {
                    log.error("Reintentos agotados para pago {}", payment.id());
                    return new PaymentProcessingException(
                        "Reintentos agotados para el pago", "RETRY_EXHAUSTED");
                }))
            .onErrorResume(error -> {
                log.error("Todos los reintentos fallaron para pago {}", payment.id());
                Payment failedPayment = payment
                    .withStatus(PaymentStatus.FAILED)
                    .withFailureReason("Reintentos agotados: " + error.getMessage());
                return Mono.just(PaymentResponse.fromPayment(failedPayment));
            });
    }

    private boolean isRetryableError(Throwable error) {
        String errorCode = extractErrorCode(error);
        return "TIMEOUT".equals(errorCode) 
               || "CONNECTION_ERROR".equals(errorCode)
               || "SERVICE_UNAVAILABLE".equals(errorCode)
               || "RATE_LIMIT_EXCEEDED".equals(errorCode);
    }

    private String extractErrorCode(Throwable error) {
        if (error instanceof PaymentProcessingException paymentError) {
            return paymentError.getErrorCode();
        }
        return "UNKNOWN_ERROR";
    }

    public Mono<PaymentResponse> getPaymentStatus(UUID paymentId) {
        log.info("Consultando estado del pago: {}", paymentId);
        return creditOriginatorPort.findById(paymentId)
            .map(PaymentResponse::fromPayment)
            .switchIfEmpty(Mono.error(new PaymentProcessingException(
                "Pago no encontrado: " + paymentId, "NOT_FOUND")));
    }

    public Mono<PaymentResponse> cancelPayment(UUID paymentId) {
        log.info("Cancelando pago: {}", paymentId);
        return creditOriginatorPort.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() == PaymentStatus.COMPLETED || 
                    payment.status() == PaymentStatus.APPROVED) {
                    return Mono.error(new PaymentProcessingException(
                        "No se puede cancelar un pago ya aprobado", "CANNOT_CANCEL"));
                }
                Payment cancelledPayment = payment.withStatus(PaymentStatus.CANCELLED);
                return creditOriginatorPort.processCredit(cancelledPayment);
            })
            .map(PaymentResponse::fromPayment);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapter.java ===
package com.pragma.payments.infrastructure.adapters;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.CreditOriginatorPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreditOriginatorAdapter implements CreditOriginatorPort {

    private static final Logger log = LoggerFactory.getLogger(CreditOriginatorAdapter.class);
    private static final Map<UUID, Payment> PAYMENT_STORE = new ConcurrentHashMap<>();

    @Override
    @CircuitBreaker(name = "creditOriginator", fallbackMethod = "processCreditFallback")
    @Retry(name = "creditOriginatorRetry")
    public Mono<Payment> processCredit(Payment payment) {
        log.info("Procesando crédito para pago: {} - Monto: {} {}",
                payment.id(), payment.amount(), payment.currency());

        return Mono.fromCallable(() -> {
            simulateCreditProcessing(payment);
            Payment processedPayment = payment.withStatus(determinePaymentStatus(payment));
            PAYMENT_STORE.put(processedPayment.id(), processedPayment);
            log.info("Crédito procesado exitosamente para pago: {}", processedPayment.id());
            return processedPayment;
        }).delayElement(Duration.ofMillis(100));
    }

    private void simulateCreditProcessing(Payment payment) throws InterruptedException {
        if (payment.externalReference().startsWith("ERROR")) {
            throw new PaymentProcessingException(
                "Error simulado en originador de crédito", "CREDIT_PROCESSING_ERROR");
        }
        if (payment.externalReference().startsWith("TIMEOUT")) {
            throw new PaymentProcessingException(
                "Timeout simulado en originador de crédito", "TIMEOUT");
        }
        if (payment.externalReference().startsWith("UNAVAILABLE")) {
            throw new PaymentProcessingException(
                "Servicio no disponible", "SERVICE_UNAVAILABLE");
        }
    }

    private com.pragma.payments.domain.model.PaymentStatus determinePaymentStatus(Payment payment) {
        if (payment.amount().compareTo(payment.maximumAllowedAmount()) > 0) {
            return com.pragma.payments.domain.model.PaymentStatus.REJECTED;
        }
        if (payment.clientCreditScore() < 500) {
            return com.pragma.payments.domain.model.PaymentStatus.REJECTED;
        }
        return com.pragma.payments.domain.model.PaymentStatus.APPROVED;
    }

    @SuppressWarnings("unused")
    private Mono<Payment> processCreditFallback(Payment payment, Throwable error) {
        log.warn("Fallback activado para pago {}: {}", payment.id(), error.getMessage());
        
        if (error instanceof PaymentProcessingException paymentError) {
            String errorCode = paymentError.getErrorCode();
            
            if ("TIMEOUT".equals(errorCode)) {
                return Mono.just(payment.withStatus(
                    com.pragma.payments.domain.model.PaymentStatus.TIMEOUT));
            }
            if ("SERVICE_UNAVAILABLE".equals(errorCode)) {
                return Mono.just(payment.withStatus(
                    com.pragma.payments.domain.model.PaymentStatus.PENDING))
                    .delayElement(Duration.ofSeconds(5))
                    .flatMap(this::processCredit);
            }
        }
        
        return Mono.just(payment.withStatus(
            com.pragma.payments.domain.model.PaymentStatus.FAILED)
            .withFailureReason("Error en originador de crédito: " + error.getMessage()));
    }

    @Override
    public Mono<Payment> findById(UUID paymentId) {
        log.debug("Buscando pago por ID: {}", paymentId);
        return Mono.fromCallable(() -> {
            Payment payment = PAYMENT_STORE.get(paymentId);
            if (payment == null) {
                throw new PaymentProcessingException(
                    "Pago no encontrado: " + paymentId, "NOT_FOUND");
            }
            return payment;
        });
    }

    @Override
    public Mono<Payment> updatePaymentStatus(UUID paymentId, 
            com.pragma.payments.domain.model.PaymentStatus newStatus) {
        log.info("Actualizando estado del pago {} a {}", paymentId, newStatus);
        return findById(paymentId)
            .map(payment -> {
                Payment updatedPayment = payment.withStatus(newStatus)
                    .withProcessedAt(java.time.LocalDateTime.now());
                PAYMENT_STORE.put(paymentId, updatedPayment);
                return updatedPayment;
            });
    }

    public Mono<Boolean> validateCreditLimit(String clientId, java.math.BigDecimal amount) {
        log.debug("Validando límite de crédito para cliente {} con monto {}", clientId, amount);
        return Mono.fromCallable(() -> {
            int clientHash = clientId.hashCode();
            java.math.BigDecimal limit = java.math.BigDecimal.valueOf(Math.abs(clientHash % 100000) + 10000);
            return amount.compareTo(limit) <= 0;
        });
    }

    public Mono<java.math.BigDecimal> getAvailableCredit(String clientId) {
        log.debug("Consultando crédito disponible para cliente: {}", clientId);
        return Mono.fromCallable(() -> {
            int clientHash = clientId.hashCode();
            return java.math.BigDecimal.valueOf(Math.abs(clientHash % 100000) + 5000);
        });
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/FraudEngineAdapter.java ===
package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.FraudEnginePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class FraudEngineAdapter implements FraudEnginePort {

    private static final Logger log = LoggerFactory.getLogger(FraudEngineAdapter.class);
    private static final double DEFAULT_FRAUD_THRESHOLD = 0.75;

    @Override
    @CircuitBreaker(name = "fraudEngineCircuitBreaker", fallbackMethod = "fallbackCheckForFraud")
    @Retry(name = "fraudEngineRetry")
    public Mono<Payment> checkForFraud(Payment payment) {
        log.info("Iniciando verificación antifraude para pago: {}", payment.id());
        
        return Mono.delay(Duration.ofMillis(100))
            .flatMap(tick -> {
                double riskScore = calculateRiskScore(payment);
                
                if (riskScore > DEFAULT_FRAUD_THRESHOLD) {
                    log.warn("Pago {} marcado como fraudulento. Score de riesgo: {}", 
                        payment.id(), riskScore);
                    return Mono.error(new FraudEngineException(
                        "Riesgo de fraude detectado para el pago: " + payment.id()));
                }
                
                String fraudReference = generateFraudReference();
                log.info("Verificación antifraude exitosa para pago {}. Referencia: {}", 
                    payment.id(), fraudReference);
                
                return Mono.just(payment.withFraudCheckReference(fraudReference));
            });
    }

    private double calculateRiskScore(Payment payment) {
        if (payment.amount() == null || payment.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return 1.0;
        }
        
        double amountFactor = payment.amount().doubleValue() > 10000 ? 0.4 : 0.1;
        double currencyFactor = payment.currency() != null && !"USD".equals(payment.currency()) 
            ? 0.2 : 0.0;
        double baseScore = 0.3;
        
        return Math.min(1.0, baseScore + amountFactor + currencyFactor);
    }

    private String generateFraudReference() {
        return "FE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @SuppressWarnings("unused")
    private Mono<Payment> fallbackCheckForFraud(Payment payment, Throwable throwable) {
        log.error("Fallback activado para verificación antifraude. Pago: {}, Error: {}", 
            payment.id(), throwable.getMessage());
        
        if (throwable instanceof FraudEngineException) {
            return Mono.error(throwable);
        }
        
        String fallbackReference = "FE-FALLBACK-" + System.currentTimeMillis();
        return Mono.just(payment.withFraudCheckReference(fallbackReference));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/RiskBureauAdapter.java ===
package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.RiskBureauPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);
    private static final Map<String, RiskCategory> RISK_CATEGORIES = new ConcurrentHashMap<>();
    
    static {
        RISK_CATEGORIES.put("LOW", RiskCategory.LOW);
        RISK_CATEGORIES.put("MEDIUM", RiskCategory.MEDIUM);
        RISK_CATEGORIES.put("HIGH", RiskCategory.HIGH);
    }

    @Override
    @CircuitBreaker(name = "riskBureauCircuitBreaker", fallbackMethod = "fallbackAssessRisk")
    @Retry(name = "riskBureauRetry")
    public Mono<Payment> assessRisk(Payment payment) {
        log.info("Iniciando evaluación de riesgo para pago: {}", payment.id());
        
        return Mono.delay(Duration.ofMillis(150))
            .flatMap(tick -> {
                RiskCategory category = calculateRiskCategory(payment);
                
                if (category == RiskCategory.HIGH) {
                    log.warn("Pago {} clasificado como alto riesgo. Categoría: {}", 
                        payment.id(), category);
                    return Mono.error(new RiskBureauException(
                        "Alto riesgo detectado para el pago: " + payment.id()));
                }
                
                String bureauReference = generateBureauReference();
                log.info("Evaluación de riesgo exitosa para pago {}. Categoría: {}, Referencia: {}", 
                    payment.id(), category, bureauReference);
                
                return Mono.just(payment.withRiskBureauReference(bureauReference));
            });
    }

    private RiskCategory calculateRiskCategory(Payment payment) {
        if (payment.amount() == null) {
            return RiskCategory.HIGH;
        }
        
        double amount = payment.amount().doubleValue();
        
        if (amount > 50000) {
            return RiskCategory.HIGH;
        } else if (amount > 10000) {
            return RiskCategory.MEDIUM;
        } else {
            return RiskCategory.LOW;
        }
    }

    private String generateBureauReference() {
        return "RB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @SuppressWarnings("unused")
    private Mono<Payment> fallbackAssessRisk(Payment payment, Throwable throwable) {
        log.error("Fallback activado para evaluación de riesgo. Pago: {}, Error: {}", 
            payment.id(), throwable.getMessage());
        
        if (throwable instanceof RiskBureauException) {
            return Mono.error(throwable);
        }
        
        String fallbackReference = "RB-FALLBACK-" + System.currentTimeMillis();
        return Mono.just(payment.withRiskBureauReference(fallbackReference));
    }
    
    public enum RiskCategory {
        LOW,
        MEDIUM,
        HIGH
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/BankCoreAdapter.java ===
package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.BankCorePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class BankCoreAdapter implements BankCorePort {

    private static final Logger log = LoggerFactory.getLogger(BankCoreAdapter.class);
    private static final String TRANSACTION_STATUS_APPROVED = "APPROVED";
    private static final String TRANSACTION_STATUS_REJECTED = "REJECTED";
    private static final String TRANSACTION_STATUS_PENDING = "PENDING";

    @Override
    @CircuitBreaker(name = "bankCoreCircuitBreaker", fallbackMethod = "fallbackProcessPayment")
    @Retry(name = "bankCoreRetry")
    public Mono<Payment> processPayment(Payment payment) {
        log.info("Iniciando procesamiento de pago en core bancario: {}", payment.id());
        
        return Mono.delay(Duration.ofMillis(200))
            .flatMap(tick -> {
                boolean isApproved = evaluateTransaction(payment);
                
                if (!isApproved) {
                    log.warn("Transacción rechazada por el core bancario para pago: {}", payment.id());
                    return Mono.error(new PaymentProcessingException(
                        "Transacción rechazada por el core bancario: " + payment.id()));
                }
                
                String bankReference = generateBankReference();
                log.info("Pago {} procesado exitosamente en core bancario. Referencia: {}", 
                    payment.id(), bankReference);
                
                return Mono.just(payment.withBankCoreReference(bankReference));
            });
    }

    private boolean evaluateTransaction(Payment payment) {
        if (payment.amount() == null || payment.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return false;
        }
        
        double amount = payment.amount().doubleValue();
        
        if (amount > 100000) {
            return false;
        }
        
        return true;
    }

    private String generateBankReference() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
    }

    @SuppressWarnings("unused")
    private Mono<Payment> fallbackProcessPayment(Payment payment, Throwable throwable) {
        log.error("Fallback activado para procesamiento en core bancario. Pago: {}, Error: {}", 
            payment.id(), throwable.getMessage());
        
        if (throwable instanceof PaymentProcessingException) {
            return Mono.error(throwable);
        }
        
        String fallbackReference = "TXN-FALLBACK-" + System.currentTimeMillis();
        return Mono.just(payment.withBankCoreReference(fallbackReference));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(Exception.class)
                .build();
        return RetryRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/rest/PaymentController.java ===
package com.pragma.payments.infrastructure.rest;

import com.pragma.payments.application.usecase.PaymentProcessingUseCase;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.infrastructure.rest.dto.PaymentRequest;
import com.pragma.payments.infrastructure.rest.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentProcessingUseCase paymentProcessingUseCase;

    public PaymentController(PaymentProcessingUseCase paymentProcessingUseCase) {
        this.paymentProcessingUseCase = paymentProcessingUseCase;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        log.info("Recibida solicitud de pago para el usuario: {} con monto: {}", 
                request.userId(), request.amount());

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.userId())
                .amount(request.amount())
                .currency(request.currency() != null ? request.currency() : "USD")
                .status(PaymentStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        Payment processedPayment = paymentProcessingUseCase.processPayment(payment);

        PaymentResponse response = new PaymentResponse(
                processedPayment.id(),
                processedPayment.userId(),
                processedPayment.amount(),
                processedPayment.currency(),
                processedPayment.status().name(),
                processedPayment.createdAt(),
                processedPayment.updatedAt()
        );

        HttpStatus status = determineHttpStatus(processedPayment.status());
        return ResponseEntity.status(status).body(response);
    }

    private HttpStatus determineHttpStatus(PaymentStatus status) {
        return switch (status) {
            case COMPLETED -> HttpStatus.OK;
            case PENDING -> HttpStatus.ACCEPTED;
            case FAILED -> HttpStatus.BAD_REQUEST;
            case FRAUD_REJECTED -> HttpStatus.FORBIDDEN;
            case RISK_REJECTED -> HttpStatus.PRECONDITION_FAILED;
            case BANK_REJECTED -> HttpStatus.PAYMENT_REQUIRED;
            case TIMEOUT -> HttpStatus.GATEWAY_TIMEOUT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/rest/GlobalExceptionHandler.java ===
package com.pragma.payments.infrastructure.rest;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.exception.RiskBureauException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentProcessingException(PaymentProcessingException ex) {
        log.error("Error procesando pago: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "PAYMENT_PROCESSING_ERROR",
                ex.getMessage(),
                ex.getErrorCode()
        );
    }

    @ExceptionHandler(FraudEngineException.class)
    public ResponseEntity<Map<String, Object>> handleFraudEngineException(FraudEngineException ex) {
        log.error("Error en motor antifraude: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "FRAUD_ENGINE_ERROR",
                ex.getMessage(),
                ex.getErrorCode()
        );
    }

    @ExceptionHandler(RiskBureauException.class)
    public ResponseEntity<Map<String, Object>> handleRiskBureauException(RiskBureauException ex) {
        log.error("Error en bureau de riesgos: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "RISK_BUREAU_ERROR",
                ex.getMessage(),
                ex.getErrorCode()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("Error de validación en request: {}", ex.getMessage());
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "VALIDATION_ERROR");
        response.put("message", "Los datos del pago no son válidos");
        response.put("details", fieldErrors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_ARGUMENT",
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        log.error("Error inesperado del sistema: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SYSTEM_ERROR",
                "Ha ocurrido un error inesperado. Por favor contacte al administrador.",
                null
        );
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(int status, String error, String message, String errorCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", status);
        response.put("error", error);
        response.put("message", message);
        if (errorCode != null) {
            response.put("errorCode", errorCode);
        }
        return ResponseEntity.status(status).body(response);
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/usecase/PaymentProcessingUseCaseTest.java ===
package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.ports.BankCorePort;
import com.pragma.payments.domain.ports.CreditOriginatorPort;
import com.pragma.payments.domain.ports.FraudEnginePort;
import com.pragma.payments.domain.ports.RiskBureauPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentProcessingUseCase - Tests Unitarios")
class PaymentProcessingUseCaseTest {

    @Mock
    private CreditOriginatorPort creditOriginatorPort;

    @Mock
    private FraudEnginePort fraudEnginePort;

    @Mock
    private RiskBureauPort riskBureauPort;

    @Mock
    private BankCorePort bankCorePort;

    private PaymentProcessingUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new PaymentProcessingUseCase(
            creditOriginatorPort,
            fraudEnginePort,
            riskBureauPort,
            bankCorePort
        );
    }

    private Payment createTestPayment() {
        return new Payment(
            UUID.randomUUID().toString(),
            "ACC-2024-001",
            "CUST-789",
            new BigDecimal("5000.00"),
            PaymentStatus.PENDING,
            null, null, null, null
        );
    }

    @Nested
    @DisplayName("Escenario: Flujo exitoso de procesamiento")
    class FlujoExitoso {

        @Test
        @DisplayName("Debe procesar el pago completamente cuando todos los servicios responden exitosamente")
        void debeProcesarPagoExitosamente() {
            Payment payment = createTestPayment();

            Payment paymentWithCredit = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("FRAUD-REF-001");
            Payment paymentWithRisk = paymentWithCredit.withStatus(PaymentStatus.RISK_ASSESSED)
                .withRiskBureauReference("RISK-REF-001");
            Payment paymentCompleted = paymentWithRisk.withStatus(PaymentStatus.COMPLETED)
                .withBankCoreReference("BANK-REF-001");

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(fraudEnginePort.checkForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(riskBureauPort.assessRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(bankCorePort.confirmPayment(any(Payment.class)))
                .thenReturn(Mono.just(paymentCompleted));

            StepVerifier.create(useCase.processPayment(payment))
                .expectNextMatches(result ->
                    result.status() == PaymentStatus.COMPLETED &&
                    result.fraudCheckReference() != null &&
                    result.riskBureauReference() != null &&
                    result.bankCoreReference() != null)
                .verifyComplete();

            verify(creditOriginatorPort, times(1)).processCredit(any(Payment.class));
            verify(fraudEnginePort, times(1)).checkForFraud(any(Payment.class));
            verify(riskBureauPort, times(1)).assessRisk(any(Payment.class));
            verify(bankCorePort, times(1)).confirmPayment(any(Payment.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Fallo en CreditOriginator con reintento")
    class FalloCreditOriginator {

        @Test
        @DisplayName("Debe reintentar cuando CreditOriginator falla temporalmente")
        void debeReintentarCreditOriginator() {
            Payment payment = createTestPayment();
            Payment paymentWithCredit = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("FRAUD-REF-001");

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error temporal de conexion")))
                .thenReturn(Mono.just(paymentWithCredit));
            when(fraudEnginePort.checkForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(riskBureauPort.assessRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit.withStatus(PaymentStatus.RISK_ASSESSED)
                    .withRiskBureauReference("RISK-REF-001")));
            when(bankCorePort.confirmPayment(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit.withStatus(PaymentStatus.COMPLETED)
                    .withBankCoreReference("BANK-REF-001")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectNextMatches(result -> result.status() == PaymentStatus.COMPLETED)
                .verifyComplete();

            verify(creditOriginatorPort, times(2)).processCredit(any(Payment.class));
        }

        @Test
        @DisplayName("Debe fallar definitivamente despues de reintentos agotados")
        void debeFallarDefinitivamente() {
            Payment payment = createTestPayment();

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error persistente")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();

            verify(creditOriginatorPort, atLeastOnce()).processCredit(any(Payment.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Fallo en FraudEngine")
    class FalloFraudEngine {

        @Test
        @DisplayName("Debe manejar excepcion cuando FraudEngine detecta fraude")
        void debeManejarFalloFraudEngine() {
            Payment payment = createTestPayment();
            Payment paymentWithCredit = payment.withStatus(PaymentStatus.CREDIT_INITIATED);

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(fraudEnginePort.checkForFraud(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Fraude detectado")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();

            verify(bankCorePort, never()).confirmPayment(any(Payment.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Reintentos configurables")
    class ReintentosConfigurables {

        @Test
        @DisplayName("Deberespectar el limite maximo de reintentos configurado")
        void debeRespetarLimiteReintentos() {
            Payment payment = createTestPayment();

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error persistente")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();

            verify(creditOriginatorPort, atLeastOnce()).processCredit(any(Payment.class));
        }

        @Test
        @DisplayName("Debe permitir reintentos solo cuando el pago lo permite")
        void debeVerificarSiRetryPermitido() {
            Payment payment = createTestPayment();

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error temporal")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapters/CreditOriginatorAdapterTest.java ===
package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.retry.annotation.Retry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreditOriginatorAdapter - Tests Unitarios")
class CreditOriginatorAdapterTest {

    @Mock
    private CreditOriginatorAdapter externalCreditService;

    @BeforeEach
    void setUp() {
        externalCreditService = mock(CreditOriginatorAdapter.class, withSettings()
            .extraInterfaces(CreditOriginatorAdapter.class));
    }

    private Payment createTestPayment() {
        return new Payment(
            UUID.randomUUID().toString(),
            "ACC-2024-001",
            "CUST-789",
            new BigDecimal("5000.00"),
            PaymentStatus.PENDING,
            null, null, null, null
        );
    }

    @Nested
    @DisplayName("Escenario: Proceso de credito exitoso")
    class ProcesoExitoso {

        @Test
        @DisplayName("Debe retornar pago con estado CREDIT_INITIATED cuando el servicio responde OK")
        void debeRetornarPagoConCreditoIniciado() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            Payment paymentWithCreditReference = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("CREDIT-REF-" + UUID.randomUUID());

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCreditReference));

            StepVerifier.create(adapter.processCredit(payment))
                .expectNextMatches(result ->
                    result.status() == PaymentStatus.CREDIT_INITIATED &&
                    result.fraudCheckReference() != null)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenario: Fallo temporal del servicio externo")
    class FalloTemporal {

        @Test
        @DisplayName("Debe reintentar automaticamente en caso de error temporal")
        void debeReintentarAutomaticamente() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();
            Payment paymentConReferencia = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("CREDIT-REF-001");

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Connection timeout")))
                .thenReturn(Mono.just(paymentConReferencia));

            StepVerifier.create(adapter.processCredit(payment))
                .expectNextMatches(result -> result.status() == PaymentStatus.CREDIT_INITIATED)
                .verifyComplete();

            verify(adapter, times(2)).processCredit(any(Payment.class));
        }

        @Test
        @DisplayName("Debe lanzar PaymentProcessingException cuando los reintentos se agotan")
        void debeLanzarExcepcionCuandoAgotaReintentos() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Servicio no disponible")));

            StepVerifier.create(adapter.processCredit(payment))
                .expectError(PaymentProcessingException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Circuit breaker abierto")
    class CircuitBreakerAbierto {

        @Test
        @DisplayName("Debefallar rapido cuando el circuit breaker esta abierto")
        void debeFallarRapidoConCircuitBreakerAbierto() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(CallNotPermittedException.createCallNotPermittedException(
                    io.github.resilience4j.circuitbreaker.CircuitBreaker.ofDefaults("test"))));

            StepVerifier.create(adapter.processCredit(payment))
                .expectError(PaymentProcessingException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Validacion de parametros")
    class ValidacionParametros {

        @Test
        @DisplayName("Debe lanzar excepcion cuando el pago es nulo")
        void debeValidarPagoNoNulo() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();

            StepVerifier.create(adapter.processCredit(null))
                .expectError(NullPointerException.class)
                .verify();
        }

        @Test
        @DisplayName("Debe validar que el monto sea positivo")
        void debeValidarMontoPositivo() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment paymentConMontoNegativo = new Payment(
                UUID.randomUUID().toString(),
                "ACC-001",
                "CUST-001",
                new BigDecimal("-100.00"),
                PaymentStatus.PENDING,
                null, null, null, null
            );

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new IllegalArgumentException("Monto debe ser positivo")));

            StepVerifier.create(adapter.processCredit(paymentConMontoNegativo))
                .expectError(IllegalArgumentException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Comportamiento con anotaciones de Resilience4j")
    class AnotacionesResilience4j {

        @Test
        @DisplayName("La anotacion @Retry debe estar presente en el metodo processCredit")
        void debeTenerAnotacionRetry() throws NoSuchMethodException {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            var method = CreditOriginatorAdapter.class.getMethod("processCredit", Payment.class);
            Retry retryAnnotation = method.getAnnotation(Retry.class);

            org.junit.jupiter.api.Assertions.assertNotNull(retryAnnotation,
                "El metodo debe tener la anotacion @Retry");
        }

        @Test
        @DisplayName("Debe manejar errores transitorios con backoff exponencial")
        void debeManejarErroresConBackoff() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error transitorio 1")))
                .thenReturn(Mono.error(new RuntimeException("Error transitorio 2")))
                .thenReturn(Mono.just(payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                    .withFraudCheckReference("FINAL-REF")));

            StepVerifier.create(adapter.processCredit(payment))
                .expectNextMatches(p -> p.status() == PaymentStatus.CREDIT_INITIATED)
                .verifyComplete();
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/rest/PaymentControllerTest.java ===
package com.pragma.payments.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.infrastructure.rest.dto.PaymentRequest;
import com.pragma.payments.infrastructure.rest.dto.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentController - Tests de Integracion")
class PaymentControllerTest {

    @Mock
    private PaymentController paymentController;

    private WebTestClient webTestClient;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(paymentController).build();
        objectMapper = new ObjectMapper();
    }

    private PaymentRequest createPaymentRequest() {
        PaymentRequest request = new PaymentRequest();
        request.setAccountNumber("ACC-2024-001");
        request.setCustomerId("CUST-789");
        request.setAmount(new BigDecimal("5000.00"));
        return request;
    }

    private Payment createTestPayment() {
        return new Payment(
            UUID.randomUUID().toString(),
            "ACC-2024-001",
            "CUST-789",
            new BigDecimal("5000.00"),
            PaymentStatus.COMPLETED,
            "FRAUD-REF-001", "RISK-REF-001", "BANK-REF-001", null
        );
    }

    @Nested
    @DisplayName("Escenario: Procesamiento exitoso de pago")
    class ProcesamientoExitoso {

        @Test
        @DisplayName("Debe retornar 200 OK cuando el pago se procesa correctamente")
        void debeRetornar200CuandoPagoExitoso() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.just(response));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.paymentId").isEqualTo(payment.getPaymentId())
                .jsonPath("$.status").isEqualTo("COMPLETED");
        }
    }

    @Nested
    @DisplayName("Escenario: Error de validacion en request")
    class ErrorValidacion {

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el request es invalido")
        void debeRetornar400CuandoRequestInvalido() {
            PaymentRequest requestInvalido = new PaymentRequest();
            requestInvalido.setAccountNumber("");
            requestInvalido.setCustomerId(null);
            requestInvalido.setAmount(BigDecimal.ZERO);

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(requestInvalido))
                .exchange()
                .expectStatus().isBadRequest();
        }

        @Test
        @DisplayName("Debe validar que el monto sea mayor a cero")
        void debeValidarMontoMayorCero() {
            PaymentRequest request = createPaymentRequest();
            request.setAmount(new BigDecimal("-100.00"));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest();
        }
    }

    @Nested
    @DisplayName("Escenario: Error en procesamiento de pago")
    class ErrorProcesamiento {

        @Test
        @DisplayName("Debe retornar 500 Internal Server Error cuando falla el procesamiento")
        void debeRetornar500CuandoFallaProcesamiento() {
            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.error(new PaymentProcessingException("Error al procesar pago")));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectStatus().is5xxServerError();
        }

        @Test
        @DisplayName("Debe manejar error de fraude detectado correctamente")
        void debeManejarErrorFraude() {
            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.error(new PaymentProcessingException("Fraude detectado")));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("$.error").exists();
        }
    }

    @Nested
    @DisplayName("Escenario: Endpoints adicionales del controlador")
    class EndpointsAdicionales {

        @Test
        @DisplayName("Debe obtener estado del pago por ID")
        void debeObtenerPagoPorId() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.getPaymentById(anyString()))
                .thenReturn(Mono.just(response));

            webTestClient.get()
                .uri("/api/v1/payments/{id}", payment.getPaymentId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.paymentId").isEqualTo(payment.getPaymentId());
        }

        @Test
        @DisplayName("Debe retornar 404 cuando el pago no existe")
        void debeRetornar404CuandoPagoNoExiste() {
            String paymentIdInexistente = "PAY-999";

            when(paymentController.getPaymentById(paymentIdInexistente))
                .thenReturn(Mono.empty());

            webTestClient.get()
                .uri("/api/v1/payments/{id}", paymentIdInexistente)
                .exchange()
                .expectStatus().isNotFound();
        }

        @Test
        @DisplayName("Debe listar pagos por estado")
        void debeListarPagosPorEstado() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.getPaymentsByStatus(anyString()))
                .thenReturn(Mono.just(java.util.List.of(response)));

            webTestClient.get()
                .uri("/api/v1/payments?status=COMPLETED")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].status").isEqualTo("COMPLETED");
        }
    }

    @Nested
    @DisplayName("Escenario: Headers y content negotiation")
    class HeadersYContentNegotiation {

        @Test
        @DisplayName("Debe aceptar solo JSON como content type")
        void debeAceptarSoloJson() {
            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_XML)
                .body(fromValue("<payment/>"))
                .exchange()
                .expectStatus().isUnsupportedMediaType();
        }

        @Test
n        @DisplayName("Debe incluir header X-Request-ID en la respuesta")
        void debeIncluirRequestIdEnRespuesta() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.just(response));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Request-ID", "REQ-12345")
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectHeader().exists("X-Request-ID");
        }
    }
}
```
