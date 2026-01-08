# Microservices Project — Inventory Service + Order Service

This project consists of two Spring Boot microservices:

1. **Inventory Service (Port 8081)** — Manages product inventory and batch-level stock.
2. **Order Service (Port 8082)** — Places orders and updates stock by communicating with Inventory Service.

Both services use:
- Java 17
- Spring Boot
- H2 Database
- Liquibase for data loading
- REST API communication
- JUnit5 + Mockito for testing

---

# 🚀 Project Setup Instructions

## 1️⃣ Clone the repository
```bash
git clone https://github.com/<your-username>/Microservices-Project.git
cd Microservices-Project
```

## 2️⃣ Start Inventory Service
```bash
cd InventoryService
mvn spring-boot:run
```
Runs at:  
`http://localhost:8081`

## 3️⃣ Start Order Service
Open a second terminal:
```bash
cd OrderService
mvn spring-boot:run
```
Runs at:  
`http://localhost:8082`

⚠ Inventory Service **must** be started first or Order Service will not work.

---

# 📘 API Documentation

## 📦 Inventory Service — Port 8081

### ➤ GET `/inventory/{productId}`
Returns inventory batches sorted by expiry date.

**Sample Response**
```json
{
  "productId": 1005,
  "productName": "Smartwatch",
  "batches": [
    { "batchId": 2, "quantity": 52, "expiryDate": "2026-05-30" },
    { "batchId": 5, "quantity": 39, "expiryDate": "2026-03-31" }
  ]
}
```

---

### ➤ POST `/inventory/update?productId=1005&quantity=10`
Deducts stock from oldest batch (FIFO method).

**Sample Response**
```
Inventory updated successfully
```

---

## 🛒 Order Service — Port 8082

### ➤ POST `/order`
Places a product order.  
Automatically checks inventory availability via Inventory Service.

**Sample Request**
```json
{
  "productId": 1002,
  "quantity": 3
}
```

**Sample Response**
```json
{
  "orderId": 5012,
  "productId": 1002,
  "productName": "Smartphone",
  "quantity": 3,
  "status": "PLACED",
  "reservedFromBatchIds": [9],
  "message": "Order placed. Inventory reserved."
}
```

---

# 🗄 H2 Database Access

## Inventory Service H2 Console
```
http://localhost:8081/h2-console
JDBC URL: jdbc:h2:mem:inventorydb
```

## Order Service H2 Console
```
http://localhost:8082/h2-console
JDBC URL: jdbc:h2:mem:orderdb
```

---

# 🧬 Liquibase Data Loading

Both microservices automatically load:

- Database schema
- Initial CSV data

Using files located under:

```
src/main/resources/db/changelog/
```

---

# 🧪 Testing Instructions

Run all tests in any service:

```bash
mvn test
```

### ✔ Unit Tests
Located in:
```
src/test/java/.../service
```
Covers:
- Inventory logic
- FIFO stock consumption
- Order validation

### ✔ Controller Tests (MockMvc)
Located in:
```
src/test/java/.../controller
```
Covers:
- GET /inventory/{id}
- POST /inventory/update
- POST /order

### ✔ Integration Tests
Validates:
- Liquibase schema creation
- Repository functionality
- H2 database operations

---

# 📦 Build JAR Files

```bash
mvn clean package
```

Output JAR will be in:

```
target/
```

---

# 🧱 Microservices Communication Flow

```
Order Service ---> GET /inventory/{id} ---> Inventory Service
Order Service ---> validates quantity available
Order Service ---> POST /inventory/update ---> Inventory Service
Order Service ---> saves order to DB
```

---

# 👤 Author

**Mohit Kumar**  
Körber Assignment Project

---
