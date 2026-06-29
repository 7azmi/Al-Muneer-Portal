# UNIVERSITI TEKNOLOGI MALAYSIA

## FACULTY OF COMPUTING - UTM Johor Bahru

**SECJ 3032:** Software Engineering PSM1

**Semester:** 01, 2024/2025

# Software Test Documentation

**Project:** Al-Muneer Online Portal

**Version:** 1.3

**Prepared by:** Ahmed Ghaleb

**Appendix:** APPENDIX D - STD

## Revision Page

### a. Overview

This document is Version 1.3 of the Software Test Documentation (STD) for the Al-Muneer Online Portal. It defines the testing strategy, scope, and test case design for verifying that the implemented system matches the Software Requirements Specification (SRS) Version 1.2 and Software Design Description (SDD) Version 1.2.

Version 1.2 syncs all test cases with the **actual implemented system**. Version 1.3 adds a **test execution summary** (Section 3) and cross-reference to the User Acceptance Test (UAT) document (Appendix E), which records manual UAT performed locally on 29/06/2026.

### b. Target Audience

- **The project developer** (Ahmed Hani Ahmed Ghaleb), as a guide for executing manual tests.
- **The project supervisor** (Dr. Muhammad Luqman bin Mohd Shafie), for review of testing coverage.
- **Examiners**, for evaluation of the planned testing approach.

### c. Project Team Members

Single developer responsible for all modules:

- **Team Member:** Ahmed Hani Ahmed Ghaleb
- **Assigned Module(s) for Testing:** All modules of the Al-Muneer Online Portal.

### d. Version Control History

| Version | Primary Author(s) | Description of Version | Date Completed |
|---------|-------------------|------------------------|----------------|
| 1.0 | Ahmed Hani Ahmed Ghaleb | Initial draft of STD document, including test case design. | 30/05/2025 |
| 1.1 | Ahmed Hani Ahmed Ghaleb | Use case 6 removed. | 30/05/2025 |
| 1.2 | Ahmed Hani Ahmed Ghaleb | Synced with implementation (SRS/SDD v1.2): single-page home, reference-code booking flow, FAQ section, corrected field names/enums, removed non-existent features, added AI advisor and inquiry lifecycle tests. | 21/06/2026 |
| 1.3 | Ahmed Hani Ahmed Ghaleb | Added Section 3 test execution summary; linked UAT results (35 pass, 1 fail, 2 blocked of 38 cases). | 30/06/2026 |

### e. Sync Change Log (v1.2)

**Removed** (features not implemented or not testable as written):

- TC_V_001_03, TC_V_003_03 — simulated REST API 500 errors (`/api/venue-info`, `/api/availability` do not exist).
- TC_V_004_03 — interactive pricing calculator with add-ons.
- TC_A_006_02 — server-side validation blocking empty venue description (not implemented).
- TC_A_016_01 — notification enable/disable toggles (replaced by WhatsApp template CRUD).

**Rewritten** to match code:

- Visitor navigation: separate pages → **home page anchor sections** (`/#venue`, `/#gallery`, etc.).
- Inquiry contact: email/phone → **9-digit WhatsApp number** with `+967` prefix.
- Inquiry IDs: `INQ12345` → **9-digit reference code** (e.g. `472918305`).
- Slot statuses: `pending_confirmation` → **`PENDING`** (`AVAILABLE`, `PENDING`, `BOOKED`).
- Payment verification: `pending` / `Verified` → **`PENDING_VERIFICATION`** / **`VERIFIED`**.
- Analytics & reports: placeholder text → **Chart.js charts + async AI advisors**.
- UC016: notification settings → **WhatsApp notification template management** at `/admin/templates`.

**Added:**

- FAQ accordion on home page + admin FAQ editor (UC001 / UC006).
- Inquiry confirmation page, reference-code lookup, visitor self-cancellation.
- Calendar date → inquiry deep link; pricing package → inquiry deep link.
- Payment proof file validation; payment rejection; admin inquiry status management.
- Admin JWT login test case.

## Table of Contents

1. Introduction
   - 1.1 Purpose
   - 1.2 Scope
   - 1.3 Definitions, Acronyms and Abbreviations
   - 1.4 References
   - 1.5 System Overview
