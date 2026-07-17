# 📘 API Test Documentation - Part 1

This document contains the test scenarios for **Customer APIs**.

**Base URL**

```
http://localhost:8081
```

---

# Test Scenario 1 - Create Valid Customer

## Objective

Verify that a customer can be created successfully with valid details.

### Request Type

```
POST
```

### Endpoint

```
/customers
```

### Full URL

```
http://localhost:8081/customers
```

### Request Body

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 23,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

### Expected Status

```
201 Created
```

### Expected Response

```json
{
  "customerId": "CUST1001",
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 23,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

---

# Test Scenario 2 - Customer Age Less Than 18

## Objective

Verify that customers below 18 years of age cannot be registered.

### Request Type

```
POST
```

### Endpoint

```
/customers
```

### Request Body

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 17,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating customer age must be between **18 and 65 years**.

---

# Test Scenario 3 - Customer Age Greater Than 65

## Objective

Verify that customers older than 65 years cannot be registered.

### Request Type

```
POST
```

### Endpoint

```
/customers
```

### Request Body

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 66,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating customer age must not exceed **65 years**.

---

# Test Scenario 4 - Invalid Email Address

## Objective

Verify email format validation.

### Request Type

```
POST
```

### Endpoint

```
/customers
```

### Request Body

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 23,
  "gender": "Male",
  "email": "karankamble.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating an invalid email address.

---

# Test Scenario 5 - Invalid Mobile Number

## Objective

Verify mobile number validation.

### Request Type

```
POST
```

### Endpoint

```
/customers
```

### Request Body

```json
{
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 23,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "98765",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating an invalid mobile number.

---

# Test Scenario 6 - Get Existing Customer

## Objective

Verify that an existing customer can be retrieved successfully.

### Request Type

```
GET
```

### Endpoint

```
/customers/CUST1001
```

### Full URL

```
http://localhost:8081/customers/CUST1001
```

### Request Body

Not Applicable

### Expected Status

```
200 OK
```

### Expected Response

```json
{
  "customerId": "CUST1001",
  "firstName": "Karan",
  "lastName": "Kamble",
  "age": 23,
  "gender": "Male",
  "email": "karan.kamble@example.com",
  "mobileNumber": "9876543210",
  "panNumber": "ABCDE1234F",
  "address": "Pune, Maharashtra"
}
```

---

# Test Scenario 7 - Get Non-Existing Customer

## Objective

Verify that the API returns **404 Not Found** for an unknown customer ID.

### Request Type

```
GET
```

### Endpoint

```
/customers/CUST9999
```

### Full URL

```
http://localhost:8081/customers/CUST9999
```

### Request Body

Not Applicable

### Expected Status

```
404 Not Found
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 404,
  "error": "Customer not found with ID: CUST9999",
  "path": "/customers/CUST9999"
}
```

---
---

# Test Scenario 8 - Create Valid Proposal

## Objective

Verify that a proposal is successfully created for an existing customer with valid details.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Full URL

```
http://localhost:8081/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
201 Created
```

### Expected Response

```json
{
  "proposalId": "PROP1001",
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G",
  "status": "DRAFT",
  "policyNumber": null
}
```

---

# Test Scenario 9 - Create Proposal for Non-Existing Customer

## Objective

Verify that proposal creation fails when the specified customer does not exist.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST9999",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
404 Not Found
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 404,
  "error": "Customer not found with ID: CUST9999",
  "path": "/proposals"
}
```

---

# Test Scenario 10 - Invalid Policy Term

## Objective

Verify that only policy terms available in the Reference Master are accepted.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 18,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 400,
  "error": "Invalid policy term.",
  "path": "/proposals"
}
```

---

# Test Scenario 11 - Invalid Payment Frequency

## Objective

