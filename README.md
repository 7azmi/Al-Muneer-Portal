# Al-Muneer Portal 🏛️

**Al-Muneer Hall for Weddings and Events — Online Portal**

Al-Muneer Portal is a modern, responsive web application designed for the Al-Muneer Hall in Ibb, Yemen. It streamlines venue management, booking inquiries, and administrative operations.

---

## 🚀 Key Features

### 🌍 Visitor Module
- **Single-Page Home:** One scrollable landing page (hero → venue → gallery → booking calendar → pricing) with anchor navigation; legacy routes (`/gallery`, `/calendar`, `/pricing`, `/venue`) redirect to the matching section.
- **Venue Showcase:** Services, capacity, contact, location label, and an embedded **Google Maps** iframe. Map share + embed URLs are editable in admin (with `application.properties` defaults as fallback).
- **Media Gallery:** Packed masonry layout (variable-height images/videos), category filters from admin-defined labels, and a full-screen lightbox.
- **Interactive Booking Calendar:** Future dates default to **Available**; past dates are dimmed. Tap an available day to reveal a single **Submit inquiry** action; inquiry opens with the date pre-filled (`/inquiry?date=YYYY-MM-DD`).
- **Pricing Packages:** Each package has a **Book [name]** button that opens the inquiry form with that tier pre-selected (`/inquiry?pricingId=…`); if a date was chosen on the home calendar, both parameters are passed.
- **Booking Inquiry Landing Page:** Unified page to submit a new inquiry or look up an existing one by reference code; shows context banner when date/package arrived from the home page.
- **9-Digit Reference Codes:** Every inquiry is assigned a random 9-digit visitor-facing reference code stored in a 150-day cookie for seamless retrieval.
- **Visitor Self-Cancellation:** Visitors can cancel their own inquiry (if no payment proof is attached) directly from the confirmation page.
- **Payment Proof Upload:** Security-verified upload system for offline payment receipts.
- **Feedback System:** Publicly accessible feedback form at `/feedback` with optional name and WhatsApp fields. Rating and message required. Feedback form is suggested to visitors post-inquiry completion via a link on the inquiry confirmation page. Responses stored for admin review.