2. Test Cases, Data and Expected Results
   - 2.1 TC_A_000 — Admin Login (cross-cutting)
   - 2.2 TC_V_001 — View Venue Information & FAQ (UC001)
   - 2.3 TC_V_002 — View Media Gallery (UC002)
   - 2.4 TC_V_003 — Check Availability (UC003)
   - 2.5 TC_V_004 — View Pricing Panel (UC004)
   - 2.6 TC_V_005 — Submit Booking Inquiry (UC005)
   - 2.7 TC_A_006 — Manage Hall Information & FAQ (UC006)
   - 2.8 TC_A_007 — Manage Media Gallery (UC007)
   - 2.9 TC_A_008 — Manage Pricing Panel (UC008)
   - 2.10 TC_A_009 — Manage Calendar & Inquiries (UC009)
   - 2.11 TC_A_010 — View Traffic Analytics (UC010)
   - 2.12 TC_V_011 — Submit Payment Proof (UC011)
   - 2.13 TC_V_012 — Submit Feedback (UC012)
   - 2.14 TC_A_013 — Manage Payment Status (UC013)
   - 2.15 TC_A_014 — View/Generate Reports (UC014)
   - 2.16 TC_A_015 — Manage Feedback (UC015)
   - 2.17 TC_A_016 — Configure/Manage Notifications (UC016)
3. Test Execution Summary (UAT — 29/06/2026)

---

## 1. Introduction

### 1.1 Purpose

The purpose of this STD is to define the scope, approach, and test cases for the Al-Muneer Online Portal. It verifies that the system functions as specified in SRS v1.2 and is implemented per SDD v1.2. **Test execution results** are summarised in Section 3 and detailed in **Appendix E — UAT** (`appendix: UAT.md`).

### 1.2 Scope

**Visitor functionalities:**

- Single-page home: venue info, Google Maps embed, FAQ accordion, gallery with category filters, availability calendar, pricing packages.
- Booking inquiry with 9-digit reference code, confirmation page, lookup, and self-cancellation.
- Payment proof upload (JPG/PNG, max 5 MB).
- Feedback form with required rating and message.

**Administrator functionalities:**

- JWT-based login at `/admin/login`.
- Venue content management including FAQs (`faqJson`), gallery (images + YouTube videos + labels), pricing tiers, calendar slots, inquiries, payment proofs, feedback, reports (Chart.js), analytics (Chart.js), and WhatsApp notification templates.
- Three asynchronous Gemini AI advisor panels (analytics, reports, feedback).

**Non-functional (representative manual checks):**

- Basic usability on visitor and admin UIs.
- Admin route protection (unauthenticated access redirected to login).
- Browser compatibility (Chrome, Firefox).
- Page load and form submission responsiveness on broadband.

**Out of scope:**

- Stress/load testing beyond basic performance observation.
- Exhaustive combinatorial input testing.

### 1.3 Definitions, Acronyms and Abbreviations

| Term | Definition |
|------|------------|
| Admin | Administrator of the Al-Muneer Online Portal |
| FAQ | Frequently Asked Questions stored as JSON in `venue_info.faqJson` |
| FR | Functional Requirement |
| JWT | JSON Web Token used for admin session authentication |
| Reference Code | Visitor-facing 9-digit numeric inquiry identifier |
| SDD | Software Design Description |
| SRS | Software Requirements Specification |
| STD | Software Test Documentation |
| TC | Test Case |
| UC | Use Case |

### 1.4 References

a) Ahmed Hani Ahmed Ghaleb. (2026). Software Requirements Specification (SRS) Al-Muneer Online Portal, Version 1.2. UTM. (Internal)

b) Ahmed Hani Ahmed Ghaleb. (2026). Software Design Description (SDD) Al-Muneer Online Portal, Version 1.2. UTM. (Internal)

c) IEEE Std 829-2008 (adapted for course requirements).

d) Al-Muneer Online Portal — Core Specifications and README (implementation reference).

e) Ahmed Hani Ahmed Ghaleb. (2026). User Acceptance Test (UAT) Document, Version 1.0. UTM. Appendix E. (Internal)

### 1.5 System Overview

- **Section 1** — purpose, scope, definitions, references.
- **Section 2** — test cases grouped by use case. Each case lists ID, description, prerequisites, test data, steps, and expected results.
- Tests assume **manual execution** in a browser against a running instance (local or deployed) with PostgreSQL seeded.

**Visitor URL model:** The public site is primarily one scrollable page at `/`. Legacy paths (`/venue`, `/gallery`, `/calendar`, `/pricing`, `/faq`) redirect to the matching `/#section` anchor.

---

## 2. Test Cases, Data and Expected Results

### 2.1 Test TC_A_000 for Module: Admin Login (cross-cutting)

