# 📚 Bookstore Management System

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![JWT](https://img.shields.io/badge/Auth-JWT-red)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

A secure and scalable REST API for an online bookstore built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**. The application enables customers to browse and purchase books while allowing administrators to manage inventory and process orders.

---

# 📑 Table of Contents

- [🚀 Features](#-features)
- [🛠 Tech Stack](#-tech-stack)
- [🏗 Architecture](#-architecture)
- [📂 Project Structure](#-project-structure)
- [🔐 Security](#-security)
- [📄 API Documentation](#-api-documentation)
- [🗄 Database](#-database)
- [▶ Running the Project](#-running-the-project)
- [🧪 Testing](#-testing)
- [📌 Future Enhancements](#-future-enhancements)
- [👩‍💻 Author](#-author)

---

# 🚀 Features

## 👤 User Authentication

- User Registration
- User Login
- JWT Authentication
- Password Encryption using BCrypt
- Role-Based Authorization (ADMIN & CUSTOMER)

## 📚 Book Management

- Add Book
- Update Book
- Delete Book
- Get All Books
- Get Book by ID
- Search Books by Title
- Search Books by Author
- Filter Books by Genre
- Pagination
- Sorting

## 📦 Order Management

- Place Order
- View Customer Orders
- View Order by ID
- Admin: View All Orders
- Admin: Update Order Status
- Automatic Inventory Stock Update
- Order Pagination
- Order Sorting

## ⚙ Additional Features

- Global Exception Handling
- DTO Pattern
- MapStruct for Entity-DTO Mapping
- Jakarta Bean Validation
- Swagger (OpenAPI) Documentation
- Unit Testing using JUnit 5 & Mockito

---

# 🛠 Tech Stack

- Java 21
- Spring Boot 3.5.16
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- MapStruct
- Lombok
- Maven
- Swagger (OpenAPI)
- JUnit 5
- Mockito
- Postman

---

# 🏗 Architecture

```text
                 Client
                    │
                    ▼
        Spring Boot REST API
                    │
      Spring Security + JWT
                    │
            Controller Layer
                    │
             Service Layer
                    │
          Repository Layer
                    │
             MySQL Database
```

---

# 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.bookstore
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       │   ├── request
│   │       │   └── response
│   │       ├── entity
│   │       ├── enums
│   │       ├── exception
│   │       ├── mapper
│   │       ├── repository
│   │       ├── security
│   │       │   └── jwt
│   │       ├── service
│   │       │   └── impl
│   │       └── util
│   └── resources
│       ├── application.properties
│       └── data.sql
└── test
```

---

# 🔐 Security

The application uses **Spring Security** with **JWT Authentication** to secure protected endpoints.

### Authentication Flow

1. Register a new user.
2. Login using email and password.
3. Receive a JWT token.
4. Include the token in the `Authorization` header.
5. Access secured APIs based on the assigned role.

### User Roles

| Role | Permissions |
|------|-------------|
| ADMIN | Manage books, view all orders, update order status |
| CUSTOMER | Browse books, place orders, view own orders |

---

# 📄 API Documentation

Swagger UI is available after running the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🗄 Database

**Database:** MySQL

### Main Tables

- users
- books
- orders
- order_items

---

# ▶ Running the Project

### Clone the Repository

```bash
git clone https://github.com/Deepali2160/bookstore-management-system.git
```

### Navigate to the Project

```bash
cd bookstore-management-system
```

### Configure the Database

Update the database configuration in:

```text
src/main/resources/application.properties
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

---

# 🧪 Testing

The project includes:

- Unit Testing using JUnit 5
- Mockito for Mocking
- Postman Collection for API Testing

All core service-layer functionalities are covered with unit tests.

---

# 📌 Future Enhancements

- ⭐ Book Reviews & Ratings
- ⭐ Payment Gateway Integration (Stripe / PayPal)
- ⭐ Docker Support
- ⭐ Cloud Deployment (AWS / Render / Railway)

---

# 👩‍💻 Author

**Deepali Mundra**

- **GitHub:** https://github.com/Deepali2160
- **Project Repository:** https://github.com/Deepali2160/bookstore-management-system

---

⭐ If you found this project helpful, consider giving it a star on GitHub!