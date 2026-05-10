# 🚀 HexaSpring Template

**HexaSpring Template** es una plantilla base en **Java 25 LTS + Spring Boot 3.5** diseñada para el periodo 2026–2027, con **Arquitectura Hexagonal (Ports & Adapters)** que promueve proyectos modulares, desacoplados y listos para producción.  
Creado por **Juan Dulcey (DevV Team)**.

---

## 🧠 ¿Por qué usar esta plantilla?

- 🧱 **Arquitectura Hexagonal**: separación clara entre `domain`, `application` e `infrastructure`.
- ☕ **Java 25 LTS**: última versión LTS con Virtual Threads estables, Records, Pattern Matching y Structured Concurrency.
- ⚡ **Virtual Threads (Project Loom)**: concurrencia masiva sin callbacks ni programación reactiva compleja.
- 🔒 **OAuth2 + JWT stateless**: seguridad lista para producción con Spring Security 6.
- 📦 **Flyway**: migraciones de base de datos versionadas y reproducibles.
- 🗺️ **MapStruct**: mapping entre capas sin boilerplate ni reflexión en runtime.
- 📊 **Prometheus + Actuator**: observabilidad integrada desde el día uno.
- 🧪 **Testcontainers**: tests de integración con infraestructura real en Docker.
- 📄 **OpenAPI 3 / Swagger UI**: documentación de API autogenerada e interactiva.

---

## ☕ Instalación de Java 25 LTS

Java 25 es la última versión **Long-Term Support (LTS)**, lanzada en septiembre de 2025. Es la versión recomendada para proyectos en producción 2026-2027.

### Opción A — Eclipse Temurin (recomendada, multi-plataforma)

