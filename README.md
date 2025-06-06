# Course Selling Platform - Microservices Architecture

This project is a **Course Selling Application** built using **Spring Boot** and follows a **Microservices Architecture**. It provides a scalable backend system for managing users, courses, bookings, and payments.

---

## 🧩 Microservices Overview

| Service               | Description                                      |
|-----------------------|--------------------------------------------------|
| `gateway-service`         | Handles routing and acts as a single entry point |
| `registry-service`    | Eureka server for service registration & discovery |
| `course-service`      | Manages courses and their metadata               |
| `user-service`        | Handles user registration, login, and profiles   |
| `course-service`     | Manages course register new course/modify               |
| `transaction-service`     | Simulates payment processing                     
---

## ⚙️ Technologies Used

- Java 17  
- Spring Boot  
- Spring Cloud (Eureka, OpenFeign, Config)  
- Spring Data MongoDB & PostgreSQL  
- REST API  
- JPA/Hibernate  
- Docker & Docker Compose  
- JWT Authentication  
- Lombok  
- Actuator (for monitoring)  
- Maven/Gradle  
- Jenkins (CI/CD)  
- Swagger (API Documentation)

---

## 📦 Folder Structure

```
course-selling-app/
├── course-service-auth/
├── course-service-course/
├── course-service-gateway/
├── course-service-registry/
├── course-service-transaction/
├── course-service-user/
└── docker-compose.yml
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17  
- Docker & Docker Compose  
- Gradle  

### Running the Application

```bash
# Start all services
docker-compose up --build
```

Visit the services:

- Eureka Dashboard: `http://localhost:8761`  
- API Gateway: `http://localhost:8080`  
- Swagger UI (if configured): `http://localhost:8080/swagger-ui.html`

---

## 🔐 Authentication

- JWT tokens are issued at login via `user-service`.  
- All requests to protected endpoints must include the header:  
  ```
  Authorization: Bearer <token>
  ```

---

## 📬 API Endpoints (via Gateway)

- `POST /auth/signup`  
- `POST /auth/login`  
- `GET /users/{username}`  
- `POST /users/save`  
- `GET /users/course/{courseID}`  
- `GET /users/mycourse`  
- `GET /course/{courseId}`  
- `POST /course`  
- `GET /transaction/{userID}`
- `POST /transaction`

---

## 📈 Features

- Microservices-based modular design  
- RESTful APIs for each service  
- Centralized configuration and service discovery  
- JWT-secured endpoints  
- Containerized with Docker  
- Extensible for future services (analytics, reviews, etc.)

---

## 🧪 Future Enhancements

- Rate limiting & retries using Resilience4j  
- Kafka-based async communication  
- Distributed tracing (Zipkin)  
- Frontend integration (React/Angular)

---

## 🧑‍💻 Author

**Aviral Gupta**  
Backend Developer | Java | Spring Boot | Microservices  

---

## 📝 License
This project is licensed under the MIT License.