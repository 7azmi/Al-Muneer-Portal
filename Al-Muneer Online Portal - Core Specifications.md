# Al-Muneer Online Portal - Core Specifications

**Project Domain:** Event Venue Management System (Web-based) **Client:** Al-Muneer Hall for Weddings and Events (Ibb, Yemen) **Methodology:** Waterfall SDLC

_Note: The project's end-user target language is Arabic (due to the local Yemeni context), but for the sake of development, testing, and presentation, the interface will temporarily be in English._

## 1. Problem & Solution Overview

**The Problem:** Currently, bookings and inquiries are handled manually via phone calls and WhatsApp. This causes significant time drain on the owner, inconsistencies, limited availability for customer inquiries, and lack of a centralized visual platform (which is especially problematic for female stakeholders who prefer remote venue assessment due to local cultural norms). Tracking payments and feedback is disorganized.

**The Solution:** A monolithic, responsive web application that centralizes venue information, an interactive availability calendar, pricing, and booking inquiries. It includes a specific workflow for visitors to upload offline payment proofs (screenshots) to confirm bookings, alongside an administrative dashboard for the owner to manage content, track inquiries, verify payments, and gather feedback.

## 2. Scope & Constraints

**In Scope:**

- Responsive web portal (mobile-friendly browser access).
    
- Two User Roles: Visitor and Administrator (Owner).
    
- Core Features: Media gallery, interactive availability calendar, dynamic pricing, inquiry submission, payment proof upload, feedback form, basic admin reporting.
    
- Admin-triggered visitor notifications via WhatsApp deep links (`wa.me/{number}?text={template}`).
    

**Out of Scope (Limitations):**

- No native mobile application.
    
- No direct online payment processing/gateways (strictly offline transfer proofs).
    
- No integration with external calendar systems (e.g., Google Calendar).
    
- No integrated live chat (relies on WhatsApp/Hotline links).
    
- No complex CRM or advanced event management features.
    
- No email notifications or third-party SMS gateways.
    

**Project Constraints:**

- **Solo Developer & Budget:** Must favor open-source, cost-effective solutions.
    
- **Local Infrastructure:** Must be optimized for potentially slower internet connections in Yemen (avoid overly heavy client-side frameworks; optimize images).
    
- **Communication Preferences:** WhatsApp deep links are the sole notification channel. No email integration is required or in scope.
    

## 3. Technology Stack & Architecture

- **Architecture Pattern:** Model-View-Controller (MVC) Monolith
    
- **Backend Framework:** Spring Boot (Java)
    
- **Frontend Interface:** HTML5, CSS3, JavaScript, Thymeleaf (Server-side rendering)
    
- **Database:** PostgreSQL or MySQL (Relational) via Spring Data JPA
    
- **Security:** Spring Security (Hashed passwords, HTTPS)
    
- **External Interfaces:** HTTP/REST APIs (Internal). WhatsApp deep link generation (`wa.me` format) for admin-initiated visitor notifications — no third-party gateway required.
    
- **Deployment:** Cloud VPS (Linux)
    

**Backend Package Structure (`com.almuneer.portal`):**

- `.config`: Security (WebSecurityConfig), MVC configurations, Notification configs.
    
- `.controller`: REST APIs handling HTTP requests and returning views/DTOs.
    
- `.service` & `.service.impl`: Core business logic, orchestrating repositories and utilities.
    
- `.repository`: Spring Data JPA interfaces for database CRUD operations.
    
- `.model`: JPA Domain Entities (POJOs mapped to DB).
    
- `.dto`: Data Transfer Objects for API request/response payloads.
    
- `.util`: Helpers (FileUploadUtil, DateUtil, ReportGeneratorUtil).
    
- `.security`: JWT-based stateless authentication for the Admin Dashboard, UserDetailsService implementation.
    

## 4. Detailed System Use Cases (SRS)

### Visitor Module

- **UC001: View Venue Info:** Retrieves and displays description, services, capacity, and location.
    
- **UC002: View Media Gallery:** Grid display of image thumbnails and embedded YouTube video tiles (no native video uploads). Clicking an image opens a full-size lightbox; clicking a video tile plays it inline via YouTube embed.
    
- **UC003: Check Availability:** Interactive calendar displaying "Available", "Booked", or "Pending" states.
    
- **UC004: View Pricing Panel:** Displays pricing tiers and packages.
    
- **UC005: Submit Booking Inquiry:** Form capture (Name, WhatsApp Number, Event Date, etc.) -> Validates -> Saves to DB -> Sets linked `AvailabilitySlot` to `Pending` -> Displays Inquiry ID to visitor on confirmation screen -> Triggers Admin Notification deep link.
    
