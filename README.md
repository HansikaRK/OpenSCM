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

**OpenSCM** is a simple **Supply Chain Management (SCM) project** built by us. It has a backend made with **Java Spring Boot** and a frontend made with **React**. The project lets users manage products, track inventory, and handle orders in a basic way.

The main goal of OpenSCM is to help us **learn and experiment** with how an SCM system works. Users can browse products, check stock levels, create orders, and monitor order status. Admins can add or update products and manage users.

This project introduces **basic concepts of web development and full-stack applications**, such as:

- Building a backend with REST APIs
- Connecting to a database to store and retrieve data
- Creating a frontend with React to interact with the backend
- Understanding roles and permissions for different users

OpenSCM is simple and easy to understand, making it a good starting point for learning how supply chain systems work. In the future, it can be expanded with features like **shipment tracking, notifications, or payment integration**.

It’s a **hands-on project** to practice connecting different parts of a system and seeing how they work together in a simple SCM setup.

---

## Architecture

The system is divided into **independently deployable microservices**, each handling a single part of the application:

| Component         | Description                                 | Technology                  | Default Port |
|-------------------|---------------------------------------------|-----------------------------|--------------|
| Eureka Server      | Service registry for all microservices      | Spring Cloud Netflix Eureka | 8761         |
| API Gateway       | Entry point for routing requests and load balancing | Spring Cloud Gateway        | 8080         |
| Auth Service      | Handles user authentication and JWT token management | Spring Boot, Spring Security| 8081         |
| Inventory Service | Tracks stock levels and updates inventory   | Spring Boot, JPA            | 8082         |
| Order Service     | Manages order creation, updates, and tracking | Spring Boot, JPA           | 8083         |
| Product Service   | Manages product catalog and product details | Spring Boot, JPA            | 8084         |
| Frontend          | React UI for users and admins                | React, Vite                 | 7172 (dev)   |

Each microservice uses its **own PostgreSQL database** to keep the system simple and separate. This makes it easier to understand how each part works on its own.
---

## Features

- Simple role-based access (Admin, Customer, Staff)  
- User registration and login (basic authentication, optional JWT)  
- Browse and manage products  
- Track inventory and update stock  
- Create and track orders  
- React frontend for interacting with the backend  
- Optional: Docker support for running services easily  

---

## Technologies Used

- **Backend:** Spring Boot, Spring Data JPA  
- **Frontend:** React, Vite, Axios  
- **Database:** PostgreSQL  
- **Containerization (optional):** Docker, Docker Compose  
- **Build Tools:** Maven  

---

## System Actors and Roles

| Role        | Description                         | Capabilities |
|------------|-------------------------------------|--------------|
| Admin      | Manages the system                  | Add/update products, manage users, view all orders |
| Customer   | Places orders                        | Browse products, create orders, check order status |
| Staff      | Handles inventory and orders         | Update stock, confirm orders, mark orders as shipped |


---

## Getting Started

### Prerequisites

- **Java 17** or higher  
- **Maven** or **Gradle** for backend build  
- **Node.js** and **npm/yarn** for frontend  
- **PostgreSQL** database (one per microservice recommended)  
- **Docker** and **Docker Compose** (optional but recommended)

### Running Locally without Docker

1. **Set up PostgreSQL databases** for each microservice (e.g., `auth_db`, `inventory_db`, `order_db`, `product_db`).  
2. Update each microservice’s `application.properties` or `application.yml` file with your database connection details.  
3. Start backend services in separate terminals (order matters if you want to avoid errors — start Eureka first):

   ```bash
   # Start Eureka Server (Service Registry)
   cd ServiceRegistry && ./mvnw spring-boot:run

   # Start API Gateway
   cd ApiGateway && ./mvnw spring-boot:run

   # Start Auth Service
   cd AuthService && ./mvnw spring-boot:run

   # Start Inventory Service
   cd InventoryService && ./mvnw spring-boot:run

   # Start Order Service
   cd OrderService && ./mvnw spring-boot:run

   # Start Product Service
   cd ProductService && ./mvnw spring-boot:run

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
OpenSCM/
├── ServiceRegistry/ # Service registry
│ ├── src/
│ ├── Dockerfile
│ ├── pom.xml
│ └── ...
├── ApiGateway/ # Entry point for routing requests
│ ├── src/
│ ├── Dockerfile
│ ├── pom.xml
│ └── ...
├── AuthService/ # User authentication and roles
│ ├── src/
│ ├── Dockerfile
│ ├── pom.xml
│ └── ...
├── InventoryService/ # Inventory tracking
│ ├── src/
│ ├── Dockerfile
│ ├── pom.xml
│ └── ...
├── OrderService/ # Order management
│ ├── src/
│ ├── Dockerfile
│ ├── pom.xml
│ └── ...
├── ProductService/ # Product catalog
│ ├── src/
│ ├── Dockerfile
│ ├── pom.xml
│ └── ...
├── frontend/ # React + Vite frontend
│ ├── public/
│ ├── src/
│ ├── Dockerfile
│ ├── package.json
│ ├── vite.config.js
│ └── ...
├── docker-compose.yml # Optional Docker setup
└── README.md # Project documentation
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

## Contact

Created and maintained by Hansika Kularathne, Ushan Savindu  
Email: hkularatne2002@gmail.com, ushansavindu666@gmail.com  
GitHub: [HansikaRK](https://github.com/HansikaRK) , [UshanSavindu55](https://github.com/UshanSavindu55)

---

*Thank you for using OpenSCM! Contributions and feedback are welcome.*