Verify that only supported payment frequencies are accepted.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "WEEKLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 400,
  "error": "Invalid payment frequency.",
  "path": "/proposals"
}
```

---

# Test Scenario 12 - Sum Assured Below Minimum Limit

## Objective

Verify that the proposal cannot be created if the sum assured is below the minimum allowed value.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 90000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating that the sum assured must be at least **₹100,000**.

---

# Test Scenario 13 - Sum Assured Above Maximum Limit

## Objective

Verify that the proposal cannot be created if the sum assured exceeds the maximum allowed value.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 60000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating that the sum assured must not exceed **₹50,000,000**.

---

# Test Scenario 14 - Annual Premium Below Minimum

## Objective

Verify that the proposal cannot be created if the annual premium is below ₹5,000.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 4000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

Validation error indicating that the annual premium must be at least **₹5,000**.

---

# Test Scenario 15 - PAN Mandatory for Premium Above ₹50,000

## Objective

Verify that PAN is mandatory when the annual premium exceeds ₹50,000.

### Precondition

Customer **CUST1002 (Tejas Lahade)** exists without a PAN number.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1002",
  "policyTerm": 20,
  "sumAssured": 1500000,
  "annualPremium": 75000,
  "paymentFrequency": "YEARLY",
  "nomineeName": "Ruchika G"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 400,
  "error": "PAN is mandatory when annual premium exceeds 50000.",
  "path": "/proposals"
}
```

---

# Test Scenario 16 - Customer Cannot Be the Nominee

## Objective

Verify that the customer name and nominee name cannot be the same.

### Request Type

```
POST
```

### Endpoint

```
/proposals
```

### Request Body

```json
{
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Karan Kamble"
}
```

### Expected Status

```
400 Bad Request
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 400,
  "error": "Nominee cannot be the customer.",
  "path": "/proposals"
}
```

---

# Test Scenario 17 - Get Existing Proposal

## Objective

Verify that an existing proposal can be retrieved successfully.

### Request Type

```
GET
```

### Endpoint

```
/proposals/PROP1001
```

### Full URL

```
http://localhost:8081/proposals/PROP1001
```

### Request Body

Not Applicable

### Expected Status

```
200 OK
```

### Expected Response

```json
{
  "proposalId": "PROP1001",
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G",
  "status": "DRAFT",
  "policyNumber": null
}
```

---

# Test Scenario 18 - Get Non-Existing Proposal

## Objective

Verify that the API returns an appropriate error when the proposal ID does not exist.

### Request Type

```
GET
```

### Endpoint

```
/proposals/PROP9999
```

### Full URL

```
http://localhost:8081/proposals/PROP9999
```

### Request Body

Not Applicable

### Expected Status

```
404 Not Found
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 404,
  "error": "Proposal not found with ID: PROP9999",
  "path": "/proposals/PROP9999"
}
```

---
---

# Test Scenario 19 - Submit Valid Proposal

## Objective

Verify that a proposal in **DRAFT** status can be successfully submitted.

### Request Type

```
POST
```

### Endpoint

```
/proposals/PROP1001/submit
```

### Full URL

```
http://localhost:8081/proposals/PROP1001/submit
```

### Request Body

Not Applicable

### Expected Status

```
200 OK
```

### Expected Response

```json
{
  "proposalId": "PROP1001",
  "customerId": "CUST1001",
  "policyTerm": 20,
  "sumAssured": 1000000,
  "annualPremium": 25000,
  "paymentFrequency": "MONTHLY",
  "nomineeName": "Ruchika G",
  "status": "SUBMITTED",
  "policyNumber": "POL1001"
}
```

---

# Test Scenario 20 - Submit Already Submitted Proposal

## Objective

Verify that an already submitted proposal cannot be submitted again.

### Request Type

```
POST
```

### Endpoint

```
/proposals/PROP1001/submit
```

### Full URL

```
http://localhost:8081/proposals/PROP1001/submit
```

### Request Body

Not Applicable

### Expected Status

```
400 Bad Request
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 400,
  "error": "Proposal has already been submitted.",
  "path": "/proposals/PROP1001/submit"
}
```

---

# Test Scenario 21 - Submit Non-Existing Proposal

## Objective

Verify that submitting a non-existing proposal returns an appropriate error.

### Request Type

```
POST
```

### Endpoint

```
/proposals/PROP9999/submit
```

