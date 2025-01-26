
---

# Installatiehandleiding voor de **ResearchProject** Applicatie

## Inhoudsopgave

1. [Gebruikte Technologieën](#gebruikte-technologieën)
2. [Vereisten](#vereisten)
3. [Project Instellen](#project-instellen)
4. [Applicatie Draaien met Docker](#applicatie-draaien-met-docker)
5. [Database Setup](#database-setup)
6. [API Endpoints](#api-endpoints)
7. [Veelvoorkomende Fouten](#veelvoorkomende-fouten)

---

## Gebruikte Technologieën

- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Persistentielaag**: JPA/Hibernate (met PostgreSQL)
- **Containerisatie**: Docker & Docker Compose
- **API Documentatie**: Swagger (optioneel, als inbegrepen)
- **Build Tool**: Maven (standaard)

---

## Vereisten

Zorg ervoor dat je de volgende software hebt geïnstalleerd:

- [**Java 11+**](https://adoptopenjdk.net/) (of hoger)
- [**Docker**](https://www.docker.com/products/docker-desktop) en [**Docker Compose**](https://docs.docker.com/compose/)
- [**Maven**](https://maven.apache.org/install.html)
- Een IDE zoals [**IntelliJ IDEA**](https://www.jetbrains.com/idea/) (aanbevolen voor Java) of [**VS Code**](https://code.visualstudio.com/)
- [**PgAdmin 4**](https://www.pgadmin.org/download/) (indien je PostgreSQL lokaal draait zonder Docker)
- Een REST API-client zoals **Postman** of de ingebouwde HTTP-client in je code editor

---

## Project Instellen

Volg deze stappen om het project op je lokale machine in te stellen:

### 1. Clone de repository

clone het project naar je lokale machine:

```bash
https://github.com/HashmiShareeb/researchproject_backend.git
cd researchproject
```

### 2. Afhankelijkheden Installeren

Als je **Maven** gebruikt, installeer je de benodigde afhankelijkheden door het volgende commando uit te voeren:

```bash
mvn clean install
```


### 3. Configureren van `application.properties` of `application.yml`

Pas indien nodig het bestand `application.properties` of `application.yml` aan voor je databaseverbinding. Als je **Docker Compose** gebruikt (zoals hieronder uitgelegd), zou je databaseconfiguratie er als volgt uit kunnen zien:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/{database_name}
spring.datasource.username=postgres
spring.datasource.password=example
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

### 4. `.env` Bestand

Zorg ervoor dat je een `.env` bestand maakt met de volgende variabelen voor PostgreSQL:

```bash
POSTGRES_DB="jouw_database_naam"
POSTGRES_PASSWORD="jouw_wachtwoord"
POSTGRES_USER="jouw_gebruikersnaam"
POSTGRES_PORT="5432"
```

### 5. Docker Compose Configuratie

Als je Docker Compose gebruikt om je applicatie en PostgreSQL-container te draaien, voeg dan de volgende configuratie toe in je `compose.yml`:

```yaml
services:
  postgres:
    image: 'postgres:latest'
    env_file:
      - .env  # Dit laad je .env bestand in
    ports:
      - "5432:5432"

```

---

## Applicatie Draaien met Docker

### 1. Starten via Docker

Zodra je de applicatie start via f5 of mvn spring-boot:run, kun je de applicatie ook starten met Docker Compose:


Dit zal zowel je backend (Spring Boot) als je PostgreSQL-database starten.

De applicatie zal beschikbaar zijn op `http://localhost:8080/api`.

### 2. Starten zonder Docker (via IDE of Command Line)

Als je Docker niet wilt gebruiken, kun je de applicatie lokaal starten via je IDE of de command line:

- In IntelliJ IDEA kun je het project starten met de **play-knop** of door `F5` in te drukken.
- Via de command line kun je de volgende opdracht uitvoeren:

```bash
mvn spring-boot:run
```

De applicatie zal ook beschikbaar zijn op `http://localhost:8080/api`.

---

## Database Setup

Voor het opzetten van de database, kun je de volgende SQL-scripts gebruiken. Dit betreft de tabellen voor `rides`, `vehicle`, `users` en `roles` die je in de applicatie nodig hebt.

### 1. **Rit Tabel (`rides`)**

```sql
CREATE TABLE IF NOT EXISTS rides (
    ride_id VARCHAR(36) PRIMARY KEY,
    ride_name VARCHAR(255) NOT NULL,
    ride_price DECIMAL(10, 2) NOT NULL,
    ride_status VARCHAR(50) NOT NULL CHECK (ride_status IN ('REQUESTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
    ride_description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id VARCHAR(36) NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    address TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
    FOREIGN KEY (vehicle_id) refrences vehicles(vehicle_id) ON DELETE CASCADE
    );

```

### 2. **Voertuig Tabel (`vehicle`)**

```sql
CREATE TABLE IF NOT EXISTS vehicles (
    vehicle_id VARCHAR(36) PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    license_plate VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    battery_level INT,
    vehicle_status VARCHAR(50) NOT NULL,
    vehicle_image VARCHAR(255)
);
```

### 3. **Gebruiker en rollen Tabel (`users, roles`)**

```sql
CREATE TABLE users (
                       user_id VARCHAR(36) PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL
);

CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       user_id VARCHAR(36) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(user_id, username) ON DELETE CASCADE
);
```

---

## API Endpoints

Zodra de applicatie draait, kun je de volgende API-endpoints gebruiken (meer eindpoints kun je zien in de http files in de API folder):

- **GET /api/rides** – Haal alle ritten op.
- **POST /api/rides** – Voeg een nieuwe rit toe.
- **GET /api/rides/{rideId}** – Haal details op van een specifieke rit.
- **PUT /api/rides/{rideId}** – Werk de gegevens van een rit bij.
- **DELETE /api/rides/{rideId}** – Verwijder een rit.

---

## Veelvoorkomende Fouten

### 1. **Fout bij het verbinden met de database**

Als de applicatie niet kan verbinden met de database, controleer dan je `application.properties` configuratie en zorg ervoor dat je de juiste databasegegevens hebt ingevoerd. Als je Docker Compose gebruikt, controleer dan of de databasecontainer draait.

---
