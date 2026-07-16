# 🛡️ Policy Proposal Processing API

A Spring Boot REST API that simulates the policy proposal lifecycle for a life insurance company. The application manages customers, insurance proposals, reference master data, and audit records using a clean layered architecture and in-memory storage.

> Developed as part of the HDFC Life Java Backend Assessment.

---

## 🚀 Features

### 📋 Reference Master
- Retrieve available policy terms
- Retrieve payment frequency options

### 👤 Customer Management
- Create Customer
- Get All Customers
- Get Customer by ID
- Update Customer

### 📄 Policy Proposal Management
- Create Policy Proposal
- Proposal starts with **DRAFT** status
- Submit Proposal
- Automatically generate Policy Number
- Change Proposal Status to **SUBMITTED**

### 📝 Audit Trail
- Automatically records proposal submission
- Maintains timestamped audit history

---

# 🏗️ Project Architecture

```
Client (Postman)
        │
        ▼
Controller
        │
        ▼
Service
        │
        ▼
Repository
        │
        ▼
In-Memory Storage (ConcurrentHashMap)
```

The application follows a **Layered Architecture**, ensuring separation of concerns and maintainable code.

---

# 📁 Project Structure

```
src
└── main
    └── java
        └── com.hdfclife.policyproposal
            ├── controller
            ├── service
            ├── repository
            ├── model
            ├── dto
            ├── config
            ├── exception
            ├── util
            └── validation
```

---

# 🛠️ Technology Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.x |
| Maven | Latest |
| Lombok | Yes |
| REST API | Spring Web |
| Storage | Java ConcurrentHashMap |
| Build Tool | Maven |
| Testing Tool | Postman |

---

# ⚙️ API Endpoints

## Reference Master

| Method | Endpoint |
|--------|----------|
| GET | `/reference-master/policy-term` |
| GET | `/reference-master/payment-frequency` |

---

## Customers

| Method | Endpoint |
|--------|----------|
| POST | `/customers` |
| GET | `/customers` |
| GET | `/customers/{customerId}` |
| PUT | `/customers/{customerId}` |

---

## Proposals

| Method | Endpoint |
|--------|----------|
| POST | `/proposals` |
| POST | `/proposals/{proposalId}/submit` |

---

## Audit

| Method | Endpoint |
|--------|----------|
| GET | `/audits` |

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
Submit Proposal
        │
        ▼
Generate Policy Number
        │
        ▼
Status = SUBMITTED
        │
        ▼
Create Audit Record
```

---

# 📌 Sample Customer Request

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 22,
  "gender": "Male",
  "email": "karan.kamble@gmail.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune"
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
  "paymentFrequency": "YEARLY",
  "nomineeName": "Vaishnavi Kamble"
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

Application starts on

```
http://localhost:8081
```

---

# 🧪 API Testing

All APIs were tested using **Postman**.

Recommended execution order:

1. Get Reference Master
2. Create Customer
3. Get Customer
4. Update Customer
5. Create Proposal
6. Submit Proposal
7. View Audit Logs

---

# 💡 Design Decisions

- Layered Architecture (Controller → Service → Repository)
- Constructor-based Dependency Injection
- DTO Pattern for request and response models
- Thread-safe in-memory storage using `ConcurrentHashMap`
- AtomicInteger for unique ID generation
- ResponseEntity for proper HTTP responses
- Lombok to reduce boilerplate code

---

# 📈 Future Enhancements

- Database integration (PostgreSQL/MySQL)
- Spring Data JPA
- Bean Validation (`@Valid`)
- Global Exception Handling
- JWT Authentication & Authorization
- Unit & Integration Testing
- Docker Support
- Swagger/OpenAPI Documentation
- Logging Framework (SLF4J)

---

# 👨‍💻 Author

**Karan Kamble**

Java Backend Developer

---