### Full URL

```
http://localhost:8081/proposals/PROP9999/submit
```

### Request Body

Not Applicable

### Expected Status

```
404 Not Found
```

### Expected Response

```json
{
  "timestamp": "2026-07-17T14:15:20",
  "status": 404,
  "error": "Proposal not found with ID: PROP9999",
  "path": "/proposals/PROP9999/submit"
}
```

---

# Test Scenario 22 - Get Audit History

## Objective

Verify that audit records are generated after proposal submission.

### Request Type

```
GET
```

### Endpoint

```
/audit/PROP1001
```

### Full URL

```
http://localhost:8081/audit/PROP1001
```

### Request Body

Not Applicable

### Expected Status

```
200 OK
```

### Expected Response

```json
[
  {
    "proposalId": "PROP1001",
    "action": "Proposal Submitted",
    "timestamp": "2026-07-17T14:15:20"
  }
]
```

---

# Test Scenario 23 - Get Available Policy Terms

## Objective

Verify that all supported policy terms are returned from the Reference Master.

### Request Type

```
GET
```

### Endpoint

```
/reference-master/policy-term
```

### Full URL

```
http://localhost:8081/reference-master/policy-term
```

### Request Body

Not Applicable

### Expected Status

```
200 OK
```

### Expected Response

```json
[
  10,
  15,
  20,
  25,
  30
]
```

---

# Test Scenario 24 - Get Available Payment Frequencies

## Objective

Verify that all supported payment frequencies are returned from the Reference Master.

### Request Type

```
GET
```

### Endpoint

```
/reference-master/payment-frequency
```

### Full URL

```
http://localhost:8081/reference-master/payment-frequency
```

### Request Body

Not Applicable

### Expected Status

```
200 OK
```

### Expected Response

```json
[
  "MONTHLY",
  "QUARTERLY",
  "HALF_YEARLY",
  "YEARLY"
]
```

---

# Overall Test Summary

| Scenario No. | Test Scenario | Expected Status |
|--------------|---------------|-----------------|
| 1 | Create Valid Customer | 201 Created |
| 2 | Customer Age Below 18 | 400 Bad Request |
| 3 | Customer Age Above 65 | 400 Bad Request |
| 4 | Invalid Email Address | 400 Bad Request |
| 5 | Invalid Mobile Number | 400 Bad Request |
| 6 | Get Existing Customer | 200 OK |
| 7 | Get Non-Existing Customer | 404 Not Found |
| 8 | Create Valid Proposal | 201 Created |
| 9 | Create Proposal for Non-Existing Customer | 404 Not Found |
| 10 | Invalid Policy Term | 400 Bad Request |
| 11 | Invalid Payment Frequency | 400 Bad Request |
| 12 | Sum Assured Below Minimum Limit | 400 Bad Request |
| 13 | Sum Assured Above Maximum Limit | 400 Bad Request |
| 14 | Annual Premium Below Minimum | 400 Bad Request |
| 15 | PAN Mandatory for Premium Above ₹50,000 | 400 Bad Request |
| 16 | Customer Cannot Be the Nominee | 400 Bad Request |
| 17 | Get Existing Proposal | 200 OK |
| 18 | Get Non-Existing Proposal | 404 Not Found |
| 19 | Submit Valid Proposal | 200 OK |
| 20 | Submit Already Submitted Proposal | 400 Bad Request |
| 21 | Submit Non-Existing Proposal | 404 Not Found |
| 22 | Get Audit History | 200 OK |
| 23 | Get Available Policy Terms | 200 OK |
| 24 | Get Available Payment Frequencies | 200 OK |

---

# Conclusion

All **24 API test scenarios** were successfully executed using **Postman**. The application correctly handled valid requests, input validation, business rule validation, resource lookup, proposal submission workflow, audit logging, and reference master retrieval.

The implemented APIs returned the expected HTTP status codes (**200**, **201**, **400**, and **404**) along with appropriate response bodies, demonstrating that the application meets the functional and validation requirements of the HDFC Life Policy Proposal Processing assignment.