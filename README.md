# Expense Tracker

A full-stack expense tracking application built to practice backend
engineering with Spring Boot — a layered REST API (Controller → Service →
Repository), DTOs, request validation, and centralized exception handling,
paired with a lightweight vanilla JS frontend.

## Features

- Add, view, update, and delete expenses
- Organize expenses into categories
- Filter expenses by month and/or category
- Monthly spending summary with a category-wise breakdown
- Clean separation of concerns: Controller / Service / Repository layers
- Request validation with meaningful error responses
- Centralized exception handling (no raw stack traces leaked to clients)

## Tech stack

**Backend**
- Java 21
- Spring Boot 3.3
- Spring Web
- Spring Data JPA (Hibernate)
- Bean Validation
- H2 (in-memory database)
- Gradle

**Frontend**
- Vanilla HTML/CSS/JavaScript (`fetch` API — no framework)

> Authentication isn't included yet — intentionally scoped out to keep the
> project focused on the core REST/data-layer fundamentals first.

## Architecture

```
Frontend (fetch)
      ↓ HTTP
Controller   → HTTP layer only: deserializes JSON, validates via @Valid,
                delegates to the service, returns a ResponseEntity
      ↓
Service      → business logic, Entity ↔ DTO mapping
      ↓
Repository   → Spring Data JPA — talks to the database
      ↓
H2 Database
```

DTOs (`ExpenseRequestDTO`, `ExpenseResponseDTO`, `CategoryDTO`) keep the
API contract decoupled from the JPA entities, so the database schema can
change without breaking API consumers.

## Project structure

```
src/main/java/com/Abhigyan/ExpenseTracker/
├── ExpenseTrackerApplication.java   # entry point
├── config/       # CORS configuration
├── model/        # JPA entities — Expense, Category
├── repository/   # JpaRepository interfaces — data access layer
├── service/      # interfaces + implementations — business logic
├── controller/   # REST endpoints
├── dto/          # request/response shapes
└── exception/    # custom exceptions + global exception handler

frontend/
└── index.html    # single-page frontend, calls the API directly
```

## Getting started

### Prerequisites
- Java 21 (JDK)
- Gradle (only needed once, to generate the wrapper — see below)

### 1. Clone the repo
```bash
git clone https://github.com/<your-username>/expense-tracker.git
cd expense-tracker
```

### 2. Generate the Gradle wrapper (one-time)
If `gradlew` isn't already present:
```bash
gradle wrapper --gradle-version 8.10
```

### 3. Run the backend
```bash
./gradlew bootRun
```
The API starts on `http://localhost:8080`.

### 4. Run the frontend
Open `frontend/index.html` directly in your browser. It calls the API at
`http://localhost:8080/api`.

### 5. Inspect the database (optional)
Visit `http://localhost:8080/h2-console`:
- JDBC URL: `jdbc:h2:mem:expensedb`
- Username: `sa`
- Password: *(blank)*

## API reference

### Categories
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/categories` | Create a category |
| GET | `/api/categories` | List all categories |

### Expenses
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/expenses` | Create an expense |
| GET | `/api/expenses` | List expenses (`?month=`, `?categoryId=` optional) |
| GET | `/api/expenses/{id}` | Get one expense |
| PUT | `/api/expenses/{id}` | Update an expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| GET | `/api/expenses/summary` | Monthly total + category breakdown (`?month=`, `?year=` optional) |

### Example requests

```bash
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d '{"name": "Food"}'

curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{"title": "Groceries", "amount": 45.50, "date": "2026-09-04", "categoryId": 1}'

curl "http://localhost:8080/api/expenses/summary?month=9&year=2026"
```

## Roadmap

- [ ] Authentication (Spring Security + JWT)
- [ ] Pagination on list endpoints
- [ ] Database migrations with Flyway
- [ ] Unit and integration tests
- [ ] Swap H2 for Postgres

## License

MIT