| ID | Name |
|----|------|
| TC_A_000_01 | Verify successful admin login with valid credentials |
| TC_A_000_02 | Verify failed login with invalid credentials |

#### TC_A_000_01: Verify successful admin login with valid credentials

**Test Case ID:** TC_A_000_01

**Description:** Administrator can log in and reach the dashboard.

| Prerequisites | Test Data |
|---------------|-----------|
| Portal deployed; default admin seeded | Username: `admin`, Password: `admin123` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Navigate to `/admin/login` | Login form displayed |
| 2 | Enter valid username and password; submit | Redirect to `/admin/dashboard`; dashboard loads |
| 3 | Attempt to access `/admin/inquiries` | Inquiry management page loads (not redirected to login) |

#### TC_A_000_02: Verify failed login with invalid credentials

**Test Case ID:** TC_A_000_02

**Description:** Invalid credentials are rejected without granting admin access.

| Prerequisites | Test Data |
|---------------|-----------|
| Portal deployed | Username: `admin`, Password: `wrongpassword` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Navigate to `/admin/login` | Login form displayed |
| 2 | Enter valid username with wrong password; submit | Error message shown; remains on login page |
| 3 | Navigate directly to `/admin/dashboard` without logging in | Redirected to `/admin/login` |

---

### 2.2 Test TC_V_001 for Module: View Venue Information & FAQ (UC001)

| ID | Name |
|----|------|
| TC_V_001_01 | Verify venue section and FAQ accordion on home page |
| TC_V_001_02 | Verify FAQ section hidden when no FAQs configured |

#### TC_V_001_01: Verify venue section and FAQ accordion on home page

**Test Case ID:** TC_V_001_01

**Description:** Visitor sees venue details, Google Maps embed, and expandable FAQ items on the home page.

| Prerequisites | Test Data |
|---------------|-----------|
| Portal deployed | `venue_info` has description, services, capacity, location, contactInfo, maps URLs, and at least 2 FAQ entries in `faqJson` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/` | Home page loads |
| 2 | Scroll to `#venue` (or click **Venue** in nav) | Description, capacity, contact, location, services, embedded map, and **Open in Google Maps** link displayed |
| 3 | Scroll to `#faq` (or click **FAQ** in nav) | FAQ section visible with question summaries |
| 4 | Click a FAQ question | Answer expands in accordion; first item may be open by default |
| 5 | Open `/faq` in address bar | Redirects to `/#faq` |

#### TC_V_001_02: Verify FAQ section hidden when no FAQs configured

**Test Case ID:** TC_V_001_02

**Description:** When `faqJson` is empty (`[]`), the FAQ section is not shown.

| Prerequisites | Test Data |
|---------------|-----------|
| Admin sets all FAQ rows blank and saves | `faqJson` = `[]` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/` and scroll past pricing | No `#faq` section rendered; **FAQ** nav link may still appear but section absent |
| 2 | Navigate to `/#faq` | No FAQ content; page does not error |

---

### 2.3 Test TC_V_002 for Module: View Media Gallery (UC002)

| ID | Name |
|----|------|
| TC_V_002_01 | Verify gallery display, filters, and lightbox |
| TC_V_002_02 | Verify empty gallery message |

#### TC_V_002_01: Verify gallery display, filters, and lightbox

**Test Case ID:** TC_V_002_01

**Description:** Gallery shows masonry layout with category filters and full-screen lightbox.

| Prerequisites | Test Data |
|---------------|-----------|
| At least 3 media items with different gallery labels | Mix of images and at least one YouTube video |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/` and scroll to `#gallery` | Thumbnails/previews displayed in masonry layout |
| 2 | Click a category filter button | Grid filters to matching label; **All** restores full set |
| 3 | Click an image thumbnail | Lightbox opens with full-size image |
| 4 | Close lightbox | Returns to gallery grid |
| 5 | Click a video item | Video plays in lightbox/player |

#### TC_V_002_02: Verify empty gallery message

**Test Case ID:** TC_V_002_02

**Description:** Empty gallery shows friendly placeholder.

| Prerequisites | Test Data |
|---------------|-----------|
| `media_items` table empty | No media records |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/#gallery` | Message such as "Gallery coming soon" displayed; no crash |

---

### 2.4 Test TC_V_003 for Module: Check Availability (UC003)

| ID | Name |
|----|------|
| TC_V_003_01 | Verify calendar statuses and past-date styling |
| TC_V_003_02 | Verify month navigation and date-to-inquiry flow |

#### TC_V_003_01: Verify calendar statuses and past-date styling

**Test Case ID:** TC_V_003_01

