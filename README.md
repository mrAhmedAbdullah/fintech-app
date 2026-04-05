# Finance Data Processing & Access Control Backend

A robust Spring Boot backend for managing financial records with Role-Based Access Control (RBAC), data validation, and real-time dashboard analytics.

## Features 
- **RBAC (Role-Based Access Control):** Differentiated access for `ADMIN`, `ANALYST`, and `VIEWER`.
- **Financial Management:** Full CRUD operations for income and expense records.
- **Dashboard Analytics:** Server-side aggregation using **Java Streams** for real-time totals.
- **Data Integrity:** Precision handling of currency using `BigDecimal`.
- **Validation:** Robust input validation and global exception handling.
- **Database:** In-memory H2 database for zero-config setup.

## 🛠 Tech Stack
- **Language:** Java 25
- **Framework:** Spring Boot 4.0.5
- **Security:** Spring Security (Basic Auth)
- **Persistence:** Spring Data JPA (Hibernate)
- **Database:** H2 (In-memory)
- **Utility:** Lombok, Jakarta Validation

## 📂 Project Structure
```text
src/main/java/in/fintech/backend
├── config       # Security & RBAC Configuration
├── controller   # REST API Endpoints
├── exceptions   # Global Error Handling
├── model        # Database Entities (FinancialRecord, User)
├── repository   # JPA Data Access Layer
└── service      # Business Logic & Analytics (Java Streams)