# OpenSCM

## Table of Contents

- [Project Overview](#project-overview)  
- [Architecture](#architecture)  
- [Features](#features)  
- [Technologies Used](#technologies-used)  
- [System Actors and Roles](#system-actors-and-roles)  
- [Getting Started](#getting-started)  
  - [Prerequisites](#prerequisites)  
  - [Running Locally without Docker](#running-locally-without-docker)  
  - [Running with Docker](#running-with-docker)  
- [Folder Structure](#folder-structure)  
- [API Gateway and Frontend Proxy Setup](#api-gateway-and-frontend-proxy-setup)  
- [Database Design](#database-design)  
- [Security](#security)  
- [Future Improvements](#future-improvements)  
- [License](#license)  
- [Contact](#contact)

---

## Project Overview

**OpenSCM** is an enterprise-grade **Supply Chain Management System** designed using a **microservices architecture**. The backend consists of multiple Spring Boot microservices responsible for distinct domains such as authentication, product management, inventory, and order processing. The frontend is built with React and Vite, providing a responsive and user-friendly interface.

The system aims to provide seamless coordination between suppliers, warehouse staff, logistics personnel, and customers through role-based access and real-time inventory and order management.

---

## Architecture

The system is split into independently deployable microservices, each focusing on a single business capability:

| Component         | Description                                 | Technology                  | Default Port |
|-------------------|---------------------------------------------|-----------------------------|--------------|
| API Gateway       | Entry point routing & load balancing         | Spring Cloud Gateway        | 8080         |
| Auth Service      | User authentication & JWT token management  | Spring Boot, Spring Security| 8081         |
| Product Service   | Manage product catalog & details             | Spring Boot, JPA            | 8082         |
| Inventory Service | Track stock levels & inventory updates       | Spring Boot, JPA            | 8083         |
| Order Service     | Handle order creation, updates, and tracking| Spring Boot, JPA            | 8084         |
| Frontend          | React UI for users and admins                 | React, Vite                 | 7172 (dev)   |

Each microservice uses its own PostgreSQL database schema to maintain loose coupling and service autonomy.

---

## Features

- Role-based access control (Customer, Supplier, Warehouse Staff, Logistics, Admin)  
- User registration and JWT-secured login/logout  
- Product catalog browsing, searching, and management  
- Real-time inventory monitoring and automatic stock updates  
- Full order lifecycle management from creation to delivery  
- API Gateway for centralized routing, security, and load balancing  
- Dockerized containers for consistent deployment environments  
- React frontend with proxy setup for seamless backend integration  

---

## Technologies Used

- **Backend:** Spring Boot, Spring Cloud Gateway, Spring Security, JWT, Spring Data JPA, Lombok  
- **Frontend:** React, Vite, Axios  
- **Database:** PostgreSQL  
- **Containerization:** Docker, Docker Compose  
- **Build Tools:** Maven  
- **Testing:** JUnit, Mockito (recommended to implement)  

---

## System Actors and Roles

| Role              | Description                                         | Capabilities                            |
|-------------------|-----------------------------------------------------|----------------------------------------|
| Customer          | End-users who place and track orders                 | Browse products, place orders, track status |
| Supplier          | Manage product supply and update product info       | Add/update products, manage stock supply |
| Warehouse Staff   | Handle inventory and stock updates                    | Monitor stock, update inventory counts |
| Logistics Personnel| Manage shipments and delivery                         | Update shipment status, manage deliveries |
| Admin             | System administrators                                | Manage users, roles, and overall system health |

---

## Getting Started

### Prerequisites

- **Java 17** or higher  
- **Maven** or **Gradle** for backend build  
- **Node.js** and **npm/yarn** for frontend  
- **PostgreSQL** database (one per microservice recommended)  
- **Docker** and **Docker Compose** (optional but recommended)

### Running Locally without Docker

1. **Setup PostgreSQL databases** for each microservice (e.g., `auth_db`, `product_db`, etc.).  
2. Configure each microservice’s `application.properties` or `application.yml` with its database connection details.  
3. Start backend microservices in separate terminals:

   ```bash
   cd auth-service && ./mvnw spring-boot:run
   cd product-service && ./mvnw spring-boot:run
   cd inventory-service && ./mvnw spring-boot:run
   cd order-service && ./mvnw spring-boot:run
   cd api-gateway && ./mvnw spring-boot:run
   ```

4. Start frontend app:

   ```bash
   cd frontend
   npm install
   npm run dev
   ```

5. Open `http://localhost:7172` in your browser.

### Running with Docker

1. Build and start all services using Docker Compose:

   ```bash
   docker-compose up --build
   ```

2. Access the frontend at `http://localhost` (or the port configured in your setup).

---

## Folder Structure

```
open-scm/
├── api-gateway/
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── ...
├── auth-service/
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── ...
├── product-service/
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── ...
├── inventory-service/
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── ...
├── order-service/
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── ...
├── frontend/
│   ├── public/
│   ├── src/
│   ├── Dockerfile
│   ├── package.json
│   ├── vite.config.js
│   └── ...
├── docker-compose.yml
└── README.md
```

---

## API Gateway and Frontend Proxy Setup

The frontend uses a proxy configuration in `vite.config.js` to forward API requests to the API Gateway, abstracting service URLs from the client code.

```js
server: {
  proxy: {
    '/api': 'http://localhost:8080'
  }
}
```

All backend services are accessed through the API Gateway at `localhost:8080`, ensuring centralized routing, authentication, and load balancing.

---

## Database Design

Each microservice maintains its own schema and tables:

- **Auth Service:** Users, Roles, Permissions  
- **Product Service:** Products, Categories  
- **Inventory Service:** Stock Levels, Warehouses  
- **Order Service:** Orders, Order Items, Shipment Status  

This separation ensures database independence and fault tolerance.

---

## Security

- JWT tokens issued by Auth Service protect backend APIs.  
- API Gateway validates tokens and routes requests accordingly.  
- Role-based access control enforced at microservice endpoints.  
- Passwords stored securely using bcrypt hashing.  

---

## Future Improvements

- Integrate payment gateway for order payments  
- Add notification services (email, SMS) for order updates  
- Implement event-driven architecture with Kafka for async communication  
- Add comprehensive logging, monitoring, and metrics dashboards  
- Expand test coverage including integration and e2e tests

---

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## Contact

Created and maintained by [Your Name]  
Email: your.email@example.com  
GitHub: [github.com/yourusername](https://github.com/yourusername)

---

*Thank you for using OpenSCM! Contributions and feedback are welcome.*
