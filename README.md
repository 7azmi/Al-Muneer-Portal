# Al-Muneer Portal 🏛️

**Al-Muneer Hall for Weddings and Events — Online Portal**

Al-Muneer Portal is a modern, responsive web application designed for the Al-Muneer Hall in Ibb, Yemen. It streamlines the process of venue management, booking inquiries, and administrative operations.

---

## 🚀 Key Features

### 🌍 Visitor Module
- **Venue Showcase:** Detailed information about venue services, capacity, and location.
- **Media Gallery:** Interactive grid with image lightboxes and embedded YouTube videos.
- **Availability Calendar:** Real-time calendar displaying "Available", "Pending", or "Booked" status.
- **Pricing & Packages:** Dynamic display of event packages and a-la-carte services.
- **Booking Inquiry:** streamlined form for submitting booking requests.
- **Payment Proof Upload:** Security-verified upload system for offline payment receipts (screenshots).
- **Feedback System:** Visitors can share their experience via a dedicated feedback form.

### 🛠️ Administrator Module
- **Dashboard Overview:** Real-time metrics for bookings, pending proofs, and inquiries.
- **Content Management:** Full CRUD operations for venue info, gallery items, and pricing tiers.
- **Inquiry Management:** Track and update inquiry statuses (New, Viewed, Contacted, Confirmed, etc.).
- **Payment Verification:** Review and verify uploaded payment receipts.
- **Notification Templates:** Configure WhatsApp-ready message templates for visitor updates.
- **Feedback Management:** Review and respond to visitor feedback.

---

## 🛠️ Technology Stack

- **Backend:** Java 21, Spring Boot 3.4.4
- **Security:** Spring Security (JWT-based, BCrypt password hashing)
- **Database:** PostgreSQL
- **Frontend:** HTML5, CSS3, JavaScript, Thymeleaf (Server-side rendering)
- **Build Tool:** Maven
- **Utilities:** Lombok, Spring Data JPA, JWT (jjwt)

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:
- **Java 21 JDK** or higher
- **Maven 3.x**
- **PostgreSQL 15+**

---

## ⚙️ Getting Started

### 1. Database Setup
Create a PostgreSQL database named `almuneer_portal`:
```sql
CREATE DATABASE almuneer_portal;
CREATE USER almuneer WITH PASSWORD 'almuneer_dev';
GRANT ALL PRIVILEGES ON DATABASE almuneer_portal TO almuneer;
```

### 2. Configure Application
The database credentials and other settings are located in `src/main/resources/application.properties`. You can modify them as needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/almuneer_portal
spring.datasource.username=almuneer
spring.datasource.password=almuneer_dev
```

### 3. Run the Application
You can run the application using Maven:
```bash
mvn spring-boot:run
```
The portal will be accessible at `http://localhost:8080`.

---

## 🔐 Administrator Access

The system comes with a default administrator account created on the first run (via `DataSeeder`):
- **URL:** `http://localhost:8080/admin/login`
- **Username:** `admin`
- **Password:** `admin123`

> [!IMPORTANT]
> Change the default password immediately after the first login in a production environment.

---

## 📂 Project Structure

```text
src/main/java/com/almuneer/portal/
├── config/         # Security and MVC configurations
├── controller/     # Web controllers (Visitor & Admin)
├── dto/            # Data Transfer Objects
├── model/          # JPA Entities
├── repository/     # Spring Data JPA repositories
├── security/       # JWT and Auth logic
├── service/        # Business logic interfaces
└── util/           # Helper classes (File upload, etc.)

src/main/resources/
├── static/         # CSS, JS, and Image assets
└── templates/      # Thymeleaf HTML templates
```

---

## 📄 License
This project is developed for Al-Muneer Hall. All rights reserved.