- **UC011: Submit Payment Proof:** Visitor enters their Inquiry ID (provided on the UC005 confirmation screen) and uploads a JPG/PNG receipt screenshot (Max 5MB). The proof is linked to the inquiry record. Multiple uploads are allowed (e.g., if a previous proof was rejected). Triggers Admin Notification deep link.
    
- **UC012: Submit Feedback:** Form capture -> Saves to DB.
    

### Administrator (Owner) Module

- **UC006: Manage Hall Info:** CRUD operations for venue descriptions, contact details, and FAQs.
    
- **UC007: Manage Media Gallery:** Upload new image files (JPG/PNG, Max 5MB) or add YouTube video URLs. Delete existing gallery items.
    
- **UC008: Manage Pricing Panel:** Add, edit, or remove pricing tiers/packages.
    
- **UC009: Manage Calendar & Inquiries:** Manually override calendar dates and read submitted inquiries.
    
- **UC010: View Traffic Analytics:** View basic logged data (e.g., total page visits).
    
- **UC013: Manage Payment Status:** View uploaded proof -> Verify offline -> Update status -> Add internal notes -> Triggers Visitor Notification.
    
- **UC014: View/Generate Reports:** Generate basic predefined summaries (Inquiry volume, Bookings, Feedback).
    
- **UC015: Manage Feedback:** Read visitor feedback, mark as "Reviewed", and add internal notes.
    
- **UC016: Configure Notification Templates:** Admin edits pre-filled WhatsApp message templates for visitor-facing alerts (e.g., "Inquiry Received", "Payment Verified", "Payment Rejected"). Triggering a notification action generates a `wa.me` deep link with the visitor's WhatsApp number and the configured template text populated.
    

## 5. Performance & Security Attributes (NFRs)

- **Response Times:** Static pages 3-5s max; Calendar updates 2-3s; Form processing 5-7s.
    
- **Scalability:** Support up to 10,000 monthly visitors (10-20 concurrent peak sessions).
    
- **Reliability:** 99% uptime target. Automated daily DB and file backups.
    
- **Security:** Enforced HTTPS (SSL/TLS), Hashed admin passwords, Strict file validation (JPG/PNG, 5MB).
    

## 6. System Design & Data Dictionary

### 6.1 Entity Lifecycle States (Business Logic)

**BookingInquiry States:**

1. `New`: Inquiry submitted by visitor, awaiting admin review.
    
2. `Viewed`: Admin has opened/viewed the inquiry details.
    
3. `Contacted`: Admin has contacted the visitor.
    
4. `Payment_Pending`: Tentative agreement made, awaiting payment proof.
    
5. `Confirmed`: Payment proof verified; booking is locked.
    
6. `Completed`: Event date has passed.
    
7. `Cancelled_By_Admin` / `Cancelled_By_Visitor`: Terminated at any stage.
    

**PaymentProof States:**

1. `Pending_Verification`: Uploaded, awaiting admin offline verification.
    
2. `Verified`: Admin confirmed payment validity (Triggers Inquiry -> `Confirmed` and Slot -> `Booked`).
    
3. `Rejected`: Admin found proof invalid/insufficient. Visitor may re-upload a new proof.
    

**AvailabilitySlot States:**

1. `Available`: No active inquiry exists for this date; open for new inquiries.
    
2. `Pending`: An active `BookingInquiry` has been submitted for this date. Automatically set on inquiry submission; blocks further inquiries for the same date until resolved.
    
3. `Booked`: Payment has been verified (`PaymentProof` -> `Verified`). Date is fully locked.
    

**State Synchronization Rules:**

- On `BookingInquiry` submitted for a date → linked `AvailabilitySlot.status` = `Pending`
    
- On `PaymentProof` → `Verified` → `BookingInquiry.status` = `Confirmed` → `AvailabilitySlot.status` = `Booked`
    
- On `BookingInquiry` → `Cancelled_By_Admin` or `Cancelled_By_Visitor` → `AvailabilitySlot.status` = `Available`
    

### 6.2 Conceptual Database Schema (ERD)

