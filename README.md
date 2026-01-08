**Order Service — Microservice 2**

The Order Service handles order placement, communicates with the Inventory Service to validate available stock, and updates inventory accordingly.
It acts as the consumer of Inventory Service APIs.

**Features-**
Accepts new product orders
Communicates with Inventory Service via REST API
Checks real-time availability before placing an order
Updates inventory after successful order placement
Stores orders in H2 in-memory database

**API Endpoints**
**POST Request **
http://localhost:8082/order
**Body**
{
  "productId": 1005,
  "quantity": 25
}

**Endpoint results **
{
	"message": "Order placed successfully. Inventory reserved.",
	"orderId": 1,
	"productId": 1005,
	"productName": "Smartwatch",
	"quantity": 25,
	"reservedFromBathIds": [7,2],
	"status": "PLACED"
}


**Communication With Inventory Service**
Order Service sends REST calls to:
GET  http://localhost:8081/inventory/{productId}
POST http://localhost:8081/inventory/update?productId=ID&quantity=QTY
