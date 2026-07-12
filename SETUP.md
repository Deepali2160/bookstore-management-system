# ⚙️ Bookstore Management System - Setup Guide

This guide explains how to set up and run the Bookstore Management System on your local machine.

---

# Prerequisites

Make sure the following are installed:

- Java 21
- Maven
- MySQL 8+
- Git

---

# 1. Clone the Repository

```bash
git clone https://github.com/Deepali2160/bookstore-management-system.git
```

```bash
cd bookstore-management-system
```

---

# 2. Create Database

Open MySQL and execute:

```sql
CREATE DATABASE bookstore_db;
```

---

# 3. Configure Database

Update the database configuration in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

---

# 4. Build the Project

```bash
mvn clean install
```

---

# 5. Run the Project

```bash
mvn spring-boot:run
```

or run the `BookstoreApplication` class directly from your IDE.

---

# 6. Default Admin Account

On the first application startup, a default admin account is created automatically.

Email:

```
admin@bookstore.com
```

Password:

```
admin123
```

If the admin already exists, the application skips creating it.

---

# 7. Swagger UI

After the application starts, open:

```
http://localhost:8080/swagger-ui/index.html
```

You can test all REST APIs directly from Swagger UI.

---

# 8. Authentication

1. Login using Admin or Customer credentials.
2. Copy the JWT token.
3. Click **Authorize** in Swagger.
4. Enter:

```
Bearer YOUR_JWT_TOKEN
```

Now you can access protected APIs.

---

# Project Modules

- User Authentication
- Book Management
- Review Management
- Order Management

---

Happy Coding 🚀