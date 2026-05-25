# PSM 2 Demo 1 Presentation Slides

## Slide 1: System Introduction
**Title:** Al-Muneer Hall for Weddings and Events - Online Portal

**Brief Explanation:**
Al-Muneer Portal is a monolithic, responsive web application designed to digitalize and centralize venue management for Al-Muneer Hall in Ibb, Yemen. The system aims to resolve the currently disorganized manual booking process by providing:
- A centralized platform for visitors to view venue info, check an interactive availability calendar, and submit booking inquiries.
- A streamlined workflow for offline payment proof verification with state cascade logic.
- An administrative dashboard for content management, inquiry tracking, payment verification, feedback management, and reporting.
- Admin-triggered visitor notifications via WhatsApp deep links using configurable message templates.

---

## Slide 2: Use Case Diagram & Progress (100% Overall Completion)
*[Placeholder: Insert your `ucd.png` image here]*
`![Use Case Diagram](./ucd.png)`

**Development Progress by Use Case:**

*Visitor Module:*
- **UC001: View Venue Info** – ✅ Implemented
- **UC002: View Media Gallery** – ✅ Implemented
- **UC003: Check Availability** – ✅ Implemented
- **UC004: View Pricing Panel** – ✅ Implemented
- **UC005: Submit Booking Inquiry** – ✅ Implemented (Phase 2)
- **UC011: Submit Payment Proof** – ✅ Implemented (Phase 2)
- **UC012: Submit Feedback** – ✅ Implemented (Phase 2)

*Administrator Module:*
- **UC006: Manage Hall Info** – ✅ Implemented
- **UC007: Manage Media Gallery** – ✅ Implemented
- **UC008: Manage Pricing Panel** – ✅ Implemented
- **UC009: Manage Calendar & Inquiries** – ✅ Implemented (Phase 2)
- **UC010: View Traffic Analytics** – ✅ Implemented (Phase 2)
- **UC013: Manage Payment Status** – ✅ Implemented (Phase 2)
- **UC014: View/Generate Reports** – ✅ Implemented (Phase 2)
- **UC015: Manage Feedback** – ✅ Implemented (Phase 2)
- **UC016: Configure Notification Templates** – ✅ Implemented (Phase 2)

**Overall Completion:** 16 out of 16 Use Cases Completed = **100%**

---

## Slide 3: Development Demo
**Demo Flow (According to UCD):**
1. **Visitor Flow:**
   - *[Screenshot of the Homepage (UC001)]*
   - *[Screenshot of the Media Gallery (UC002)]*
   - *[Screenshot of the Availability Calendar (UC003)]*
   - *[Screenshot of the Pricing Page (UC004)]*
   - *[Screenshot of the Booking Inquiry Form (UC005)]*
   - *[Screenshot of the Inquiry Confirmation (UC005 — Inquiry ID display)]*
   - *[Screenshot of Payment Proof Upload (UC011)]*
   - *[Screenshot of the Feedback Form (UC012)]*
2. **Admin Flow:**
   - *[Screenshot of the Admin Login]*
   - *[Screenshot of the New Admin Dashboard (3-column quick links)]*
   - *[Screenshot of Inquiries List + Detail with WhatsApp deep-link (UC009)]*
   - *[Screenshot of Payment Proof Review + Verify/Reject + WA notify (UC013)]*
   - *[Screenshot of Reports page with inquiry/payment/feedback stats (UC014)]*
   - *[Screenshot of Analytics page with page visit data (UC010)]*
   - *[Screenshot of Notification Templates editor (UC016)]*

*(Note: Have the application running via `mvn spring-boot:run` during the live presentation.)*

---

## Slide 4: Logbook Update
**Details for your Logbook:**
- **Date:** (Current demo date)
- **Meeting Type:** Progress Demo 2 / Final Demo
- **Discussion Points:**
  - Completed all 16 planned use cases (100% system completion).
  - Demonstrated full visitor booking workflow: inquiry submission → payment proof upload → admin verification → WhatsApp notification.
  - Demonstrated admin-side: inquiry management, payment status cascade (Verified → Inquiry CONFIRMED + Slot BOOKED), feedback review, traffic analytics, and configurable WhatsApp notification templates.
  - State synchronization acceptance criteria (AC#3) implemented transactionally.
  - WhatsApp deep-link generation (UC016/AC#5) operational with placeholder resolution.
- **Action Items:** Final testing, deployment preparation.