**Description:** Calendar reflects slot statuses; future dates without DB rows default to available.

| Prerequisites | Test Data |
|---------------|-----------|
| Admin configured slots for current month | e.g. `2026-07-10` = `BOOKED`, `2026-07-15` = `PENDING`; other future dates have no row |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/#availability` | Interactive calendar for current month |
| 2 | Observe configured dates | `BOOKED` and `PENDING` dates visually distinct from `AVAILABLE` |
| 3 | Observe past dates | Past dates dimmed / not selectable |
| 4 | Observe unconfigured future date | Treated as **AVAILABLE** (green/clickable) |

#### TC_V_003_02: Verify month navigation and date-to-inquiry flow

**Test Case ID:** TC_V_003_02

**Description:** Month navigation refreshes data; selecting an available date enables inquiry submission with pre-filled date.

| Prerequisites | Test Data |
|---------------|-----------|
| Slots exist in adjacent months | At least one `AVAILABLE` future date |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click **Next month** on calendar | Calendar updates; statuses match DB for that month |
| 2 | Click an **AVAILABLE** future date | Date highlighted; **Submit inquiry** action appears |
| 3 | Click **Submit inquiry** | Navigates to `/inquiry?date=YYYY-MM-DD` with event date pre-filled |

---

### 2.5 Test TC_V_004 for Module: View Pricing Panel (UC004)

| ID | Name |
|----|------|
| TC_V_004_01 | Verify active pricing tiers and Book links |
| TC_V_004_02 | Verify pricing section hidden when no active tiers |

#### TC_V_004_01: Verify active pricing tiers and Book links

**Test Case ID:** TC_V_004_01

**Description:** Active tiers shown with name, price (YER), description, and inquiry deep link.

| Prerequisites | Test Data |
|---------------|-----------|
| At least 2 active tiers and 1 inactive tier | Inactive tier has `isActive=false` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/#pricing` | Active tiers displayed with correct name, price, description |
| 2 | Verify inactive tier | Inactive tier **not** shown |
| 3 | Click **Book [package name]** | Navigates to `/inquiry?pricingId={id}` with package pre-selected on form |

#### TC_V_004_02: Verify pricing section hidden when no active tiers

**Test Case ID:** TC_V_004_02

**Description:** No pricing section when all tiers inactive or table empty.

| Prerequisites | Test Data |
|---------------|-----------|
| All tiers `isActive=false` or no tiers | — |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/` and scroll to where pricing would be | `#pricing` section not rendered |

---

### 2.6 Test TC_V_005 for Module: Submit Booking Inquiry (UC005)

| ID | Name |
|----|------|
| TC_V_005_01 | Verify successful inquiry submission and reference code |
| TC_V_005_02 | Verify validation for missing required fields |
| TC_V_005_03 | Verify invalid WhatsApp number rejected |
| TC_V_005_04 | Verify inquiry rejected for unavailable date |
| TC_V_005_05 | Verify lookup by reference code and visitor cancellation |

#### TC_V_005_01: Verify successful inquiry submission and reference code

**Test Case ID:** TC_V_005_01

**Description:** Valid inquiry creates DB record, shows confirmation with 9-digit reference code, and sets cookie.

