# E-Commerce Payment Gateway

A Spring Boot-based e-commerce backend application with integrated Razorpay payment gateway support. This application provides RESTful APIs for managing users, products, shopping carts, orders, and payment processing.

## 🚀 Features

- **User Management**: Create and manage user accounts
- **Product Catalog**: CRUD operations for products with inventory management
- **Shopping Cart**: Add, view, and clear cart items
- **Order Processing**: Create and track orders
- **Payment Integration**: Razorpay payment gateway integration
- **Webhook Support**: Handle payment status updates via Razorpay webhooks
- **MongoDB Integration**: NoSQL database for flexible data storage
- **Exception Handling**: Global error handling with custom exceptions

## 🛠️ Tech Stack

- **Framework**: Spring Boot 4.0.1
- **Language**: Java 17
- **Database**: MongoDB
- **Payment Gateway**: Razorpay
- **Build Tool**: Maven
- **Testing**: JUnit 5

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB 4.4+ (running locally or remote instance)
- Razorpay account (for payment processing)

## ⚙️ Configuration

### 1. Clone the Repository

```bash
git clone <repository-url>
cd ecommerce_payment_gateway
```

### 2. Configure Application Properties

Update `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: ecommerce_payment_gateway
  data:
    mongodb:
      uri: mongodb://localhost:27017/ecommerce_payment_gateway

server:
  port: 8080

razorpay:
  key: your_razorpay_key_id
  secret: your_razorpay_key_secret
```

**Important**: Replace the Razorpay credentials with your actual API keys from the [Razorpay Dashboard](https://dashboard.razorpay.com/app/keys).

### 3. Start MongoDB

Ensure MongoDB is running:

```bash
# For Windows
mongod

# For Linux/Mac
sudo systemctl start mongod
```

### 4. Build the Project

```bash
./mvnw clean install
```

For Windows:
```cmd
mvnw.cmd clean install
```

### 5. Run the Application

```bash
./mvnw spring-boot:run
```

For Windows:
```cmd
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### User Management

#### Create User
```http
POST /api/users
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "role": "CUSTOMER"
}
```

**Response**: `200 OK`
```json
{
  "id": "65abc123...",
  "username": "john_doe",
  "email": "john@example.com",
  "role": "CUSTOMER"
}
```

---

### Product Management

#### Create Product
```http
POST /api/products
Content-Type: application/json

{
  "name": "Wireless Mouse",
  "price": 29.99,
  "stock": 100
}
```

#### Get All Products
```http
GET /api/products
```

**Response**: `200 OK`
```json
[
  {
    "id": "65def456...",
    "name": "Wireless Mouse",
    "price": 29.99,
    "stock": 100
  }
]
```

---

### Shopping Cart

#### Add to Cart
```http
POST /api/cart/add
Content-Type: application/json

{
  "userId": "65abc123...",
  "productId": "65def456...",
  "quantity": 2
}
```

#### View Cart
```http
GET /api/cart/{userId}
```

#### Clear Cart
```http
DELETE /api/cart/{userId}/clear
```

---

### Order Management

#### Create Order
```http
POST /api/orders/{userId}
```

**Response**: `200 OK`
```json
{
  "orderId": "65ghi789...",
  "totalAmount": 59.98,
  "status": "CREATED",
  "items": [
    {
      "productId": "65def456...",
      "price": 29.99,
      "quantity": 2
    }
  ]
}
```

#### Get Order by ID
```http
GET /api/orders/{orderId}
```

#### Get User Orders
```http
GET /api/orders/user/{userId}
```

---

### Payment Processing

#### Create Payment
```http
POST /api/payments/{orderId}
```

**Response**: `200 OK`
```json
{
  "razorpayOrderId": "order_xyz123...",
  "currency": "INR",
  "amount": 59.98
}
```

---

### Webhook

#### Payment Webhook (Razorpay)
```http
POST /api/webhooks/payment
Content-Type: application/json

{
  "payload": {
    "payment": {
      "entity": {
        "id": "pay_xyz...",
        "order_id": "order_xyz123...",
        "status": "captured"
      }
    }
  }
}
```

## 🗂️ Project Structure

```
ecommerce_payment_gateway/
├── src/
│   ├── main/
│   │   ├── java/com/example/ecommerce_payment_gateway/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── exception/       # Custom exceptions & handlers
│   │   │   ├── model/           # MongoDB entities
│   │   │   ├── repository/      # MongoDB repositories
│   │   │   ├── service/         # Business logic
│   │   │   ├── util/            # Utility classes
│   │   │   └── webhook/         # Webhook handlers
│   │   └── resources/
│   │       └── application.yaml # Application configuration
│   └── test/                    # Unit tests
├── pom.xml                      # Maven dependencies
└── README.md
```

## 🔒 Security Considerations

1. **Never commit** your actual Razorpay API keys to version control
2. Use environment variables for sensitive configuration:
   ```bash
   export RAZORPAY_KEY=your_key
   export RAZORPAY_SECRET=your_secret
   ```
3. Implement authentication/authorization (JWT, OAuth2) for production use
4. Validate webhook signatures from Razorpay
5. Use HTTPS in production environments

## 🧪 Testing

Run all tests:
```bash
./mvnw test
```

For Windows:
```cmd
mvnw.cmd test
```

## 🚀 Deployment

### Docker Deployment (Optional)

Create a `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
docker build -t ecommerce-payment-gateway .
docker run -p 8080:8080 ecommerce-payment-gateway
```

## 🐛 Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB is running: `sudo systemctl status mongod`
- Check connection URI in `application.yaml`
- Verify network connectivity and firewall settings

### Razorpay Integration Issues
- Verify API keys are correct
- Check Razorpay dashboard for API status
- Review webhook logs in Razorpay dashboard

### Build Failures
- Ensure Java 17 is installed: `java -version`
- Clean Maven cache: `./mvnw clean`
- Check internet connectivity for dependency downloads

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/my-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/my-feature`
5. Submit a Pull Request

## 📧 Contact

For questions or support, please open an issue in the repository.

## 🔄 Workflow Example

1. **Create a user account**
2. **Browse products** via GET `/api/products`
3. **Add items to cart** via POST `/api/cart/add`
4. **Create an order** via POST `/api/orders/{userId}`
5. **Initiate payment** via POST `/api/payments/{orderId}`
6. **Complete payment** on Razorpay's interface
7. **Webhook updates** order status automatically
8. **Verify order status** via GET `/api/orders/{orderId}`

---

**Happy Coding! 🎉**

