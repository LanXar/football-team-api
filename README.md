# Football Team API

## Overview

Football Team API is a Spring Boot application that provides a RESTful API for managing football teams and their players. It allows you to perform CRUD operations on teams and players.

## Features

- Add, retrieve, update, and delete football teams
- Add, retrieve, update, and delete players in a team
- Pagination and sorting support for listing teams and players

## Technologies Used

- Java 22
- Spring Boot 3.3.2
- Spring Data JPA
- MySQL
- Maven
- Mockito
- JUnit

## Getting Started

### Prerequisites

- Java 22
- Maven
- MySQL

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/LanXar/football-team-api.git
   cd football-team-api
   ```

2. Update MySQL configuration in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Teams

- **GET /api/teams**: Get all teams (with pagination and sorting)
- **POST /api/teams**: Create a new team
- **GET /api/teams/{teamId}/players**: Get players by team ID (with pagination)
- **POST /api/teams/{teamId}/players**: Add a player to a team

### Players

- **GET /api/players**: Get all players (with pagination and sorting)
- **POST /api/players**: Create a new player
- **GET /api/players/{playerId}**: Get a player by ID
- **PUT /api/players/{playerId}**: Update a player by ID
- **DELETE /api/players/{playerId}**: Delete a player by ID

## Running Tests

To run the unit tests:
```bash
mvn test
```

# Technical Choices for Football Team API
```
## Overview

This document outlines the technical choices made during the development of the Football Team API.

## Backend Framework

### Spring Boot

- **Reason**: Spring Boot is a widely used framework for building microservices and web applications. It provides a range of features and integrations that simplify the development process, including dependency management, embedded servers, and auto-configuration.

## Database

### MySQL

- **Reason**: MySQL is a popular and reliable relational database management system. It is well-suited for structured data and provides robust support for transactions, indexing, and scalability.

## ORM Framework

### Spring Data JPA

- **Reason**: Spring Data JPA simplifies the implementation of data access layers by providing a repository abstraction. It integrates seamlessly with Spring Boot and supports JPA-based data access, making it easier to manage database entities and perform CRUD operations.

## Testing Frameworks

### JUnit

- **Reason**: JUnit is a widely-used testing framework for Java. It provides a simple and efficient way to write and run repeatable tests. It is well-supported and integrates with most Java development tools.

### Mockito

- **Reason**: Mockito is a popular mocking framework for unit tests in Java. It allows developers to create mock objects and define their behavior, making it easier to test components in isolation.

## Build Tool

### Maven

- **Reason**: Maven is a powerful build automation tool used primarily for Java projects. It provides a standardized way to manage project dependencies, build lifecycle, and project structure. Maven's dependency management ensures that the project can be easily built and run on any system.

## Conclusion

The above choices were made to ensure the application is maintainable, and scalable. The technologies and frameworks selected are widely used and well-supported within the Java ecosystem, providing a solid foundation for the Football Team API.
```