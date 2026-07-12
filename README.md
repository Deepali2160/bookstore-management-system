# 📚 Bookstore Management System

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![JWT](https://img.shields.io/badge/Auth-JWT-red)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

A secure and scalable REST API for an online bookstore built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**. The application enables customers to browse books, write reviews, and place orders, while allowing administrators to manage books, reviews, inventory, and customer orders.

---

# 📑 Table of Contents

- 🚀 Features
- 🛠 Tech Stack
- 🏗 Architecture
- 📂 Project Structure
- 🔐 Security
- 📄 API Documentation
- 🗄 Database
- ▶ Running the Project
- 🔑 Default Admin Credentials
- 🧪 Testing
- 📌 Future Enhancements
- 👩‍💻 Author

---

# 🚀 Features

## 👤 User Authentication

- User Registration
- User Login
- JWT Authentication
- Password Encryption using BCrypt
- Role-Based Authorization (ADMIN & CUSTOMER)

---

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

---

## ⭐ Review Management

- Add Review
- View Reviews by Book
- One Review per Customer per Book
- Delete Review (Admin Only)

---

## 📦 Order Management

- Place Order
- View Customer Orders
- View Order by ID
- View All Orders (Admin)
- Update Order Status (Admin)
- Automatic Inventory Stock Update
- Pagination & Sorting
- Role-Based Order Access

---

## ⚙ Additional Features

- Global Exception Handling
- DTO Pattern
- Custom Mapper Classes
- Jakarta Bean Validation
- Swagger (OpenAPI)
- Unit Testing using JUnit 5 & Mockito

---

# 🛠 Tech Stack

- Java 21
- Spring Boot 3.5.16
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
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
│       └── application.properties
└── test
```

---

# 🔐 Security

The application uses **Spring Security** with **JWT Authentication**.

Passwords are encrypted using **BCrypt**, and JWT tokens are used for stateless authentication and authorization.

### Authentication Flow

1. Register a new customer.
2. Login using email and password.
3. Receive a JWT token.
4. Include the token in the `Authorization` header.
5. Access protected APIs based on your role.

### User Roles

| Role | Permissions |
|------|-------------|
| ADMIN | Manage books, reviews, inventory, view all orders, update order status |
| CUSTOMER | Browse books, place orders, view own orders, add reviews |

---

# 📄 API Documentation

Swagger UI is available after running the project:

```
http://localhost:8080/swagger-ui/index.html
```

---

# 🗄 Database

**Database:** MySQL

### Main Tables

- users
- books
- reviews
- orders
- order_items

---

# ▶ Running the Project

## 1. Clone the Repository

```bash
git clone https://github.com/Deepali2160/bookstore-management-system.git
```

## 2. Navigate to the Project

```bash
cd bookstore-management-system
```

## 3. Create Database

Execute the following command in MySQL:

```sql
CREATE DATABASE bookstore_db;
```

## 4. Configure Database

Update your MySQL username and password in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

## 5. Build the Project

```bash
mvn clean install
```

## 6. Run the Project

```bash
mvn spring-boot:run
```

Application URL:

```
http://localhost:8080
```

---

# 🔑 Default Admin Credentials

A default administrator account is automatically created when the application runs for the first time.

**Email**

```
admin@bookstore.com
```

**Password**

```
admin123
```

If the admin already exists, the application skips creating it.

---

# 🧪 Testing

The project includes:

- Unit Testing using JUnit 5
- Mockito for Mocking Dependencies
- API Testing using Swagger UI
- API Testing using Postman

### Unit Tests Implemented

- Authentication Service
- Book Service
- Order Service
- Review Service

---

# 📌 Future Enhancements

- ⭐ Shopping Cart Module
- ⭐ Wishlist Feature
- ⭐ Payment Gateway Integration (Stripe / Razorpay)
- ⭐ Email Notifications
- ⭐ Docker Support
- ⭐ Cloud Deployment (AWS / Render / Railway)

---

# 👩‍💻 Author

**Deepali Mundra**

**GitHub**

https://github.com/Deepali2160

**Project Repository**

https://github.com/Deepali2160/bookstore-management-system

---

⭐ If you found this project helpful, consider giving it a ⭐ on GitHub!