| Prerequisites | Test Data |
|---------------|-----------|
| Future date is `AVAILABLE` | Name: `Test Visitor`, WhatsApp: `772629404`, Event date: future `AVAILABLE` date, optional event type/guests/message |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/inquiry` | Inquiry form displayed |
| 2 | Fill required fields; submit | Redirect to `/inquiry/confirmation/{9-digit-code}` |
| 3 | Observe confirmation page | Shows reference code, visitor name, event date, status `NEW` |
| 4 | Check database | `booking_inquiries` row with matching data and `reference_code` |
| 5 | Revisit `/inquiry` | Existing inquiry surfaced via cookie (if within 150 days) |

#### TC_V_005_02: Verify validation for missing required fields

**Test Case ID:** TC_V_005_02

| Prerequisites | Test Data |
|---------------|-----------|
| Inquiry form open | Leave Full Name or WhatsApp empty |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Leave **Full Name** empty; submit | Browser HTML5 validation prevents submit |
| 2 | Fill name; leave **WhatsApp** empty; submit | Submit blocked; required-field indication |

#### TC_V_005_03: Verify invalid WhatsApp number rejected

**Test Case ID:** TC_V_005_03

| Prerequisites | Test Data |
|---------------|-----------|
| Inquiry form open | WhatsApp: `abc` or fewer than 9 digits |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Enter non-numeric or wrong-length WhatsApp; submit | Pattern validation blocks submit (`[0-9]{9}`) |

#### TC_V_005_04: Verify inquiry rejected for unavailable date

**Test Case ID:** TC_V_005_04

| Prerequisites | Test Data |
|---------------|-----------|
| Date marked `BOOKED` or `PENDING` in admin calendar | That date selected on form |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Submit inquiry for booked/pending date | Error flash: date not available; no new inquiry created |

#### TC_V_005_05: Verify lookup by reference code and visitor cancellation

**Test Case ID:** TC_V_005_05

| Prerequisites | Test Data |
|---------------|-----------|
| Existing inquiry with no payment proofs | Known 9-digit reference code |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | On `/inquiry`, use **Look up** with valid reference code | Redirect to confirmation page for that inquiry |
| 2 | Click **Cancel inquiry** on confirmation page | Success message; inquiry status `CANCELLED_BY_VISITOR`; slot returns to `AVAILABLE` |
| 3 | Attempt cancel on inquiry **with** payment proof uploaded | Cancellation blocked with error message |

---

### 2.7 Test TC_A_006 for Module: Manage Hall Information & FAQ (UC006)

| ID | Name |
|----|------|
| TC_A_006_01 | Verify update of venue information and map URLs |
| TC_A_006_02 | Verify add, edit, and remove FAQ items |

#### TC_A_006_01: Verify update of venue information and map URLs

**Test Case ID:** TC_A_006_01

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in | New description: `Updated hall description`; updated maps share/embed URLs |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Navigate to `/admin/venue` | Editable venue form and live preview |
| 2 | Update description and map URLs; save | Success flash; DB updated |
| 3 | Open `/#venue` on public site | Updated description and map embed reflect changes |

#### TC_A_006_02: Verify add, edit, and remove FAQ items

**Test Case ID:** TC_A_006_02

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in | New FAQ: Q=`Parking available?`, A=`Yes, on-site parking for guests.` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | On `/admin/venue`, click **+ Add FAQ** | New FAQ row appears |
| 2 | Enter question and answer; save | Success message; `faqJson` updated in DB |
| 3 | Open `/#faq` | New FAQ visible in accordion |
| 4 | Edit answer in admin; save | Public FAQ shows updated answer |
| 5 | Remove FAQ row; save | FAQ removed from public page |

---

### 2.8 Test TC_A_007 for Module: Manage Media Gallery (UC007)

| ID | Name |
|----|------|
| TC_A_007_01 | Verify image upload appears in admin and public gallery |
| TC_A_007_02 | Verify add YouTube video and delete media item |
| TC_A_007_03 | Verify rejection of invalid file type or oversized image |

#### TC_A_007_01: Verify image upload appears in admin and public gallery

**Test Case ID:** TC_A_007_01

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in | Valid JPG/PNG ≤ 5 MB, caption optional |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Go to `/admin/gallery`; upload image | Success message; item in admin list |
| 2 | Open `/#gallery` | New image visible in masonry grid |

#### TC_A_007_02: Verify add YouTube video and delete media item

**Test Case ID:** TC_A_007_02

| Prerequisites | Test Data |
|---------------|-----------|
| Valid YouTube URL | e.g. `https://www.youtube.com/watch?v=...` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Add video via admin gallery form | Video appears in admin list and public gallery |
| 2 | Delete a media item; confirm | Removed from DB, admin list, and public gallery |

#### TC_A_007_03: Verify rejection of invalid file type or oversized image

**Test Case ID:** TC_A_007_03

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in | `document.pdf` or image > 5 MB |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Upload PDF | Error: only JPG/PNG allowed; no DB record |
| 2 | Upload oversized JPG | Error: exceeds 5 MB limit |

---

### 2.9 Test TC_A_008 for Module: Manage Pricing Panel (UC008)

| ID | Name |
|----|------|
| TC_A_008_01 | Verify create and update pricing tier |
| TC_A_008_02 | Verify validation on invalid tier data |

#### TC_A_008_01: Verify create and update pricing tier

**Test Case ID:** TC_A_008_01

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in | Name: `Gold Package`, Price: `150000`, Description, `isActive=true` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Create tier at `/admin/pricing` | Success; tier in admin list |
| 2 | Open `/#pricing` | Tier visible publicly |
| 3 | Edit price; set `isActive=false`; save | Tier hidden from public pricing section |

#### TC_A_008_02: Verify validation on invalid tier data

**Test Case ID:** TC_A_008_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Create tier with empty name | Validation prevents save |
| 2 | Create tier with non-numeric price | Validation prevents save |