```
@startuml
entity "AdminUser" as admin {
  * userId : BIGINT <<PK>>
  --
  username : VARCHAR(50)
  passwordHash : VARCHAR(255)
  role : VARCHAR(20)
}

entity "VenueInfo" as venue {
  * infoId : INT <<PK>>
  --
  description : TEXT
  services : TEXT
  capacity : INT
  location : VARCHAR
  contactInfo : VARCHAR
  faqJson : TEXT
}

entity "MediaItem" as media {
  * mediaId : BIGINT <<PK>>
  --
  mediaType : VARCHAR(12)
  fileName : VARCHAR
  filePath : VARCHAR
  youtubeUrl : VARCHAR
  caption : VARCHAR
  uploadDate : TIMESTAMP
}

entity "AvailabilitySlot" as slot {
  * slotId : BIGINT <<PK>>
  --
  slotDate : DATE
  status : VARCHAR(20)
  notes : TEXT
  updatedAt : TIMESTAMP
}

entity "PricingTier" as price {
  * pricingId : BIGINT <<PK>>
  --
  tierName : VARCHAR
  basePrice : DECIMAL
  description : TEXT
  isActive : BOOLEAN
}

entity "BookingInquiry" as inquiry {
  * inquiryId : BIGINT <<PK>>
  --
  visitorName : VARCHAR
  visitorWhatsApp : VARCHAR
  slotId : BIGINT <<FK>>
  pricingId : BIGINT <<FK, nullable>>
  numGuests : INT
  eventType : VARCHAR
  message : TEXT
  submissionDate : TIMESTAMP
  status : VARCHAR(50)
  adminNotes : TEXT
}

entity "PaymentProof" as proof {
  * proofId : BIGINT <<PK>>
  --
  inquiryId : BIGINT <<FK>>
  fileName : VARCHAR
  filePath : VARCHAR
  uploadTimestamp : TIMESTAMP
  verificationStatus : VARCHAR(25)
  adminNotes : TEXT
}

entity "Feedback" as feedback {
  * feedbackId : BIGINT <<PK>>
  --
  visitorName : VARCHAR
  visitorWhatsApp : VARCHAR
  feedbackText : TEXT
  rating : INT
  submissionDate : TIMESTAMP
  isReviewed : BOOLEAN
  adminNotes : TEXT
}

admin ||--|| venue : manages
admin ||--o{ media : manages
admin ||--o{ slot : manages
admin ||--o{ price : manages
admin ||--o{ inquiry : manages
admin ||--o{ feedback : manages
inquiry }o--|| slot : reserves
inquiry }o--o| price : uses
inquiry ||--o{ proof : has
@enduml
```

## 7. UI Wireframe Summaries (Low-Fidelity)

- **Homepage:** Logo/Nav bar at top. Main hero section with primary Call-to-Action (CTA). Sections below detailing venue introductory info and 3 key feature highlights.
    
- **Gallery Page:** Filter tabs (All, Weddings, Hall Setup, Decorations, Exterior) over a mixed grid of image thumbnails and YouTube video tiles. Clicking an image opens a lightbox; clicking a video tile expands an inline YouTube embed player.
    
- **Availability Calendar:** Interactive monthly calendar view. Color-coded dates (e.g., Green = Available, Yellow = Pending, Red = Booked). "Make an Inquiry" button below.
    
- **Pricing Page:** 3-column pricing tier cards (Package 1, 2, 3) with feature checklists and CTA buttons. Below, a section for individual a-la-carte service pricing.
    
- **Inquiry Form:** Standard web form (Name, WhatsApp Number, Date picker, Event type dropdown, Pricing Package dropdown (optional), special requests text area) with a side panel displaying direct contact info (Hotline/WhatsApp). On successful submission, a confirmation screen displays the visitor's Inquiry ID with instructions for submitting a payment proof.
    
- **Admin Dashboard:** Secure login leading to a metrics overview (Bookings this month, Pending proofs, Recent inquiries). Sidebar navigation for CRUD operations on all entities.
    

## 8. Quality Assurance & Acceptance Criteria (Testing Reference)

_Note: This section summarizes the STD for practical development checks. Ensure unit testing during implementation and black-box functional testing post-integration._

**Core Acceptance Criteria for Development:**

1. **Form Validation (UC005, UC012):** The system MUST reject submissions with empty required fields or invalid data formats (e.g., malformed WhatsApp numbers) and display clear error messages without crashing or losing valid input data.
    
2. **File Upload Security (UC007, UC011):** The system MUST restrict Payment Proof uploads (UC011) to JPG/PNG image types with a strict 5MB limit. Gallery image uploads (UC007) are similarly restricted to JPG/PNG; videos are added via YouTube URL only (no file upload). Invalid uploads must trigger an immediate UI error.
    
3. **State Management Synchronization (UC009, UC013):** When an Admin updates a `PaymentProof` status to "Verified", the associated `BookingInquiry` status MUST automatically update to "Confirmed", and the `AvailabilitySlot` for that date MUST reflect as "Booked".
    
4. **Authentication (UC006-UC016):** The Admin Dashboard MUST be completely inaccessible to unauthenticated users. Unauthorized URL access attempts must redirect to the login page.
    
5. **Notification Triggers (UC016):** Admin notification actions (e.g., "Payment Verified", "Payment Rejected") MUST generate a correctly formatted `wa.me` deep link using the visitor's WhatsApp number and the configured message template, presented immediately and without blocking the UI response for the visitor.