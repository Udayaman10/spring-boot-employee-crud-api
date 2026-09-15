# 👨‍💼 Employee CRUD REST API

A production-style **Employee Management REST API** built with **Spring Boot**, implementing complete CRUD operations with a clean layered architecture.

The project demonstrates how a client request flows through the **REST Controller → Service → DAO → EntityManager → JPA/Hibernate → MySQL** layers.

---

## 📌 Project Overview

The Employee CRUD API provides endpoints to create, retrieve, update, partially update, and delete employee records.

The main goal of this project is to understand and implement a structured Spring Boot backend using:

- RESTful APIs
- Layered Architecture
- Dependency Injection
- DAO Pattern
- Service Layer
- JPA / Hibernate
- EntityManager
- MySQL
- Transaction Management

---

## ✨ Features

- ✅ Create employee
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

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| ☕ Java | Programming Language |
| 🌱 Spring Boot | Backend Framework |
| 🌐 Spring Web | REST API Development |
| 🗃️ JPA | Persistence Specification |
| ⚙️ Hibernate | ORM Implementation |
| 🔌 EntityManager | Database Operations |
| 🐬 MySQL | Database |
| 📦 Maven | Build & Dependency Management |
| 🔄 Jackson JsonMapper | JSON Processing & PATCH |
| 🧪 Postman | API Testing |
| 🔧 Git & GitHub | Version Control |

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