---

### 2.10 Test TC_A_009 for Module: Manage Calendar & Inquiries (UC009)

| ID | Name |
|----|------|
| TC_A_009_01 | Verify manual calendar slot status update |
| TC_A_009_02 | Verify admin inquiry status update |

#### TC_A_009_01: Verify manual calendar slot status update

**Test Case ID:** TC_A_009_01

| Prerequisites | Test Data |
|---------------|-----------|
| Date `2026-11-10` currently `AVAILABLE` | Change to `BOOKED` |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/calendar` | Admin calendar with current statuses |
| 2 | Select date; set status to `BOOKED`; save | Success; DB `availability_slots` updated |
| 3 | Open visitor `/#availability` | Same date shows as `BOOKED` |
| 4 | Verify grid refresh | Calendar stays on selected month (no full-page jump) |

#### TC_A_009_02: Verify admin inquiry status update

**Test Case ID:** TC_A_009_02

| Prerequisites | Test Data |
|---------------|-----------|
| Inquiry in `NEW` status | Change to `CONTACTED` with optional admin note |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/inquiries`; open inquiry detail | Status dropdown and notes field shown |
| 2 | Update status; save | DB updated; list reflects new status |

---

### 2.11 Test TC_A_010 for Module: View Traffic Analytics (UC010)

| ID | Name |
|----|------|
| TC_A_010_01 | Verify analytics charts display page visit data |
| TC_A_010_02 | Verify AI Traffic Funnel Advisor loads asynchronously |

#### TC_A_010_01: Verify analytics charts display page visit data

**Test Case ID:** TC_A_010_01

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in; some `page_visits` records exist | Browse visitor pages before test |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/analytics` | Bar chart (top pages) and line chart (daily traffic) render without error |
| 2 | If no visit data yet | Charts show empty/zero state gracefully |

#### TC_A_010_02: Verify AI Traffic Funnel Advisor loads asynchronously

**Test Case ID:** TC_A_010_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/analytics` | Page renders immediately; AI panel shows loading spinner |
| 2 | Wait for `/admin/analytics/ai-insight` response | Bullet-point advice appears OR graceful error if API key unavailable |
| 3 | Verify page load | Initial render not blocked by AI call |

---

### 2.12 Test TC_V_011 for Module: Submit Payment Proof (UC011)

| ID | Name |
|----|------|
| TC_V_011_01 | Verify successful payment proof upload via reference code |
| TC_V_011_02 | Verify rejection of invalid proof file |

#### TC_V_011_01: Verify successful payment proof upload via reference code

**Test Case ID:** TC_V_011_01

| Prerequisites | Test Data |
|---------------|-----------|
| Existing inquiry; valid JPG/PNG ≤ 5 MB | Access via `/payment/upload?referenceCode={code}` from confirmation page |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open upload page from inquiry confirmation | Form shows visitor name and event date banner |
| 2 | Select valid receipt image; submit | Success message; `payment_proofs` record with `PENDING_VERIFICATION` |
| 3 | Check admin `/admin/payments` | New proof listed as pending |

#### TC_V_011_02: Verify rejection of invalid proof file

**Test Case ID:** TC_V_011_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Upload PDF or file > 5 MB | Error message; no proof record created |

---

### 2.13 Test TC_V_012 for Module: Submit Feedback (UC012)

| ID | Name |
|----|------|
| TC_V_012_01 | Verify successful feedback with rating and optional contact |
| TC_V_012_02 | Verify empty message rejected |

#### TC_V_012_01: Verify successful feedback with rating and optional contact

**Test Case ID:** TC_V_012_01

| Prerequisites | Test Data |
|---------------|-----------|
| — | Rating: 5, Message: `Great venue!`, optional name/WhatsApp |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/feedback` | Form with optional name/WhatsApp, required rating stars, required message |
| 2 | Submit with rating and message | Thank-you message; record in `feedback` with `isReviewed=false` |
| 3 | Submit anonymously (blank name/WhatsApp) | Accepted; stored with null contact fields |

#### TC_V_012_02: Verify empty message rejected

**Test Case ID:** TC_V_012_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Leave message blank; submit | Error: message required; no DB record |

---

### 2.14 Test TC_A_013 for Module: Manage Payment Status (UC013)

| ID | Name |
|----|------|
| TC_A_013_01 | Verify payment proof verification cascades to confirmed booking |
| TC_A_013_02 | Verify payment proof rejection |

#### TC_A_013_01: Verify payment proof verification cascades to confirmed booking

