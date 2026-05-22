# Al-Muneer Portal 🏛️

**Al-Muneer Hall for Weddings and Events — Online Portal**

Al-Muneer Portal is a modern, responsive web application designed for the Al-Muneer Hall in Ibb, Yemen. It streamlines venue management, booking inquiries, and administrative operations.

---

## 🚀 Key Features

### 🌍 Visitor Module
- **Venue Showcase:** Detailed information about venue services, capacity, and location.
- **Media Gallery:** Interactive grid with image lightboxes and embedded YouTube videos.
- **Availability Calendar:** Real-time calendar displaying "Available", "Pending", or "Booked" status.
- **Pricing & Packages:** Dynamic display of event packages and à-la-carte services.
- **Booking Inquiry Landing Page:** Unified page to either submit a new inquiry or look up an existing one by reference code.
- **9-Digit Reference Codes:** Every inquiry is assigned a random 9-digit visitor-facing reference code stored in a 150-day cookie for seamless retrieval.
- **Visitor Self-Cancellation:** Visitors can cancel their own inquiry (if no payment proof is attached) directly from the confirmation page.
- **Payment Proof Upload:** Security-verified upload system for offline payment receipts.
- **Feedback System:** Visitors can share their experience via a dedicated feedback form.

### 🛠️ Administrator Module
- **Dashboard Overview:** Real-time metrics for bookings, pending proofs, and inquiries.
- **Content Management:** Full CRUD for venue info, gallery items, and pricing tiers.
- **Inquiry Management:** Rich filter bar with per-status counts; active inquiries shown by default; completed/cancelled hidden. Client-side search. Reference code displayed per row.
- **Payment Verification:** Review uploaded payment receipts with image display. Cascades status to inquiry and slot on verification.
- **Dynamic WhatsApp Notifications:** Select any notification template from a dropdown on the inquiry/payment detail page; message preview with placeholder resolution; deep-link generated client-side.
- **Notification Template CRUD:** Full create, edit, and delete for WhatsApp message templates.
- **Feedback Management:** Review and respond to visitor feedback.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 3.4.4 |
| Security | Spring Security (JWT-based, BCrypt) |
| Database | PostgreSQL 16 |
| Frontend | HTML5, CSS3, Vanilla JS, Thymeleaf |
| Build | Maven |
| Utilities | Lombok, Spring Data JPA, jjwt |

---

## 📋 Prerequisites

- **Java 21 JDK** or higher
- **Maven 3.x**
- **PostgreSQL 15+**

---

## ⚙️ Getting Started

### 1. Database Setup
```sql
CREATE DATABASE almuneer_portal;
CREATE USER almuneer WITH PASSWORD 'almuneer_dev';
GRANT ALL PRIVILEGES ON DATABASE almuneer_portal TO almuneer;
```

### 2. Configure Application
Edit `src/main/resources/application.properties`:
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/almuneer_portal
spring.datasource.username=almuneer
spring.datasource.password=almuneer_dev

# Phone / Regional — change if deploying outside Yemen
app.country.code=+967
```

### 3. Run the Application
```bash
mvn spring-boot:run
```
The portal will be accessible at `http://localhost:8080`.

---

## 🔐 Administrator Access

Default admin account created on first run (`DataSeeder`):
- **URL:** `http://localhost:8080/admin/login`
- **Username:** `admin`
- **Password:** `admin123`

> [!IMPORTANT]
> Change the default password immediately after the first login in a production environment.

---

## ⚙️ Key Configuration Properties

| Property | Default | Description |
|---|---|---|
| `app.country.code` | `+967` | Country dialling code prepended to all WhatsApp numbers. Change for non-Yemen deployments. |
| `app.upload.gallery-dir` | `uploads/gallery` | Relative path for gallery image uploads. |
| `app.upload.proofs-dir` | `uploads/proofs` | Relative path for payment proof uploads. |
| `app.jwt.secret` | *(see file)* | JWT signing secret — **must** be changed in production. |

---

## 📂 Project Structure

```text
src/main/java/com/almuneer/portal/
├── config/         # Security, MVC config, GlobalModelAdvice, DataSeeder
├── controller/     # Web controllers (Visitor & Admin)
├── model/          # JPA Entities (BookingInquiry has referenceCode field)
├── repository/     # Spring Data JPA repositories
├── security/       # JWT and Auth logic
├── service/        # Business logic interfaces + implementations
└── util/           # FileUploadUtil, DeepLinkBuilderUtil

src/main/resources/
├── static/         # CSS, JS, and Image assets
└── templates/
    ├── admin/      # Admin panel Thymeleaf templates
    └── visitor/    # Visitor-facing Thymeleaf templates
```

---

## 🔄 Changelog

### v0.3 — 2026-05-22
- **Fix:** Payment proof images now display correctly — `FileUploadUtil` returns the UUID filename (not the full path), which is what the `/uploads/proofs/**` resource handler serves.
- **Fix:** Admin `/inquiries` 500 error — Thymeleaf `counts` map re-keyed from enum to String so `${counts[s.name()]}` resolves correctly.
- **Feature:** Configurable country code (`app.country.code=+967`) injected via `GlobalModelAdvice` into all templates as `${countryCode}`; backend normalisation uses the same value.
- **Feature:** Admin inquiries panel rebuilt with status filter chips (per-status counts), client-side search, and "active only" default view.
- **Feature:** Random 9-digit `referenceCode` on `BookingInquiry` — visitor-facing; stored in a 150-day `HttpOnly` cookie (`inq_ref`).
- **Feature:** `/inquiry` landing page unifies new inquiry form and existing inquiry lookup (by reference code).
- **Feature:** Visitor self-cancellation — only allowed when no payment proof is attached; frees the slot.
- **Feature:** Dynamic WhatsApp template selector on admin inquiry/payment detail pages — resolves placeholders client-side before building `wa.me` deep-link.
- **Feature:** Full CRUD for notification templates (create, edit, delete).
- **Feature:** All visitor phone inputs show a static country code badge; backend `normaliseWhatsApp()` strips non-digits and prepends the configured code.

### v0.2 — 2026-05-19
- Completed all 16 use cases (Phase 2 implementation).
- Added `PageVisitInterceptor` for daily traffic analytics.
- Built `DeepLinkBuilderUtil` for WhatsApp messaging via database-backed templates.
- Created full admin modules: inquiry management, payment processing, analytics, reporting, notifications.
- Implemented atomic status cascade: `PaymentProof` verification → `BookingInquiry` CONFIRMED → `AvailabilitySlot` BOOKED.

### v0.1 — 2026-04-20
- Initial implementation: venue info, gallery, availability calendar, pricing.
- Admin authentication (JWT + BCrypt).
- Spring Security configuration, DataSeeder for default admin and seed data.

---

## 📄 License
Developed for Al-Muneer Hall. All rights reserved.
