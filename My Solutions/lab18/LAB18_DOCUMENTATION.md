# Lab 18: Spring Security - Basic Authentication with In-Memory Users

## 📋 Overview

This lab demonstrates how to implement **basic authentication** using **Spring Security** with **in-memory user management**. The project extends a simple Spring Boot CRUD application for managing products by adding security features to protect API endpoints.

---

## 🎯 Learning Objectives

- Understand Spring Security fundamentals
- Implement Basic HTTP Authentication
- Configure in-memory user management
- Secure REST API endpoints
- Use stateless session management
- Test authenticated requests using Thunder Client/Postman

---

## 🏗️ Project Structure

```
lab18/
├── src/main/java/psu/edu/ch06/crud04/
│   ├── Crud04Application.java          # Main Spring Boot application
│   ├── config/
│   │   └── SecurityConfig.java         # Security configuration
│   ├── controller/
│   │   └── ProductController.java      # REST API endpoints
│   ├── model/
│   │   ├── Product.java                # Product entity
│   │   └── User.java                   # User entity (record)
│   ├── repository/
│   │   └── ProductRepository.java      # Data access layer
│   └── security/
│       └── UserPrincipal.java          # UserDetails adapter
└── src/main/resources/
    ├── application.properties          # Database & app configuration
    ├── schema.sql                      # Database schema
    └── data.sql                        # Initial data
```

---

## 📦 Dependencies

The project uses the following key dependencies (configured in `pom.xml`):

```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- Spring Data JDBC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

---

## 🔐 Security Implementation

### Step 1: Create the User Model

The `User` class is a simple Java record that represents a user in our system.

**File:** `model/User.java`

```java
package psu.edu.ch06.crud04.model;

import org.springframework.data.annotation.Id;

