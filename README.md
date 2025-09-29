# Event-Driven Design Patterns

Este proyecto demuestra cómo aplicar **patrones de diseño clásicos** —como **Strategy**, **Factory** y **Observer**— en un contexto de **arquitectura orientada a eventos**. Usa **Spring WebFlux** para procesamiento reactivo y **AWS SQS** como bus de eventos (simulado localmente con **LocalStack**).

Su propósito es servir como **referencia práctica y base de aprendizaje** para construir arquitecturas desacopladas, escalables y resilientes donde los eventos son el centro de la comunicación entre componentes.

---

## 🚀 Objetivos del Proyecto

- **Patrones de diseño aplicados a eventos:**  
  Implementar Strategy + Factory para enrutar eventos dinámicamente según su tipo (PSE, PayPal, Tarjeta, etc.).
- **Arquitectura limpia:**  
  Separar responsabilidades en capas (modelos, casos de uso, adaptadores) para favorecer el desac acoplamiento.
- **Procesamiento reactivo:**  
  Usar Spring WebFlux y Reactor Context para propagar contexto y trazabilidad (`messageId`, `spanId`) entre hilos.
- **Infraestructura local reproducible:**  
  Integrar LocalStack para simular SQS y probar el flujo completo sin depender de AWS real.
- **Base de entrenamiento:**  
  Servir como ejemplo de referencia para formación técnica o PoC de arquitecturas event-driven.

---

## 🧩 Principales Patrones Implementados

- **Strategy Pattern:**  
  Permite procesar distintos tipos de transacción (Tarjeta, PayPal, PSE…) usando estrategias independientes.
- **Factory Pattern:**  
  Centraliza la selección de la estrategia adecuada para cada evento entrante.
- **Observer / Event-Driven:**  
  Los productores publican mensajes en SQS; los consumidores reaccionan de forma asíncrona sin acoplamiento.
- **Context Propagation:**  
  Propagación automática de `messageId` y `spanId` mediante Micrometer + Reactor Context.

---

## 🗂️ Estructura del Proyecto

```
📦event-driven-design-patterns
┣ 📂applications
┃ ┗ 📂app-service
┃ ┃ ┣ 📂src
┃ ┃ ┃ ┣ 📂main
┃ ┃ ┃ ┃ ┣ 📂java
┃ ┃ ┃ ┃ ┃ ┗ 📂co.com.eventdriven.designpatterns
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜[configs and beans]
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MainApplication.java
┃ ┃ ┃ ┃ ┗ 📂resources
┃ ┃ ┃ ┃ ┃ ┣ 📜[properties]
┃ ┃ ┃ ┗ 📂test
┃ ┃ ┃ ┃ ┗ 📂java
┃ ┃ ┃ ┃ ┃ ┗ 📂co.com.eventdriven.designpatterns
┃ ┃ ┗ 📜build.gradle
┣ 📂deployment
┃ ┣ 📜[Dockerfile, Pipelines as a code]
┣ 📂domain
┃ ┣ 📂model
┃ ┃ ┣ 📂src
┃ ┃ ┃ ┣ 📂main
┃ ┃ ┃ ┃ ┗ 📂java
┃ ┃ ┃ ┃ ┃ ┗ 📂co.com.eventdriven.designpatterns
┃ ┃ ┃ ┗ 📂test
┃ ┃ ┃ ┃ ┗ 📂java
┃ ┃ ┃ ┃ ┃ ┗ 📂co.com.eventdriven.designpatterns
┃ ┃ ┗ 📜build.gradle
┃ ┗ 📂usecase
┃ ┃ ┣ 📂src
┃ ┃ ┃ ┣ 📂main
┃ ┃ ┃ ┃ ┗ 📂java
┃ ┃ ┃ ┃ ┃ ┗ 📂co.com.eventdriven.designpatterns
┃ ┃ ┃ ┗ 📂test
┃ ┃ ┃ ┃ ┗ 📂java
┃ ┃ ┃ ┃ ┃ ┗ 📂co.com.eventdriven.designpatterns
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂usecase
┃ ┃ ┗ 📜build.gradle
┣ 📂infrastructure
┃ ┣ 📂driven-adapters
┃ ┣ 📂entry-points
┃ ┗ 📂helpers
┣ 📜.gitignore
┣ 📜build.gradle
┣ 📜gradle.properties
┣ 📜lombok.config
┣ 📜main.gradle
┣ 📜README.md
┗ 📜settings.gradle
```

---

## 🛠️ Tecnologías Clave

- **Java 21** + **Spring Boot / WebFlux**
- **AWS SQS** (simulado con **LocalStack**)
- **Gradle** para build modular
- **Lombok** para simplificar modelos
- **Micrometer Context Propagation** para propagación de contexto
- **LogstashEncoder / Logback** para logs estructurados en JSON

---

## 🔄 Flujo de Eventos

1. **Productor envía evento** a SQS (`sqs-payment-events-local`).
2. **SQSPaymentFilteringProcessor** consume el mensaje y aplica la **Factory** para elegir la estrategia adecuada.
3. Cada **Strategy** (ej: `CardTransactionStrategy`, `PaypalTransactionStrategy`) procesa el evento con pasos definidos (`doOnNext`).
4. Los datos se transforman en un DTO común (`PaymentTransactionInfo`) listo para persistencia o integración.

---

## 🧪 Ejecución Local

1. **Levantar LocalStack y crear cola SQS:**
   ```bash
    sh LocalStackStartEnvironment.sh
   ```

2. **Enviar mensaje de pago por tarjeta:**
   ```bash
   sh LocalStackPhysicalCardPaymentEvent.sh
   ```

3. **Enviar mensaje de pago por Paypal:**
   ```bash
   sh LocalStackPaypalPaymentEvent.sh
   ```

4. **Enviar mensaje de pago por PSE:**
   ```bash
   sh LocalStackPSEPaymentEvent.sh
   ```

5. **Purgar SQS (Opcional):**
   ```bash
   sh LocalStackPurgeSQSQueue.sh
   ```

6. **Ejecutar aplicación Spring Boot:**
   ```bash
   ./gradlew bootRun
   ```

---

## 📊 Logs y Contexto

- Logs en formato JSON con `messageId` y `spanId` propagados automáticamente.
- Cada Strategy imprime pasos relevantes con `doOnNext(...)` para trazabilidad de negocio.

---

## 📚 Próximos Pasos / Extensiones

- Añadir persistencia (ej. DynamoDB, MongoDB) para registrar resultados de cada evento.
- Consumir servicios externos para extraer data como ejecución de alguna de las strategies.
- Integrar métricas y dashboards con Micrometer + Prometheus.

---