### 🛠️ Administrator Module
- **Dashboard Overview:** Daily workload at a glance — new/active inquiries, pending payment proofs, unreviewed feedback, visits today/last 7 days, average rating, recent inquiries, and top pages.
- **Analytics Dashboard:** Interactive **Chart.js visualizations** — bar chart of top pages, line chart of daily traffic trends over last 30 days. Includes an **AI Traffic Funnel Advisor** that analyzes page visit counts and booking funnel drop-off.
- **Reports with Visualizations:** Comprehensive reports with **pie charts** (inquiry status, payment status) and **bar charts** (feedback rating distribution). Date range filtering to analyze trends by period (`?fromDate=` and `?toDate=`). Includes an **AI Business Report Advisor** that computes conversion and cancellation rates and surfaces urgent action bullets.
- **Feedback AI Analysis:** The feedback management list includes an **AI Feedback Advisor** that separates low-rating complaints from positive highlights and tells the owner what to address first.
- **Non-blocking AI panels:** All three AI advisors load asynchronously via `fetch()` after the page renders — page load is never delayed by Gemini. Each panel shows a spinner while loading and degrades gracefully on API error.
- **Content Management:** Full CRUD for venue info (including **Google Maps share + embed URLs**), gallery items, **gallery labels** (filter categories), and pricing tiers.
- **Inquiry Management:** Rich filter bar with per-status counts; active inquiries shown by default; completed/cancelled hidden. Client-side search. Reference code displayed per row.
- **Payment Verification:** Review uploaded payment receipts with image display. From an inquiry, **View Payment Proofs** opens proofs filtered by that inquiry (`/admin/payments?inquiryId=…`). Cascades status to inquiry and slot on verification.
- **Dynamic WhatsApp Notifications:** Select any notification template from a dropdown on the inquiry/payment detail page; message preview with placeholder resolution; deep-link generated client-side.
- **Notification Template CRUD:** Full create, edit, and delete for WhatsApp message templates.
- **Feedback Management:** List with snippet preview; **View** opens full message on a detail page; mark as reviewed from list or detail.
- **Calendar Management:** Slot updates refresh the grid in place (no full-page reload or jump back to the current month).

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 3.4.4 |
| Security | Spring Security (JWT-based, BCrypt) |
| Database | PostgreSQL 16 |
| Frontend | HTML5, CSS3, Vanilla JS, Thymeleaf, Chart.js |
| AI | Google GenAI Java SDK (`google-genai` 1.56.0) — Gemini 2.0 Flash Lite |
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
| `app.maps.share-url` | Google share link | Default “Open in Google Maps” link (overridden by **Admin → Venue Info** when set) |
| `app.maps.embed-url` | Maps embed URL | Default map iframe `src` (overridden by **Admin → Venue Info** when set) |
| `app.gemini.api-key` | *(required)* | Gemini API key from [aistudio.google.com](https://aistudio.google.com/). Without it, AI panels show a graceful fallback message. |

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
│   └── GeminiService.java   # Google GenAI SDK wrapper — async AI advisor endpoints
└── util/           # FileUploadUtil, DeepLinkBuilderUtil, ReportGeneratorUtil

src/main/resources/
├── static/
│   ├── css/main.css
│   └── js/
│       ├── main.js              # Nav, scroll-spy, admin calendar (in-place slot refresh), lightbox helpers
│       ├── visitor-calendar.js  # Interactive visitor availability calendar
│       ├── booking-flow.js      # Syncs inquiry URLs with selected date/package
│       ├── masonry-layout.js    # Packed gallery layout
│       └── gallery.js           # Gallery filters + lightbox
└── templates/
    ├── admin/      # Admin panel Thymeleaf templates
    └── visitor/    # Visitor-facing Thymeleaf templates (home.html is the main landing page)
```

### Visitor booking flow (home page)

```text
1. Scroll to #availability (or use nav “Book”)
2. Tap an available calendar day
3. Click “Submit inquiry →”  →  /inquiry?date=…
   — or from Pricing: “Book [Package]”  →  /inquiry?pricingId=…  (adds date if already selected)
4. Complete the inquiry form (date/package pre-filled when provided)
```

---

## 🔄 Changelog

### v0.7 — 2026-06-04
- **Feature:** Gemini AI integration using the official **Google GenAI Java SDK** (`google-genai` 1.56.0, model `gemini-3.1-flash-lite`).
- **Feature:** `GeminiService` — Spring `@Service` wrapping the SDK; returns a graceful fallback on any error so pages never crash.
- **Feature:** **AI Business Report Advisor** (`GET /admin/reports/ai-insight`) — derives booking conversion rate and cancellation rate, then prompts Gemini for 3 number-backed action bullets.
- **Feature:** **AI Feedback Advisor** (`GET /admin/feedback/ai-insight`) — separates 1-2★ complaints from 4-5★ positives; asks Gemini what the owner should address first this week.
- **Feature:** **AI Traffic Funnel Advisor** (`GET /admin/analytics/ai-insight`) — maps Home → Gallery → Pricing → Inquiry visit counts to surface funnel drop-off and suggest one concrete conversion improvement.
- **Architecture:** All AI panels load **asynchronously** via dedicated `GET /ai-insight` endpoints; main pages render instantly, the browser fetches the insight after DOM load. Bullet responses are formatted as styled HTML lists client-side.
- **Config:** `app.gemini.api-key` added to `application.properties`.

### v0.6 — 2026-06-04
- **Feature:** Analytics dashboard with interactive **Chart.js visualizations** — bar chart for top pages, line chart for daily traffic trends.
- **Feature:** Reports page with **visual charts**: inquiry status breakdown (pie chart), payment status (pie chart), feedback ratings distribution (bar chart).
- **Feature:** Date range filtering on reports page (`?fromDate=YYYY-MM-DD&toDate=YYYY-MM-DD`) to analyze historical trends.
- **Fix:** Feedback `visitor_name` column now nullable; feedback form optional name field now works without errors.
- **UX:** Feedback form moved to post-inquiry flow — no longer suggested on home page, only linked after inquiry completion.
- **UX:** Inquiry page (`/inquiry`) now fully responsive across mobile breakpoints (768px, 640px, 480px); two-column grids stack to single column on mobile.

### v0.5 — 2026-05-24
- **Feature:** Admin dashboard stats (`AdminDashboardService`) — inquiries, pending proofs, unreviewed feedback, traffic, recent activity.
- **Feature:** Venue admin stores **maps share + embed URLs** in DB; home page and preview use saved values; properties remain fallback.
- **Feature:** Admin feedback **View** detail page (`/admin/feedback/{id}`) for full messages; visitor name/WhatsApp optional on submit.
- **UX:** Venue section layout (info row + services + map column); admin venue live preview synced with landing page.
- **UX:** Admin calendar slot updates without full page reload; inquiry **View Payment Proofs** filtered by `inquiryId`.
- **Fix:** `/admin` redirect; global `error.html`; gallery label delete confirm (no Thymeleaf `th:onsubmit` breakage).

### v0.4 — 2026-05-24
- **Feature:** Single-page scroll home with anchor nav and scroll-spy; consolidated visitor journey on `/`.
- **Feature:** Google Maps embed + configurable `app.maps.share-url` / `app.maps.embed-url`.
- **Feature:** Masonry gallery (`masonry-layout.js`, `gallery.js`) with label-based filters; admin **Gallery Labels** CRUD in sidebar (`/admin/gallery/labels`).
- **Feature:** Interactive visitor calendar (`visitor-calendar.js`) — large month nav, future dates default Available, past dates dimmed, click-to-select with one **Submit inquiry** CTA (`booking-flow.js`).
- **Feature:** Pricing **Book [package]** buttons pre-select tier on inquiry; `InquiryController` accepts `?date=` and `?pricingId=`.
- **UX:** Removed redundant booking buttons (duplicate step path, extra CTAs, separate `#book` section); nav simplified to **Book** → `#availability`.

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
