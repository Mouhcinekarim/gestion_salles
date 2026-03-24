# Gestion des salles

Documentation d’architecture : **`ARCHITECTURE.md`**.

API **Spring Boot 3** + JPA + **MySQL** + **Springdoc OpenAPI**.

## Prérequis

- JDK 17
- Maven 3.9+
- MySQL (créer la base et utilisateur décrits dans `src/main/resources/application.properties`)

## Build

```bash
mvn -DskipTests package
```

## Lancer

```bash
mvn spring-boot:run
```

Documentation API Swagger UI en général sur `/swagger-ui.html` (selon configuration Springdoc).
