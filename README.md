# 🚀 HexaSpring Template

**HexaSpring Template** es una plantilla base en **Java 21 + Spring Boot 3** diseñada para el 2025–2026, con una arquitectura **hexagonal (Ports & Adapters)** que promueve proyectos modulares, escalables y fáciles de mantener.  
Creado por **Juan Dulcey (DevV Team)**.

---

## 🧠 ¿Por qué usar esta plantilla?

- 🧱 **Arquitectura Hexagonal**: separación clara entre **domain**, **application** e **infrastructure**.  
- 📐 **Principios SOLID**: mantenibilidad y extensibilidad desde el inicio.  
- 🔄 **Estructura modular**: preparada para crecer en proyectos complejos.  
- 🤝 **Onboarding ágil**: ideal para equipos y hackatones.  
- ⚡ **Spring Boot + Java 21**: lo último en productividad y compatibilidad.  

---

## 🛠️ Tecnologías incluidas

| Categoría         | Herramienta / Técnica   | Propósito                                |
|-------------------|-------------------------|------------------------------------------|
| Lenguaje base     | Java 21                 | Backend moderno y seguro                 |
| Framework         | Spring Boot 3           | Arranque rápido de microservicios        |
| Arquitectura      | Hexagonal Architecture  | Separación de responsabilidades          |
| Principios        | SOLID                   | Buenas prácticas de diseño               |
| Build tool        | Maven                   | Gestión de dependencias y build          |
| Testing           | JUnit + Mockito         | Pruebas unitarias y de integración       |

---

## 📂 Estructura del proyecto

```bash
src/main/java/com/devv/hexaspring
 ├── application       # Casos de uso, DTOs, servicios
 ├── domain            # Modelos de dominio, puertos
 └── infrastructure    # Adaptadores, controladores, repositorios

src/main/resources
 ├── static            # Archivos estáticos (css, js, img)
 └── templates         # Plantillas Thymeleaf u otros engines
