# 📚 Sample API Requests

These sample requests can be used to quickly test the REST APIs.

---

# Register Customer

POST

```
/api/auth/register
```

```json
{
  "name": "Rahul",
  "email": "rahul@gmail.com",
  "password": "password123"
}
```

---

# Login

POST

```
/api/auth/login
```

```json
{
  "email": "rahul@gmail.com",
  "password": "password123"
}
```

---

# Add Book (ADMIN)

POST

```
/api/books
```

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "price": 899,
  "stockQuantity": 20,
  "genre": "TECHNOLOGY",
  "description": "A handbook of agile software craftsmanship.",
  "imageUrl": "https://example.com/clean-code.jpg"
}
```

---

# Update Book

PUT

```
/api/books/{id}
```

```json
{
  "title": "Clean Code - Updated",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "price": 950,
  "stockQuantity": 25,
  "genre": "TECHNOLOGY",
  "description": "Updated Edition",
  "imageUrl": "https://example.com/clean-code.jpg"
}
```

---

# Place Order

POST

```
/api/orders
```

```json
{
  "items": [
    {
      "bookId": 2,
      "quantity": 2
    },
    {
      "bookId": 3,
      "quantity": 1
    }
  ]
}
```

---

# Update Order Status (ADMIN)

PUT

```
/api/orders/admin/{id}/status
```

```json
{
  "orderStatus": "DELIVERED"
}
```

---

# Add Review

POST

```
/api/reviews
```

```json
{
  "bookId": 2,
  "rating": 5,
  "comment": "Excellent Spring Boot book!"
}
```

---

# Search Books by Title

GET

```
/api/books/search?title=Clean
```

---

# Search Books by Author

GET

```
/api/books/search?author=Robert
```

---

# Filter Books by Genre

GET

```
/api/books/genre/TECHNOLOGY
```

---

# Get All Books

GET

```
/api/books
```

---

# Get Book By ID

GET

```
/api/books/2
```

---

# Get Customer Orders

GET

```
/api/orders
```

---

# Get All Orders (ADMIN)

GET

```
/api/orders/admin
```

---

# Get Reviews of a Book

GET

```
/api/reviews/book/2
```

---

# Delete Review (ADMIN)

DELETE

```
/api/reviews/{id}
```

---

All secured endpoints require a valid JWT token.

Use the following Authorization header:

```
Bearer YOUR_JWT_TOKEN
```