**Test Case ID:** TC_A_013_01

| Prerequisites | Test Data |
|---------------|-----------|
| Pending proof linked to inquiry | Admin note optional |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open proof in `/admin/payments/{id}` | Receipt image visible |
| 2 | Set status to `VERIFIED`; save | Proof `VERIFIED`; inquiry → `CONFIRMED`; slot → `BOOKED` |

#### TC_A_013_02: Verify payment proof rejection

**Test Case ID:** TC_A_013_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Set proof status to `REJECTED`; save | Proof `REJECTED`; inquiry status unchanged or per business rule; no slot booking |

---

### 2.15 Test TC_A_014 for Module: View/Generate Reports (UC014)

| ID | Name |
|----|------|
| TC_A_014_01 | Verify reports dashboard with date filter and charts |
| TC_A_014_02 | Verify AI Business Report Advisor |

#### TC_A_014_01: Verify reports dashboard with date filter and charts

**Test Case ID:** TC_A_014_01

| Prerequisites | Test Data |
|---------------|-----------|
| Inquiries/feedback/proofs in DB | `fromDate` and `toDate` spanning known records |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/reports` | Summary counts and pie/bar charts (inquiry status, payment status, feedback ratings) |
| 2 | Apply date range filter | Figures and charts reflect filtered period |
| 3 | Use range with no data | Zero/empty state shown without error |

#### TC_A_014_02: Verify AI Business Report Advisor

**Test Case ID:** TC_A_014_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/reports?fromDate=...&toDate=...` | AI panel shows spinner after page load |
| 2 | Wait for `/admin/reports/ai-insight` | 3 bullet points referencing report figures, or graceful API error |

---

### 2.16 Test TC_A_015 for Module: Manage Feedback (UC015)

| ID | Name |
|----|------|
| TC_A_015_01 | Verify mark feedback as reviewed |
| TC_A_015_02 | Verify AI Feedback Advisor |

#### TC_A_015_01: Verify mark feedback as reviewed

**Test Case ID:** TC_A_015_01

| Prerequisites | Test Data |
|---------------|-----------|
| Unreviewed feedback exists | Admin note optional |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/feedback` | List with snippet and review status |
| 2 | Open detail; mark reviewed; add note; save | `isReviewed=true`; note saved; list updated |

#### TC_A_015_02: Verify AI Feedback Advisor

**Test Case ID:** TC_A_015_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/feedback` | AI panel loads async via `/admin/feedback/ai-insight` |
| 2 | Observe response | Complaints vs highlights with actionable bullets, or graceful error |

---

### 2.17 Test TC_A_016 for Module: Configure/Manage Notifications (UC016)

| ID | Name |
|----|------|
| TC_A_016_01 | Verify create and edit WhatsApp notification template |
| TC_A_016_02 | Verify delete notification template |

#### TC_A_016_01: Verify create and edit WhatsApp notification template

**Test Case ID:** TC_A_016_01

**Description:** Admin manages reusable WhatsApp message templates with placeholders (not system notification toggles).

| Prerequisites | Test Data |
|---------------|-----------|
| Admin logged in | Event: `CUSTOM_REMINDER`, Label: `Booking Reminder`, Body with `{visitorName}`, `{eventDate}` placeholders |

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open `/admin/templates` | List of seeded templates (e.g. INQUIRY_RECEIVED, PAYMENT_VERIFIED) |
| 2 | Create new template; save | Template in list and DB |
| 3 | On inquiry detail, select template from dropdown | Message preview resolves placeholders; WhatsApp deep link generated client-side |

#### TC_A_016_02: Verify delete notification template

**Test Case ID:** TC_A_016_02

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Delete a custom template; confirm | Removed from list and DB |
| 2 | Verify seeded templates | Default templates remain unless explicitly deleted |

---

## Appendix: Test Case Traceability Summary

| UC | Test IDs | Primary FR coverage |
|----|----------|---------------------|
| Admin Login | TC_A_000_01–02 | Secure admin access |
| UC001 | TC_V_001_01–02 | Venue info, maps, FAQ |
| UC002 | TC_V_002_01–02 | Media gallery |
| UC003 | TC_V_003_01–02 | Availability calendar |
| UC004 | TC_V_004_01–02 | Pricing display |
| UC005 | TC_V_005_01–05 | Booking inquiry lifecycle |
| UC006 | TC_A_006_01–02 | Venue & FAQ management |
| UC007 | TC_A_007_01–03 | Gallery management |
| UC008 | TC_A_008_01–02 | Pricing management |
| UC009 | TC_A_009_01–02 | Calendar & inquiries |
| UC010 | TC_A_010_01–02 | Analytics & AI funnel |
| UC011 | TC_V_011_01–02 | Payment proof upload |
| UC012 | TC_V_012_01–02 | Feedback submission |
| UC013 | TC_A_013_01–02 | Payment verification |
| UC014 | TC_A_014_01–02 | Reports & AI advisor |
| UC015 | TC_A_015_01–02 | Feedback management |
| UC016 | TC_A_016_01–02 | WhatsApp templates |