public record User(
    @Id Integer id,
    String username,
    String password
) {}
```

**Key Points:**
- Uses Java **records** (introduced in Java 14+) for concise data classes
- Contains three fields: `id`, `username`, and `password`
- The `@Id` annotation marks the primary key field

---

### Step 2: Create the UserPrincipal Adapter

Spring Security requires users to implement the `UserDetails` interface. Since our `User` record doesn't implement this interface, we create an **adapter class** called `UserPrincipal`.

**File:** `security/UserPrincipal.java`

```java
package psu.edu.ch06.crud04.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import psu.edu.ch06.crud04.model.User;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }
}
```

**Key Points:**
- **Adapter Pattern**: Adapts our `User` class to Spring Security's `UserDetails` interface
- `getAuthorities()`: Returns the roles/permissions (in this case, "USER" role)
- `getPassword()`: Delegates to the `User` record's password field
- `getUsername()`: Delegates to the `User` record's username field

---

### Step 3: Configure Spring Security

The `SecurityConfig` class is the heart of our security configuration.

**File:** `config/SecurityConfig.java`

```java
package psu.edu.ch06.crud04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import psu.edu.ch06.crud04.model.User;
import psu.edu.ch06.crud04.security.UserPrincipal;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {})
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pwde) {
        UserDetails usera = new UserPrincipal(new User(1, "usera", pwde.encode("pwd123")));
        UserDetails userb = new UserPrincipal(new User(2, "userb", pwde.encode("pwd456")));
        
        return new InMemoryUserDetailsManager(usera, userb);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

#### 🔍 Detailed Breakdown:

**1. SecurityFilterChain Bean:**
- **CSRF Disabled**: `.csrf(csrf -> csrf.disable())`
  - CSRF (Cross-Site Request Forgery) protection is disabled because we're using stateless sessions
  - For REST APIs with token/basic auth, CSRF is not needed
  
- **Authentication Required**: `.anyRequest().authenticated()`
  - All endpoints require authentication
  - No anonymous access allowed
  
- **Basic Authentication**: `.httpBasic(basic -> {})`
  - Enables HTTP Basic Authentication
  - Credentials are sent in the Authorization header as Base64-encoded string
  
- **Stateless Sessions**: `.sessionCreationPolicy(SessionCreationPolicy.STATELESS)`
  - No session cookies created
  - Each request must include credentials
  - Ideal for REST APIs

**2. UserDetailsService Bean:**
- Creates two in-memory users:
  - **Username:** `usera` | **Password:** `pwd123`
  - **Username:** `userb` | **Password:** `pwd456`
- Passwords are encrypted using BCrypt before storage
- Uses `InMemoryUserDetailsManager` for simplicity (not for production!)

**3. PasswordEncoder Bean:**
- Uses `BCryptPasswordEncoder` for password hashing
- BCrypt is a strong, adaptive hashing function
- Automatically handles salt generation

---

## 🗄️ Database Configuration

Update your `application.properties` with your MySQL credentials:

```properties
spring.application.name=lab18

spring.datasource.url=jdbc:mysql://your-host:port/your-database?ssl-mode=REQUIRED
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.sql.init.mode=always
```

---

## 🚀 API Endpoints

The application provides a REST API for managing products:

| Method | Endpoint | Description | Authentication Required |
|--------|----------|-------------|------------------------|
| GET | `/api/v1/products` | Get all products | ✅ Yes |
| GET | `/api/v1/products/{id}` | Get product by ID | ✅ Yes |
| POST | `/api/v1/products` | Create new product | ✅ Yes |
| PUT | `/api/v1/products/{id}` | Update product | ✅ Yes |
| DELETE | `/api/v1/products/{id}` | Delete product | ✅ Yes |

---

## 🧪 Testing with Thunder Client / Postman

### Step 1: Start the Application

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

or run from Eclipse:
- Right-click on `Crud04Application.java`
- Select **Run As → Spring Boot App**

The application will start on `http://localhost:8080`

---

### Step 2: Test Without Authentication (Should Fail)

Try accessing an endpoint without credentials:

**Request:**
```
GET http://localhost:8080/api/v1/products
```

**Expected Response:**
```
Status: 401 Unauthorized
```

This confirms that authentication is required!

---

### Step 3: Test With Basic Authentication (Should Succeed)

#### Using Thunder Client (VS Code):

1. **Create a new request**
2. **Method:** GET
3. **URL:** `http://localhost:8080/api/v1/products`
4. **Go to the "Auth" tab**
5. **Select:** Basic Auth
6. **Enter credentials:**
   - Username: `usera`
   - Password: `pwd123`
7. **Send the request**

**Expected Response:**
```json
Status: 200 OK
[
  {
    "id": 1,
    "name": "Product A",
    "description": "Description A",
    "price": 19.99
  },
  ...
]
```

#### Using Postman:

1. **Create a new request**
2. **Method:** GET
3. **URL:** `http://localhost:8080/api/v1/products`
4. **Go to the "Authorization" tab**
5. **Type:** Select "Basic Auth"
6. **Enter credentials:**
   - Username: `usera`
   - Password: `pwd123`
7. **Send the request**

#### Manual Header Approach:

You can also manually add the Authorization header:

**Header:**
```
Authorization: Basic dXNlcmE6cHdkMTIz
```

The value is Base64-encoded `username:password` (`usera:pwd123`)

---

### Step 4: Test Other Endpoints

**Create a Product:**
```
POST http://localhost:8080/api/v1/products
Authorization: Basic Auth (usera / pwd123)
Content-Type: application/json

{
  "name": "New Product",
  "description": "Product Description",
  "price": 29.99
}
```

**Get a Specific Product:**
```
GET http://localhost:8080/api/v1/products/1
Authorization: Basic Auth (usera / pwd123)
```

**Update a Product:**
```
PUT http://localhost:8080/api/v1/products/1
Authorization: Basic Auth (usera / pwd123)
Content-Type: application/json

{
  "id": 1,
  "name": "Updated Product",
  "description": "Updated Description",
  "price": 39.99
}
```

**Delete a Product:**
```
DELETE http://localhost:8080/api/v1/products/1
Authorization: Basic Auth (usera / pwd123)
```

---

## 🔒 How Basic Authentication Works

### Flow Diagram:

```
Client                          Server
  |                               |
  |  1. Request without auth      |
  |------------------------------>|
  |                               |
  |  2. 401 Unauthorized          |
  |      WWW-Authenticate: Basic  |
  |<------------------------------|
  |                               |
  |  3. Request with credentials  |
  |      Authorization: Basic ... |
  |------------------------------>|
  |                               |
  |  4. Validate credentials      |
  |     - Decode Base64           |
  |     - Check username/password |
  |     - Verify with BCrypt      |
  |                               |
  |  5. 200 OK + Response Data    |
  |<------------------------------|
```

### Under the Hood:

1. **Client sends credentials:** `usera:pwd123`
2. **Base64 encoding:** `dXNlcmE6cHdkMTIz`
3. **Authorization header:** `Basic dXNlcmE6cHdkMTIz`
4. **Server decodes** the header
5. **Spring Security** validates credentials against `UserDetailsService`
6. **BCryptPasswordEncoder** verifies the hashed password
7. **Access granted** if credentials are valid

---

## 🎓 Key Concepts

### 1. **Stateless Authentication**
- No session cookies
- Each request must include credentials
- Server doesn't maintain session state
- Scalable for distributed systems

### 2. **Password Encryption**
- **Plain text passwords are never stored**
- BCrypt algorithm provides:
  - Adaptive hashing (configurable work factor)
  - Built-in salt generation
  - Resistant to rainbow table attacks

### 3. **In-Memory User Management**
- **Pros:** Simple, fast, no database needed
- **Cons:** Users lost on restart, not for production
- **Alternatives:** JDBC, LDAP, OAuth2, JWT

### 4. **Adapter Pattern**
- `UserPrincipal` adapts our `User` class to `UserDetails` interface
- Allows Spring Security to work with our custom User model
- Decouples our domain model from Spring Security

---

## 🔧 Common Issues & Solutions

### Issue 1: 401 Unauthorized with Correct Credentials

**Cause:** Password encoding mismatch

**Solution:** Ensure passwords are encoded with BCrypt:
```java
pwde.encode("pwd123")  // ✅ Correct
"pwd123"                // ❌ Wrong
```

---

### Issue 2: CORS Errors

**Cause:** Frontend running on different port

**Solution:** Add CORS configuration:
```java
http.cors(cors -> cors.configurationSource(request -> {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:3000"));
    config.setAllowedMethods(List.of("*"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);
    return config;
}));
```

---

### Issue 3: Database Connection Failed

**Cause:** Incorrect MySQL credentials or URL

**Solution:** 
1. Verify database is running
2. Check credentials in `application.properties`
3. Test connection with MySQL client

---

## 🚀 Next Steps & Enhancements

### 1. **Role-Based Authorization**
Add different roles (ADMIN, USER, GUEST):
```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/v1/products/**").hasRole("USER")
    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
    .anyRequest().authenticated()
)
```

### 2. **Database-Backed Users**
Replace in-memory users with JPA/JDBC:
```java
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
```

### 3. **JWT Authentication**
Implement token-based authentication:
- Generate JWT tokens on login
- Validate tokens on each request
- Store user info in token payload

### 4. **OAuth2 Integration**
Add social login (Google, GitHub):
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

---

## 📚 Additional Resources

- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Basic Authentication RFC 7617](https://tools.ietf.org/html/rfc7617)
- [BCrypt Algorithm](https://en.wikipedia.org/wiki/Bcrypt)
- [OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)

---

## ✅ Summary

This lab successfully demonstrates:
- ✅ Setting up Spring Security in a Spring Boot application
- ✅ Implementing HTTP Basic Authentication
- ✅ Creating custom User model and UserDetails adapter
- ✅ Configuring in-memory user management
- ✅ Using BCrypt for password encryption
- ✅ Securing REST API endpoints
- ✅ Testing authenticated requests with Thunder Client/Postman
- ✅ Understanding stateless session management

**Security Best Practices Applied:**
- 🔐 Passwords are encrypted (never plain text)
- 🔐 Stateless sessions (scalable & secure)
- 🔐 All endpoints protected by default
- 🔐 CSRF disabled appropriately for REST APIs

---

## 📝 Credentials for Testing

| Username | Password | Role |
|----------|----------|------|
| usera | pwd123 | USER |
| userb | pwd456 | USER |

---

**Lab Completed Successfully! 🎉**

*Date: November 17, 2025*
