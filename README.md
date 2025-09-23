# Event-Driven Design Patterns

Este proyecto demuestra cómo aplicar **patrones de diseño** (Strategy, Factory, Observer, etc.) en un contexto de **arquitectura orientada a eventos**, usando tecnologías modernas como **AWS SQS**, **Spring WebFlux** y **LocalStack**.

Su propósito es servir como **referencia y base de aprendizaje** para arquitecturas desacopladas y altamente escalables, donde los eventos son el centro de la comunicación entre componentes.

---

## 🚀 Objetivos

- Mostrar el uso de **patrones de diseño clásicos** (Strategy, Factory, Observer, Adapter…) integrados en un flujo de eventos.
- Desarrollar componentes desacoplados basados en **arquitectura limpia** (Clean Architecture).
- Integrar **SQS** como bus de eventos para desacoplar productores y consumidores.
- Proveer un entorno **local reproducible** usando **LocalStack**.
- Servir de base para **pruebas de conceptos** y **formación técnica**.

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