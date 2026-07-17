# Design Decisions

## Overview

The project was designed with simplicity, maintainability, and clean architecture in mind while fulfilling all assignment requirements.

---

## 1. Layered Architecture

The application follows a layered architecture consisting of:

- Controller
- Service
- Repository

This keeps responsibilities separated.

Advantages:

- Easier maintenance
- Better readability
- Easier testing
- Scalable design

---

## 2. DTO Pattern

Request and Response DTOs were created instead of exposing model classes directly.

Benefits:

- Prevents exposing internal implementation
- Better API design
- Easier validation
- Loose coupling

---

## 3. In-Memory Repository

ConcurrentHashMap was chosen for data storage because the assignment specifically required in-memory storage.

Advantages:

- Fast access
- Thread-safe
- No external database dependency

---

## 4. Bean Validation

Jakarta Bean Validation annotations were used for validating incoming requests.

Examples:

- @NotBlank
- @Email
- @Min
- @Max
- @Pattern

Benefits:

- Reduces manual validation code
- Cleaner controllers
- Standard validation mechanism

---

## 5. Business Validation

Business rules that cannot be handled through Bean Validation were implemented in the Service layer.

Examples:

- Customer must exist
- PAN required for premium above ₹50,000
- Nominee cannot be customer
- Proposal cannot be submitted twice
- Payment frequency validation
- Policy term validation

Keeping business rules inside the service layer maintains proper separation of concerns.

---

## 6. Global Exception Handling

A centralized exception handler was implemented using @ControllerAdvice.

Advantages:

- Consistent error responses
- Cleaner controllers
- Better API usability

---

## 7. Reference Master Service

Static reference data such as:

- Policy Terms
- Payment Frequencies

is maintained through a dedicated ReferenceMasterService.

Advantages:

- Single source of truth
- Easy future expansion
- Better maintainability

---

## 8. Unique ID Generation

AtomicInteger was used for generating:

- Customer IDs
- Proposal IDs
- Policy Numbers

Advantages:

- Thread-safe
- Simple implementation
- Suitable for in-memory applications

---

## 9. Audit Logging

An audit record is generated whenever a proposal is successfully submitted.

Purpose:

- Maintain proposal history
- Improve traceability
- Simulate real insurance workflow

---

## Conclusion

The application follows clean coding practices and separates validation, business logic, persistence, and API layers. The design emphasizes maintainability, readability, and scalability while satisfying all functional requirements of the assignment.