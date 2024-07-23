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
- Hibernate
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
   spring.jpa.show-sql=true
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Database Setup

Ensure your MySQL server is running and create a database for the application:

```sql
CREATE DATABASE football_team_db;
```

Update `application.properties` with your MySQL credentials and the newly created database name.

## Using the API with Postman

To interact with the API using Postman:

1. **Install Postman**

2. **Create a New Collection**: In Postman, create a new collection named "Football Team API".

3. **Add Requests to Collection**:
   - **Get All Teams**: 
     - Method: GET
     - URL: `http://localhost:8080/api/teams`
   - **Create a New Team**:
     - Method: POST
     - URL: `http://localhost:8080/api/teams`
     - Body: 
       ```json
       {
         "name": "Nice",
         "acronym": "NFC",
         "budget": 1000000.0
       }
       ```
   - **Get a Team by ID**:
     - Method: GET
     - URL: `http://localhost:8080/api/teams/{teamId}`
   - **Update a Team by ID**:
     - Method: PUT
     - URL: `http://localhost:8080/api/teams/{teamId}`
     - Body:
       ```json
       {
         "name": "Nice Updated",
         "acronym": "NFCU",
         "budget": 1200000.0
       }
       ```
   - **Add Player to Team**:
     - Method: POST
     - URL: `http://localhost:8080/api/teams/{teamId}/players`
     - Body:
       ```json
       {
         "name": "Player1",
         "position": "Forward"
       }
       ```
   - **Delete a Team by ID**:
     - Method: DELETE
     - URL: `http://localhost:8080/api/teams/{teamId}`
   - **Get All Players**: 
     - Method: GET
     - URL: `http://localhost:8080/api/players`
   - **Get Players by Team ID**:
     - Method: GET
     - URL: `http://localhost:8080/api/teams/{teamId}/players`
   - **Create a New Player**:
     - Method: POST
     - URL: `http://localhost:8080/api/players`
     - Body:
       ```json
       {
         "name": "Player1",
         "position": "Forward",
         "team": {
           "id": 1
         }
       }
       ```
   - **Get a Player by ID**:
     - Method: GET
     - URL: `http://localhost:8080/api/players/{playerId}`
   - **Update a Player by ID**:
     - Method: PUT
     - URL: `http://localhost:8080/api/players/{playerId}`
     - Body:
       ```json
       {
         "name": "Player1",
         "position": "Midfielder"
       }
       ```
   - **Delete a Player by ID**:
     - Method: DELETE
     - URL: `http://localhost:8080/api/players/{playerId}`

---

### Running Tests

To run the unit tests:
```bash
mvn test
```

## Technical Choices for Football Team API

### Backend Framework

#### Spring Boot

- **Reason**: Spring Boot is a widely used framework for building microservices and web applications. It provides a range of features and integrations that simplify the development process, including dependency management, embedded servers, and auto-configuration.

### Database

#### MySQL

- **Reason**: MySQL is a reliable relational database management system. It is well-suited for structured data and provides robust support for transactions, indexing, and scalability.

### ORM Framework

#### Spring Data JPA

- **Reason**: Spring Data JPA simplifies the implementation of data access layers by providing a repository abstraction. It integrates seamlessly with Spring Boot and supports JPA-based data access, making it easier to manage database entities and perform CRUD operations.

### Testing Frameworks

#### JUnit

- **Reason**: JUnit is a widely-used testing framework for Java. It provides a simple and efficient way to write and run repeatable tests. It is well-supported and integrates with most Java development tools.

#### Mockito

- **Reason**: Mockito is a popular mocking framework for unit tests in Java. It allows developers to create mock objects and define their behavior, making it easier to test components in isolation.

### Build Tool

#### Maven

- **Reason**: Maven is a powerful build automation tool used primarily for Java projects. It provides a standardized way to manage project dependencies, build lifecycle, and project structure. Maven's dependency management ensures that the project can be easily built and run on any system.

## Conclusion

The above choices were made to ensure the application is maintainable, and scalable. The technologies and frameworks selected are widely used and well-supported within the Java ecosystem, providing a solid foundation for the Football Team API.