Descarga el instalador desde **[adoptium.net](https://adoptium.net/temurin/releases/?version=25)**:

| Sistema operativo | Formato       |
|-------------------|---------------|
| Windows           | `.msi` (instalador con PATH automático) |
| macOS             | `.pkg`        |
| Linux             | `.tar.gz` / paquete APT/RPM |

Verifica la instalación:
```bash
java -version
# openjdk version "25" 2025-09-16 LTS
# OpenJDK Runtime Environment Temurin-25+... (build 25+...)
```

### Opción B — SDKMAN (Linux / macOS / WSL)

```bash
# Instalar SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Instalar Java 25 Temurin
sdk install java 25-tem
sdk default java 25-tem

# Verificar
java -version
```

### Opción C — Winget (Windows 11)

```powershell
winget install EclipseAdoptium.Temurin.25.JDK
```

### Opción D — Homebrew (macOS)

```bash
brew install --cask temurin@25
```

### Configurar JAVA_HOME (Windows)

Si el instalador no lo configura automáticamente:

```powershell
# En PowerShell como administrador
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Eclipse Adoptium\jdk-25", "Machine")
[System.Environment]::SetEnvironmentVariable("PATH", "$env:PATH;$env:JAVA_HOME\bin", "Machine")
```

---

## 🛠️ Stack tecnológico

| Categoría            | Tecnología / Librería                  | Versión       | Propósito                                        |
|----------------------|----------------------------------------|---------------|--------------------------------------------------|
| Lenguaje             | Java LTS                               | **25**        | Virtual Threads, Records, Pattern Matching, Structured Concurrency |
| Framework            | Spring Boot                            | 3.5.4         | Arranque rápido, autoconfiguración               |
| Arquitectura         | Hexagonal (Ports & Adapters)           | —             | Separación dominio / infraestructura             |
| Principios           | SOLID                                  | —             | Diseño mantenible y extensible                   |
| Build                | Maven Wrapper                          | 3.9.11        | Build reproducible sin instalación de Maven      |
| Persistencia         | Spring Data JPA + Hibernate 6          | Managed       | ORM moderno con soporte UUID nativo              |
| Base de datos        | PostgreSQL                             | 17            | Motor relacional de producción                   |
| Migraciones BD       | Flyway                                 | 10.x          | Versionado de esquema de base de datos           |
| Caché local          | Caffeine                               | 3.x           | Caché en memoria de alto rendimiento             |
| Seguridad            | Spring Security 6 + OAuth2 / JWT       | Managed       | Autenticación stateless, Resource Server         |
| Mensajería           | RabbitMQ (AMQP)                        | 3.13          | Eventos y procesamiento asíncrono                |
| Cliente HTTP         | Spring WebFlux / WebClient             | Managed       | Cliente HTTP no bloqueante                       |
| Mapping              | MapStruct                              | 1.6.3         | Mapping entre capas sin reflexión en runtime     |
| Documentación API    | SpringDoc OpenAPI 3 (Swagger UI)       | 2.8.9         | Documentación interactiva autogenerada           |
| Observabilidad       | Micrometer + Prometheus + Actuator     | Managed       | Métricas, health checks, trazabilidad            |
| Testing unitario     | JUnit 5 + Mockito                      | Managed       | Pruebas unitarias del dominio                    |
| Testing integración  | Testcontainers                         | Managed       | Tests con PostgreSQL / RabbitMQ reales en Docker |
| Utilidades           | Lombok                                 | Managed       | Reducción de boilerplate                         |
| Contenedores local   | Docker + Docker Compose                | —             | Entorno local reproducible con un comando        |

---

## 📂 Estructura del proyecto

```
src/main/java/com/devv/hexaspring
 │
 ├── domain/                        # Núcleo de negocio — SIN dependencias externas
 │   ├── model/                     #   Agregados y entidades del dominio (Java Records)
 │   └── ports/
 │       ├── in/                    #   Puertos de entrada (interfaces de casos de uso)
 │       └── out/                   #   Puertos de salida (interfaces de repositorios/servicios)
 │
 ├── application/                   # Orquestación de casos de uso
 │   ├── dto/                       #   DTOs request/response (Java Records + Bean Validation)
 │   ├── usecases/                  #   Implementaciones de los casos de uso
 │   └── services/                  #   Servicios de aplicación opcionales
 │
 └── infrastructure/                # Adaptadores — detalles técnicos reemplazables
     ├── adapters/                  #   Adaptadores de salida (JPA, eventos, APIs externas)
     │   ├── UserRepositoryAdapter  #     Implementa el puerto de salida → JPA
     │   └── UserMapper             #     MapStruct: dominio ↔ entidad JPA
     ├── config/                    #   Beans de configuración Spring
     │   ├── SecurityConfig         #     OAuth2 Resource Server + JWT
     │   ├── OpenApiConfig          #     Swagger UI + Bearer auth
     │   ├── CacheConfig            #     @EnableCaching con Caffeine
     │   └── GlobalExceptionHandler #     ProblemDetail (RFC 9457) para todos los errores
     ├── controllers/               #   Adaptadores de entrada (REST controllers)
     ├── entities/                  #   Entidades JPA (@Entity + auditoría automática)
     └── repositories/              #   Interfaces Spring Data JPA

src/main/resources
 ├── application.yml                # Configuración principal (con variables de entorno)
 └── db/migration/                  # Scripts Flyway versionados (V1__, V2__, ...)

docker-compose.yml                  # PostgreSQL 17 + RabbitMQ 3.13 listos para levantar
```

---

## ⚙️ Prerrequisitos

| Herramienta     | Versión mínima | Notas                                    |
|-----------------|----------------|------------------------------------------|
| Java JDK        | **25 LTS**     | Ver sección de instalación arriba        |
| Maven           | —              | Incluido vía `./mvnw` (no instalar nada) |
| Docker Desktop  | 24+            | Necesario para infraestructura local     |
| Docker Compose  | v2             | Incluido en Docker Desktop               |

---

## 🚀 Inicio rápido

### 1. Clonar el repositorio

```bash
git clone https://github.com/devv-team/hexaspring-template.git
cd hexaspring-template
```

### 2. Levantar infraestructura local con Docker

```bash
docker compose up -d
```

Esto levanta:
- **PostgreSQL 17** → `localhost:5432` (DB: `hexaspring`, user: `postgres`, pass: `postgres`)
- **RabbitMQ 3.13** → `localhost:5672` (Management UI: `http://localhost:15672`)

### 3. Ejecutar la aplicación

```bash
# Linux / macOS / WSL
./mvnw spring-boot:run

# Windows PowerShell
.\mvnw.cmd spring-boot:run
```

La aplicación arranca en `http://localhost:8080`.

---

## 📄 Recursos disponibles

| Recurso                    | URL                                           |
|----------------------------|-----------------------------------------------|
| **Swagger UI**             | http://localhost:8080/swagger-ui.html         |
| **OpenAPI JSON**           | http://localhost:8080/api-docs                |
| **Health check**           | http://localhost:8080/actuator/health         |
| **Métricas Prometheus**    | http://localhost:8080/actuator/prometheus     |
| **RabbitMQ Management UI** | http://localhost:15672 (guest / guest)        |

---

## 🔒 Seguridad

La plantilla usa **OAuth2 Resource Server con JWT**. Configura el `issuer-uri` de tu proveedor de identidad en `application.yml` o vía variable de entorno:

```bash
JWT_ISSUER_URI=https://tu-proveedor.com/realms/mi-realm
```

Los endpoints **públicos por defecto** (sin token):
- `/swagger-ui/**` y `/api-docs/**`
- `/actuator/health` y `/actuator/info`

---

## 🌿 Variables de entorno

| Variable          | Default                | Descripción                         |
|-------------------|------------------------|-------------------------------------|
| `DB_HOST`         | `localhost`            | Host de PostgreSQL                  |
| `DB_PORT`         | `5432`                 | Puerto de PostgreSQL                |
| `DB_NAME`         | `hexaspring`           | Nombre de la base de datos          |
| `DB_USERNAME`     | `postgres`             | Usuario de PostgreSQL               |
| `DB_PASSWORD`     | `postgres`             | Contraseña de PostgreSQL            |
| `JWT_ISSUER_URI`  | Keycloak local         | URI del issuer OAuth2/JWT           |
| `RABBITMQ_HOST`   | `localhost`            | Host de RabbitMQ                    |
| `RABBITMQ_PORT`   | `5672`                 | Puerto de RabbitMQ                  |

---

## 🔄 Migraciones de base de datos (Flyway)

Los scripts SQL viven en `src/main/resources/db/migration/` con la nomenclatura:

```
V{versión}__{descripcion}.sql

Ejemplos:
  V1__create_users_table.sql
  V2__add_roles_table.sql
  V3__add_audit_columns.sql
```

Flyway ejecuta automáticamente las migraciones pendientes al arrancar la aplicación.

---

## 🧪 Testing

```bash
# Tests unitarios + de integración
./mvnw test

# Con reporte de cobertura (Surefire + Jacoco)
./mvnw verify
```

Los **tests de integración** usan **Testcontainers**: levanta PostgreSQL real en Docker automáticamente durante los tests — sin configuración manual, sin mocks de BD.

---

## 🧱 Arquitectura Hexagonal — Diagrama

```
              ┌─────────────────────────────────────────┐
              │           INFRASTRUCTURE                 │
              │                                          │
              │  [REST Controller]  [AMQP Listener]      │
              │         │                  │             │
              │         ▼                  ▼             │
              │    ┌─────────────────────────────┐       │
              │    │       APPLICATION            │       │
              │    │   (Use Cases / Services)     │       │
              │    └─────────────┬───────────────┘       │
              │                  │                       │
              │         ┌────────▼────────┐              │
              │         │    DOMAIN        │              │
              │         │  Models + Ports  │              │
              │         └────────┬────────┘              │
              │                  │                       │
              │    [JPA Adapter] [HTTP Adapter] ...       │
              └─────────────────────────────────────────┘

  Regla principal: las flechas de dependencia apuntan SIEMPRE hacia el dominio.
  El dominio NO importa nada de Spring, JPA ni ningún framework.
```

---

## ☕ Características de Java 25 usadas en la plantilla

| Feature                    | Disponible desde | Uso en esta plantilla                         |
|----------------------------|------------------|-----------------------------------------------|
| Virtual Threads             | Java 21 (LTS)   | Habilitados vía `spring.threads.virtual.enabled: true` |
| Records                     | Java 16+        | DTOs (`UserRequest`, `UserResponse`) y modelos de dominio (`User`) |
| Pattern Matching            | Java 21+        | Expresiones `switch` en handlers de excepciones |
| Sequenced Collections       | Java 21+        | `.toList()` en streams                        |
| Structured Concurrency      | Java 21+ preview → estable en 25 | Disponible para operaciones concurrentes      |
| Scoped Values               | Java 21+ preview → estable en 25 | Alternativa a `ThreadLocal` con Virtual Threads |

---

## 📌 Extensiones recomendadas (roadmap del template)

Al crecer el proyecto, considera añadir:

| Necesidad                     | Tecnología sugerida                          |
|-------------------------------|----------------------------------------------|
| Caché distribuida             | Spring Data Redis + Valkey 8                 |
| Trazabilidad distribuida      | OpenTelemetry + Grafana Tempo / Jaeger       |
| Mensajería de alto volumen    | Apache Kafka                                 |
| Build de imagen Docker        | Jib Maven Plugin (sin Dockerfile)            |
| Análisis estático de código   | SonarQube / SpotBugs + Checkstyle            |
| Rate limiting                 | Bucket4j + Spring AOP                        |
| Autenticación local (dev)     | Keycloak en Docker Compose                   |

---

## 👤 Autor

**Juan Dulcey** — DevV Team  
Plantilla diseñada para proyectos Java de producción — **Java 25 LTS · Spring Boot 3.5 · 2026–2027**
