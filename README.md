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
- MapStruct Mapper
- Swagger API Documentation
- Jakarta Validation
- Unit Testing using JUnit & Mockito

---

# 🛠 Tech Stack

- Java 21
- Spring Boot 3.5.16
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- Maven
- Lombok
- MapStruct
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

The application uses **Spring Security** with **JWT Authentication** to secure all protected endpoints.

### Authentication Flow

- Register a new user
- Login using email and password
- Receive JWT Token
- Pass JWT Token in the Authorization Header
- Access secured APIs

### Roles

| Role | Permissions |
|------|-------------|
| ADMIN | Manage Books, View All Orders, Update Order Status |
| CUSTOMER | Browse Books, Place Orders, View Own Orders |

---

# 📄 API Documentation

Swagger UI is available after running the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🗄 Database

Database Used:

```text
MySQL
```

### Main Tables

- users
- books
- orders
- order_items

---

# ▶ Running the Project

## Clone Repository

```bash
git clone https://github.com/Deepali2160/bookstore-management-system.git
```

## Navigate to Project

```bash
cd bookstore-management-system
```

## Configure Database

Update the MySQL configuration in:

```text
src/main/resources/application.properties
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

# 🧪 Testing

The project includes:

- JUnit 5
- Mockito
- Postman API Testing

All core service layer functionalities are covered with unit tests.

---

# 📸 Screenshots

> Screenshots will be added after project completion.

- Swagger UI
- Login API
- Books API
- Orders API

---

# 📌 Future Enhancements

- ⭐ Book Reviews & Ratings
- ⭐ Payment Gateway Integration (Stripe/PayPal)
- ⭐ Docker Support
- ⭐ Cloud Deployment (Render/AWS)

---

# 👩‍💻 Author

**Deepali Mundra**

GitHub:

https://github.com/Deepali2160

Project Repository:

https://github.com/Deepali2160/bookstore-management-system

---

## ⭐ If you found this project helpful, don't forget to star the repository!