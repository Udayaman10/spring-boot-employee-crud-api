# 👨‍💼 Employee CRUD REST API

A production-style **Employee Management REST API** built with **Spring Boot**, implementing complete CRUD operations with a clean layered architecture. It is containerized with **Docker** and deployed to the cloud.

🔗 **Live API:** https://spring-boot-employee-crud-api.onrender.com/api/employees

> Hosted on Render's free plan, so the first request after a period of inactivity can take about 50 seconds while the app wakes up.

The project demonstrates how a client request flows through the **REST Controller → Service → DAO → EntityManager → JPA/Hibernate → MySQL** layers.

---

## 📌 Project Overview

The Employee CRUD API provides endpoints to create (single and bulk), retrieve, update, partially update, and delete employee records.

The main goal of this project is to understand and implement a structured Spring Boot backend, and to deploy it end to end, using:

- RESTful APIs
- Layered Architecture
- Dependency Injection
- DAO Pattern
- Service Layer
- JPA / Hibernate
- EntityManager
- MySQL
- Transaction Management
- Docker and cloud deployment

---

## ✨ Features

- ✅ Create employee
- ✅ Bulk create employees (one request, all-or-nothing transaction)
- ✅ Get all employees
- ✅ Get employee by ID
- ✅ Update employee
- ✅ Partial update using PATCH
- ✅ Delete employee
- ✅ MySQL database integration
- ✅ JPA/Hibernate persistence
- ✅ EntityManager-based database operations
- ✅ Constructor-based Dependency Injection
- ✅ Service and DAO layers
- ✅ Transaction management
- ✅ Environment-based database credentials
- ✅ Dockerized with a multi-stage build
- ✅ Deployed to the cloud with automatic redeploys on every push

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| ☕ Java 21 | Programming Language |
| 🌱 Spring Boot | Backend Framework |
| 🌐 Spring Web | REST API Development |
| 🗃️ JPA | Persistence Specification |
| ⚙️ Hibernate | ORM Implementation |
| 🔌 EntityManager | Database Operations |
| 🐬 MySQL | Database |
| 📦 Maven | Build & Dependency Management |
| 🔄 Jackson JsonMapper | JSON Processing & PATCH |
| 🐳 Docker | Containerization |
| ☁️ Render | Application Hosting |
| 🗄️ Aiven | Managed MySQL Hosting |
| 🧪 Postman | API Testing |
| 🔧 Git & GitHub | Version Control |

---

## 📡 API Endpoints

Base URL: `https://spring-boot-employee-crud-api.onrender.com/api`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/employees` | Get all employees |
| GET | `/employees/{id}` | Get one employee by ID |
| POST | `/employees` | Create an employee |
| POST | `/employees/bulk` | Create multiple employees |
| PUT | `/employees` | Update an employee |
| PATCH | `/employees/{id}` | Partially update an employee |
| DELETE | `/employees/{id}` | Delete an employee |

**Create an employee** (`POST /employees`):

```json
{
  "firstName": "Aarav",
  "lastName": "Sharma",
  "email": "aarav.sharma@example.com"
}
```

**Create multiple employees** (`POST /employees/bulk`): send a JSON array of the objects above.

```json
[
  { "firstName": "Aarav", "lastName": "Sharma", "email": "aarav.sharma@example.com" },
  { "firstName": "Priya", "lastName": "Verma", "email": "priya.verma@example.com" }
]
```

---

# 🏗️ Architecture

```text
                    ┌───────────────┐
                    │    Client     │
                    │   / Postman  │
                    └───────┬───────┘
                            │
                            ▼
                ┌──────────────────────┐
                │   REST Controller    │
                │ EmployeeRestController│
                └──────────┬───────────┘
                           │
                           ▼
                ┌──────────────────────┐
                │    Service Layer     │
                │ EmployeeService      │
                └──────────┬───────────┘
                           │
                           ▼
                ┌──────────────────────┐
                │      DAO Layer       │
                │     EmployeeDAO      │
                └──────────┬───────────┘
                           │
                           ▼
                ┌──────────────────────┐
                │     EntityManager    │
                └──────────┬───────────┘
                           │
                           ▼
                ┌──────────────────────┐
                │    JPA / Hibernate   │
                └──────────┬───────────┘
                           │
                           ▼
                ┌──────────────────────┐
                │    MySQL Database    │
                └──────────────────────┘
```

---

## ☁️ Deployment

```text
GitHub (main) ──push──▶ Render (Docker build) ──▶ Spring Boot app ──▶ Aiven MySQL
```

- **Application:** built from the `Dockerfile` (multi-stage: Maven builds the JAR, a slim JRE image runs it) and hosted on Render
- **Database:** managed MySQL on Aiven, connected over SSL
- **CI/CD:** Render redeploys automatically on every push to `main`
- **Configuration:** database credentials are read from environment variables, so no secrets are stored in the repository

| Environment variable | Purpose |
|---|---|
| `DB_URL` | JDBC URL of the MySQL database |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `PORT` | Server port (set automatically by Render, defaults to 8080) |

---

## ▶️ Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/Udayaman10/spring-boot-employee-crud-api.git
   cd spring-boot-employee-crud-api
   ```
2. Create a local MySQL database.
3. Set the environment variables, for example:
   ```bash
   export DB_URL=jdbc:mysql://localhost:3306/your_database
   export DB_USERNAME=your_username
   export DB_PASSWORD=your_password
   ```
4. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```
5. The API is available at `http://localhost:8080/api/employees`.

On Windows, set the variables in your IDE's run configuration, or use `set` in Command Prompt instead of `export`.