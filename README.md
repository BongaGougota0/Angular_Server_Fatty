# 🚀 Spring Boot Server Application

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Gradle](https://img.shields.io/badge/Maven-02303A?style=for-the-badge&logo=maven&logoColor=white)](https://gradle.org/)

## Overview

This repository contains a robust RESTful API server built with Spring Boot. The application provides the backend services for my Angular client, handles authentication, business logic, and data persistence. The client code can be found on [https://github.com/BongaGougota0/Angular_Client]

## 🔧 Technologies

- **Spring Boot**: Java-based framework for creating stand-alone production-grade applications
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data persistence and ORM
- **Hibernate**: Object-relational mapping
- **Mysql**: Relational database
- **Lombok**: Boilerplate code reduction

## 📋 Requirements

- JDK 17 or later (this project : Java 21)
- Maven 3.8.x
- MySQL 8.x

## 🚀 Getting Started

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/BongaGougota0/Angular_Server_Fatty
   cd Angular_Server_Fatty
   ```

2. Configure the database connection in `src/main/resources/application.properties`:

3. Build the application:
   ```
   # Maven
   ./mvnw clean package
   ```

4. Run the application:
   ```
   # Maven
   ./mvnw spring-boot:run
   ```

The server will start at `http://localhost:8080`.

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/yourcompany/app/
│   │   ├── configuration/       # Configuration classes, demo data generator
│   │   ├── controller/          # REST controllers
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── exception/           # Custom exceptions
│   │   ├── models               # Entity classes
│   │   ├── repositories/        # Data access layer
│   │   ├── security/            # Authentication & authorization
│   │   ├── service/             # Business logic
│   │   └── util/                # Utility classes
│   └── resources/
│       ├── app-.properties      # Application configuration
│       └── schema.sql           # script for creating schema and tables
```

### Environment Variables

Key environment variables that need to be set:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`

## 🤝 Done.
Bonga Gougota Gongotha