**Total test cases:** 38 (across 17 modules including admin login).

---

## 3. Test Execution Summary (UAT — 29/06/2026)

Manual user acceptance testing was performed **locally** at `http://localhost:8080` against a PostgreSQL-seeded instance. Full step-by-step results, screenshots, and tester comments are in **Appendix E — UAT** (`appendix: UAT.md`). Screenshots: `test-execution/uat-screenshots/`.

| Field | Detail |
|-------|--------|
| **Tester** | Ahmed Hani (local UAT — Ibb) |
| **Admin credentials** | `admin` / `admin123` |
| **Browser** | Google Chrome (Chromium) |
| **STD version tested** | 1.2 |

### 3.1 Overall Results

| Result | Count |
|--------|-------|
| **Pass** | 35 |
| **Fail** | 1 |
| **Blocked** | 2 |
| **Total** | 38 |

### 3.2 Results by Module

| Module | Cases | Pass | Fail | Blocked | Notes |
|--------|-------|------|------|---------|-------|
| Admin Login (TC_A_000) | 2 | 2 | 0 | 0 | JWT login and route protection verified |
| Venue & FAQ (TC_V_001) | 2 | 2 | 0 | 0 | FAQ hide/show cycle tested via admin |
| Media Gallery (TC_V_002) | 2 | 1 | 0 | 1 | TC_V_002_02 blocked — gallery not empty on local DB |
| Availability (TC_V_003) | 2 | 2 | 0 | 0 | Calendar statuses and inquiry deep link OK |
| Pricing (TC_V_004) | 2 | 1 | 0 | 1 | TC_V_004_02 blocked — active tiers exist in seed data |
| Booking Inquiry (TC_V_005) | 5 | 5 | 0 | 0 | Reference code, validation, cancel flow OK |
| Venue Admin (TC_A_006) | 2 | 2 | 0 | 0 | Venue text and FAQ CRUD OK |
| Gallery Admin (TC_A_007) | 3 | 3 | 0 | 0 | Upload, video, invalid file rejection OK |
| Pricing Admin (TC_A_008) | 2 | 2 | 0 | 0 | Tier CRUD and validation OK |
| Calendar & Inquiries (TC_A_009) | 2 | 2 | 0 | 0 | Slot status and inquiry status update OK |
| Analytics (TC_A_010) | 2 | 2 | 0 | 0 | Charts render; AI panel async (placeholder API key) |
| Payment Proof (TC_V_011) | 2 | 2 | 0 | 0 | Upload and PDF rejection OK |
| Feedback (TC_V_012) | 2 | 1 | 1 | 0 | TC_V_012_01 step 3 (second anonymous submit) — flaky thank-you page |
| Payments Admin (TC_A_013) | 2 | 2 | 0 | 0 | Verify/reject cascades OK |
| Reports (TC_A_014) | 2 | 2 | 0 | 0 | Charts + date filter OK |
| Feedback Admin (TC_A_015) | 2 | 2 | 0 | 0 | Mark reviewed + AI panel OK |
| Templates (TC_A_016) | 2 | 2 | 0 | 0 | WhatsApp template CRUD OK |

### 3.3 Failed / Blocked Cases

| Case ID | Result | Summary |
|---------|--------|---------|
| TC_V_002_02 | **Blocked** | Cannot empty gallery without removing owner demo media |
| TC_V_004_02 | **Blocked** | Cannot deactivate all pricing tiers for negative test |
| TC_V_012_01 | **Fail** | Named feedback submit passed; second anonymous submit did not show thank-you page consistently |

### 3.4 Observations

- Admin login with `admin` / `admin123` works; invalid password shows error and protected routes redirect to login.
- Gemini AI advisor panels load asynchronously and degrade gracefully when API key is a placeholder.
- Visitor booking flow (inquiry → confirmation → payment proof → admin verify) works end-to-end.
- Negative tests requiring **empty** gallery or **no** active pricing tiers need a dedicated test database or teardown script.
