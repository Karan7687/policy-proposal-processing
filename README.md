# 🛡️ Policy Proposal Processing API


A Spring Boot REST API developed as part of the ** Java Backend Assessment**.
- 📄 [Spring Boot Notes (PDF)](https://drive.google.com/file/d/1SeMusE30yJ6P9ublFchwyQdctsA1CuQ6/view?usp=sharing)
The application simulates the end-to-end policy proposal lifecycle for a life insurance company. It enables customer onboarding, proposal creation, proposal submission, reference data management, and audit tracking while enforcing business validations through a clean layered architecture.

---

# 📖 Overview

This project demonstrates backend development concepts including:

- RESTful API development
- Layered Architecture
- DTO Pattern
- Business Rule Validation
- Bean Validation
- Global Exception Handling
- Thread-safe In-Memory Repository
- Audit Trail Generation

The application intentionally uses **ConcurrentHashMap** instead of a database, as required by the assessment.

---

# 🚀 Features

## 📋 Reference Master

- Retrieve available Policy Terms
- Retrieve available Payment Frequencies

## 👤 Customer Management

- Create Customer
- Get Customer by ID

## 📄 Policy Proposal Management

- Create Policy Proposal
- Retrieve Proposal by ID
- Submit Proposal
- Automatic Policy Number Generation
- Proposal Status Management (DRAFT → SUBMITTED)

## 📝 Audit Management

- Automatically records proposal submission
- Retrieves audit history for a proposal

---

# 🏗️ Architecture

```
                 Client (Postman)
                        │
                        ▼
                 REST Controller
                        │
                        ▼
                  Service Layer
        (Business Rules & Validation)
                        │
                        ▼
                Repository Layer
                        │
                        ▼
      ConcurrentHashMap (In-Memory Storage)
```

The project follows a **Layered Architecture**, ensuring clear separation between API handling, business logic, and data persistence.

---

# 📂 Project Structure

```
src
└── main
    └── java
        └── com.hdfclife.policyproposal
            ├── controller
            ├── dto
            ├── exception
            ├── model
            ├── repository
            └── service
```

---

# 🛠️ Technology Stack

| Technology | Details |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.x |
| Spring Web | REST APIs |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |
| Bean Validation | Jakarta Validation |
| Storage | ConcurrentHashMap |
| API Testing | Postman |

---

# 📌 REST APIs

## Reference Master

| Method | Endpoint |
|--------|----------|
| GET | `/reference-master/policy-term` |
| GET | `/reference-master/payment-frequency` |

---

## Customer

| Method | Endpoint |
|--------|----------|
| POST | `/customers` |
| GET | `/customers/{customerId}` |

---

## Proposal

| Method | Endpoint |
|--------|----------|
| POST | `/proposals` |
| GET | `/proposals/{proposalId}` |
| POST | `/proposals/{proposalId}/submit` |

---

## Audit

| Method | Endpoint |
|--------|----------|
| GET | `/audit/{proposalId}` |

---

# 🔄 Business Workflow

```
Create Customer
        │
        ▼
Create Proposal
(Status = DRAFT)
        │
        ▼
Business Validation
        │
        ▼
Submit Proposal
        │
        ▼
Generate Policy Number
        │
        ▼
Status → SUBMITTED
        │
        ▼
Create Audit Record
```

---

# ✅ Business Rules Implemented

- Customer age must be between **18 and 65** years.
- Customer must exist before creating a proposal.
- Policy Term must be one of:
    - 10 Years
    - 15 Years
    - 20 Years
    - 25 Years
    - 30 Years
- Sum Assured must be between **₹100,000** and **₹50,000,000**.
- Annual Premium must be at least **₹5,000**.
- PAN is mandatory if Annual Premium exceeds **₹50,000**.
- Payment Frequency must exist in Reference Master.
- Customer cannot be the nominee.
- Proposal is initially created with **DRAFT** status.
- A proposal can only be submitted once.
- Policy Number is generated only after successful submission.
- Every successful submission creates an Audit Record.

---

# ⚠️ Exception Handling

Centralized exception handling has been implemented using **@ControllerAdvice**.

Handled scenarios include:

- Resource Not Found (404)
- Business Validation Failure (400)
- Bean Validation Failure (400)
- Invalid Request Body (400)
- Internal Server Error (500)

---

# 📌 Sample Customer Request

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 23,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Baner, Pune"
}
```

---

# 📌 Sample Proposal Request

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Tejas Lahade"
}
```

---

# ▶️ Running the Application

Clone the repository

```bash
git clone https://github.com/<your-username>/policy-proposal-api.git
```

Navigate to the project

```bash
cd policy-proposal-api
```

Build the project

```bash
mvn clean install
```

Run the application

```bash
mvn spring-boot:run
```

Application URL

```
http://localhost:8081
```

---

# 🧪 Testing

The application was manually tested using **Postman**.

Testing included:

- Customer APIs
- Proposal APIs
- Audit APIs
- Reference Master APIs
- Bean Validation
- Business Rule Validation
- Exception Handling
- End-to-End Proposal Lifecycle

---

# 💡 Design Highlights

- Layered Architecture (Controller → Service → Repository)
- Constructor-Based Dependency Injection
- DTO Pattern for API contracts
- Thread-safe In-Memory Repository using ConcurrentHashMap
- AtomicInteger for unique ID generation
- Bean Validation for request validation
- Service-layer business validation
- Global exception handling using @ControllerAdvice
- ResponseEntity for consistent HTTP responses
- Lombok to reduce boilerplate code

---

# 📈 Future Enhancements

- MySQL/PostgreSQL Integration
- Spring Data JPA
- JWT Authentication & Authorization
- Swagger / OpenAPI Documentation
- Docker Containerization
- CI/CD Pipeline
- Persistent Audit Storage
- Logging using SLF4J
- Enhanced Unit & Integration Test Coverage

---

# 👨‍💻 Author

**Karan Kamble**
=======
Java Backend Developer
