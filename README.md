# IEEE Event Management Backend

This is the backend service for the IEEE Computer Society BDC Event Management System.

## Description

This backend is built using Spring Boot and provides RESTful APIs for managing events, applications, and other related data for the IEEE event management system.

## Features

- Event management APIs
- Application submission and management
- Integration with PostgreSQL database
- Validation and error handling
- OpenAPI Swagger UI for API documentation

## How to Run Locally

1. Ensure you have Java 17 and Maven installed.
2. Configure your PostgreSQL database credentials in `src/main/resources/application.properties`.
3. Build the project:
   ```
   ./mvnw clean package
   ```
4. Run the application:
   ```
   java -jar target/ieee-event-management-backend-0.0.1-SNAPSHOT.jar
   ```
5. The backend server will start on port 8080 by default.

## Deployment

This backend can be deployed on platforms like Railway.app or Heroku. Ensure to set environment variables for database credentials in the deployment environment.

## API Documentation

Swagger UI is available at `/swagger-ui.html` when the server is running.
