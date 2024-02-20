# URL Shortener

## Stack:
- Java
- Spring Boot
- Maven
- Spring Test
- Redis
- Docker
- PostgreSQL (latest version)
  
## Description of Technologies:
- **Java**: The primary programming language for the application logic.
- **Spring Boot**: Used for creating standalone, production-grade Spring-based Applications.
- **Maven**: Dependency management and build automation tool.
- **Spring Test**: Framework for writing and running tests.
- **Redis**: In-memory data structure store used for caching.
- **Docker**: Containerization platform for packaging applications and their dependencies.
- **PostgreSQL**: Relational database management system for data storage.

## Features:
- Scheduling: Utilized for certain methods.
  
## Endpoints:
- **POST** `/api/v1/short-url`: Generates a short URL.
- **GET** `/api/v1/{shortUrl}`: Redirects to the original URL associated with the short URL.

## To-Do List:
1. Integrate Redis for load balancing.
2. Implement Docker.
3. Complete test coverage.
4. Perform load testing.

