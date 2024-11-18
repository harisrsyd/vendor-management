# Vendor Management API

## About
This API CRUD for managing vendors. It's built using java for the purpose of learning and assignment for a job interview only.
In this project, I used **_Spring Boot_**, **_Spring Data JPA_**, **_Spring Security_** for basic authentication, **_Spring AOP_** for simple Rate Limiter and applied the best practices for RESTful API.
I would really appreciate any feedback or suggestions for improvement. 

Best Regards,
Haris Naufal Rasyid

## Getting Started (How to run the project)
### Prerequisites
- Java 21+
- OpenJDK 21+
- PostgreSQL 14+ or Any other RDBMS
- Git for version control

### Setup Local
1. **Clone this repository**
```bash
https://github.com/harisrsyd/vendor-management.git
```
2. **Create a new database in PostgreSQL**
```sql 
CREATE DATABASE vendor_management;
```
3. **Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, etc)**
4. **Change the database configuration in `application.properties`**
5. **Wait until all dependencies are downloaded**

### Run the project
1. **Make sure your java version is 21+**
2. **Run the project**
```bash
mvn spring-boot:run
```
3. **Verify the project is running properly** (default port is 9090)
```bash
curl http://localhost:9090/api/v1/vendors
```
4. **You can also access the API documentation in Swagger UI**
```bash
http://localhost:9090/swagger-ui.html
```

## API Endpoints Documentation
You can access the full API endpoint documentation in **the docs folder** at OpenApi file or by accessing the **Swagger UI** in your browser.

## Authentication
I have implemented basic authentication using **Spring Security**. Before accessing the API vendor-management, the user must provide the username and password. If the user doesn't have any credentials, the user can register first.

## Simple Rate Limiter
Now, I have implemented a simple rate limiter using **Spring AOP**. The rate limiter is applied to all endpoints. The rate limiter is set to five (5) requests per second. If the user exceeds the limit, the user will get a `(429) Too Many Requests` response.

