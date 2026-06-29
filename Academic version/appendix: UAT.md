# UNIVERSITI TEKNOLOGI MALAYSIA

## FACULTY OF COMPUTING - UTM Johor Bahru

**SECJ 3032:** Software Engineering PSM1

**Semester:** 01, 2024/2025

# User Acceptance Test (UAT) Document

**Project:** Al-Muneer Online Portal

**Version:** 1.0

**Prepared by:** Ahmed Ghaleb

**Appendix:** APPENDIX E — UAT

---

## Document Overview

This document records **user acceptance testing** performed against the Al-Muneer Online Portal, following the test cases defined in Software Test Documentation (STD) Version 1.2.

| Field | Detail |
|-------|--------|
| **Test date** | 29/06/2026 20:27 UTC |
| **STD reference** | Version 1.2 |

### Performed by

1. **Visitor** — public pages (`/`, `/inquiry`, `/feedback`, `/payment/upload`)
2. **Administrator**

### Summary

| Total test cases | 38 |
| Pass | 35 |
| Fail | 1 |
| Blocked | 2 |

> Screenshots are referenced as figures below.

---

## PART 1

### VISITOR MODULE (PUBLIC WEBSITE)

**Description:**

This part covers visitor-facing functionality on the public website: venue information, gallery, availability calendar, pricing, booking inquiries, payment proof upload, and feedback.

**Performed by:**

1. Visitor (public browser)

---

### 1.0 View Venue Information & FAQ

**STD reference:** TC_V_001

**Figure 0.0 (g)** — ![Figure 0.0 (g)](/Academic%20version/test-execution/uat-screenshots/TC_V_001_01_s1.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](/Academic%20version/test-execution/uat-screenshots/TC_V_001_01_s2.png) **Figure 0.0 (i)** — ![Figure 0.0 (i)](/Academic%20version/test-execution/uat-screenshots/TC_V_001_01_s3.png) **Figure 0.0 (j)** — ![Figure 0.0 (j)](/Academic%20version/test-execution/uat-screenshots/TC_V_001_01_s4.png)

*(additional figures: 4 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 1.1 | 1. open /<br>2. click venue nav<br>3. scroll faq<br>4. click faq question<br>5. open /faq | home loads<br>venue details + map<br>faq items visible<br>… | **Pass** |
| 1.2 | 1. open / scroll down<br>2. go /#faq | no #faq section<br>no error, no content | **Pass** |

---

### 2.0 View Media Gallery

**STD reference:** TC_V_002

**Figure 0.0 (o)** — ![Figure 0.0 (o)](/Academic%20version/test-execution/uat-screenshots/TC_V_002_01_s1.png) **Figure 0.0 (p)** — ![Figure 0.0 (p)](/Academic%20version/test-execution/uat-screenshots/TC_V_002_01_s2.png) **Figure 0.0 (q)** — ![Figure 0.0 (q)](/Academic%20version/test-execution/uat-screenshots/TC_V_002_01_s3.png) **Figure 0.0 (r)** — ![Figure 0.0 (r)](/Academic%20version/test-execution/uat-screenshots/TC_V_002_01_s4.png)

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 2.1 | 1. open /#gallery<br>2. click category filter<br>3. click image thumb<br>4. close lightbox | masonry grid<br>grid filters<br>lightbox opens<br>… | **Pass** |
| 2.2 | 1. check gallery empty prereq | no media in db | **Blocked** |

---

### 3.0 Check Availability Calendar

**STD reference:** TC_V_003

**Figure 0.0 (s)** — ![Figure 0.0 (s)](/Academic%20version/test-execution/uat-screenshots/TC_V_003_01_s1.png) **Figure 0.0 (t)** — ![Figure 0.0 (t)](/Academic%20version/test-execution/uat-screenshots/TC_V_003_01_s2.png) **Figure 0.0 (u)** — ![Figure 0.0 (u)](/Academic%20version/test-execution/uat-screenshots/TC_V_003_01_s3.png) **Figure 0.0 (v)** — ![Figure 0.0 (v)](/Academic%20version/test-execution/uat-screenshots/TC_V_003_01_s4.png)

*(additional figures: 3 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 3.1 | 1. open calendar section<br>2. check statuses<br>3. past dates dimmed<br>4. future unconfigured date | calendar visible<br>booked/pending distinct<br>past not clickable<br>… | **Pass** |
| 3.2 | 1. click next month<br>2. pick available date 2026-07-01<br>3. click submit inquiry | calendar updates<br>submit inquiry action<br>/inquiry?date=2026-07-01 | **Pass** |

---

### 4.0 View Pricing Panel

**STD reference:** TC_V_004

**Figure 0.0 (z)** — ![Figure 0.0 (z)](/Academic%20version/test-execution/uat-screenshots/TC_V_004_01_s1.png) **Figure 0.0 (a)** — ![Figure 0.0 (a)](/Academic%20version/test-execution/uat-screenshots/TC_V_004_01_s2.png) **Figure 0.0 (b)** — ![Figure 0.0 (b)](/Academic%20version/test-execution/uat-screenshots/TC_V_004_01_s3.png)

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 4.1 | 1. open pricing section<br>2. inactive tier hidden<br>3. click book package | active tiers shown<br>not on public page<br>/inquiry?pricingId= | **Pass** |
| 4.2 | 1. prereq: no active tiers | pricing hidden | **Blocked** |

---

### 5.0 Submit Booking Inquiry

**STD reference:** TC_V_005

**Figure 0.0 (f)** — ![Figure 0.0 (f)](/Academic%20version/test-execution/uat-screenshots/TC_V_005_01_s1.png) **Figure 0.0 (g)** — ![Figure 0.0 (g)](/Academic%20version/test-execution/uat-screenshots/TC_V_005_01_s2.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](/Academic%20version/test-execution/uat-screenshots/TC_V_005_01_s3.png) **Figure 0.0 (i)** — ![Figure 0.0 (i)](/Academic%20version/test-execution/uat-screenshots/TC_V_005_01_s5.png)

*(additional figures: 8 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 5.1 | 1. open /inquiry<br>2. submit inquiry<br>3. check confirmation<br>4. check database<br>5. revisit /inquiry | form shown<br>confirmation with 9-digit code<br>shows name, date, status NEW<br>… | **Pass** |
| 5.2 | 1. empty name submit<br>2. empty whatsapp | html5 blocks<br>blocked | **Pass** |
| 5.3 | 1. bad whatsapp abc<br>1. short whatsapp 5 digits | pattern blocks<br>blocked | **Pass** |
| 5.4 | 1. submit for booked date 2026-08-29 | error flash | **Pass** |
| 5.5 | 1. lookup by ref code<br>2. cancel inquiry<br>3. cancel inquiry with payment proof | confirmation page<br>status cancelled<br>blocked with error | **Pass** |

---

### 6.0 Submit Payment Proof

**STD reference:** TC_V_011

**Figure 0.0 (q)** — ![Figure 0.0 (q)](/Academic%20version/test-execution/uat-screenshots/TC_V_011_01_s1.png) **Figure 0.0 (r)** — ![Figure 0.0 (r)](/Academic%20version/test-execution/uat-screenshots/TC_V_011_01_s2.png) **Figure 0.0 (s)** — ![Figure 0.0 (s)](/Academic%20version/test-execution/uat-screenshots/TC_V_011_01_s3.png) **Figure 0.0 (t)** — ![Figure 0.0 (t)](/Academic%20version/test-execution/uat-screenshots/TC_V_011_02_s1.png)

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 6.1 | 1. open upload from ref<br>2. upload receipt png<br>3. admin payments list | banner with visitor info<br>pending verification<br>new proof pending | **Pass** |
| 6.2 | 1. upload pdf proof | error no record | **Pass** |

---

### 7.0 Submit Feedback

**STD reference:** TC_V_012

**Figure 0.0 (u)** — ![Figure 0.0 (u)](/Academic%20version/test-execution/uat-screenshots/TC_V_012_01_s1.png) **Figure 0.0 (v)** — ![Figure 0.0 (v)](/Academic%20version/test-execution/uat-screenshots/TC_V_012_01_s2.png) **Figure 0.0 (w)** — ![Figure 0.0 (w)](/Academic%20version/test-execution/uat-screenshots/TC_V_012_01_s3.png) **Figure 0.0 (x)** — ![Figure 0.0 (x)](/Academic%20version/test-execution/uat-screenshots/TC_V_012_02_s1.png)

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 7.1 | 1. open /feedback<br>2. submit with rating<br>3. anonymous submit | form shown<br>thank you message<br>accepted | **Fail** |
| 7.2 | 1. empty message submit | error required | **Pass** |

---

## PART 2

### ADMINISTRATOR MODULE (ADMIN DASHBOARD)

**Description:**

This part covers administrator functionality in the admin dashboard: secure login, content management, calendar and inquiry handling, payment verification, analytics, reports, feedback review, and WhatsApp notification templates.

**Performed by:**

1. Administrator (`admin`)

---

### 1.0 Login

**STD reference:** TC_A_000

**Figure 0.0 (a)** — ![Figure 0.0 (a)](/Academic%20version/test-execution/uat-screenshots/TC_A_000_01_s1.png) **Figure 0.0 (b)** — ![Figure 0.0 (b)](/Academic%20version/test-execution/uat-screenshots/TC_A_000_01_s2.png) **Figure 0.0 (c)** — ![Figure 0.0 (c)](/Academic%20version/test-execution/uat-screenshots/TC_A_000_01_s3.png) **Figure 0.0 (d)** — ![Figure 0.0 (d)](/Academic%20version/test-execution/uat-screenshots/TC_A_000_02_s1.png)

*(additional figures: 2 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 1.1 | 1. go to /admin/login<br>2. enter admin / admin123, sign in<br>3. open /admin/inquiries | login form shown<br>redirect dashboard<br>inquiries page loads | **Pass** |
| 1.2 | 1. open login page<br>2. wrong password<br>3. hit dashboard without login | login form<br>error, stay on login<br>redirect login | **Pass** |

---

### 2.0 Manage Hall Information & FAQ

**STD reference:** TC_A_006

**Figure 0.0 (n)** — ![Figure 0.0 (n)](/Academic%20version/test-execution/uat-screenshots/TC_A_006_01_s1.png) **Figure 0.0 (o)** — ![Figure 0.0 (o)](/Academic%20version/test-execution/uat-screenshots/TC_A_006_01_s2.png) **Figure 0.0 (p)** — ![Figure 0.0 (p)](/Academic%20version/test-execution/uat-screenshots/TC_A_006_01_s3.png) **Figure 0.0 (q)** — ![Figure 0.0 (q)](/Academic%20version/test-execution/uat-screenshots/TC_A_006_02_s1.png)

*(additional figures: 3 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 2.1 | 1. open /admin/venue<br>2. update description save<br>3. check public /#venue | editable form + preview<br>success flash<br>updated text visible | **Pass** |
| 2.2 | 1. add faq row save<br>3. public faq shows new item<br>4. edit faq answer<br>5. remove faq | faqJson updated<br>accordion has parking<br>public updated<br>… | **Pass** |

---

### 3.0 Manage Media Gallery

**STD reference:** TC_A_007

**Figure 0.0 (u)** — ![Figure 0.0 (u)](/Academic%20version/test-execution/uat-screenshots/TC_A_007_01_s1.png) **Figure 0.0 (v)** — ![Figure 0.0 (v)](/Academic%20version/test-execution/uat-screenshots/TC_A_007_01_s2.png) **Figure 0.0 (w)** — ![Figure 0.0 (w)](/Academic%20version/test-execution/uat-screenshots/TC_A_007_02_s1.png) **Figure 0.0 (x)** — ![Figure 0.0 (x)](/Academic%20version/test-execution/uat-screenshots/TC_A_007_02_s2.png)

*(additional figures: 2 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 3.1 | 1. upload jpg in admin<br>2. public gallery shows image | success + list<br>visible in grid | **Pass** |
| 3.2 | 1. add youtube video<br>2. delete media item | in admin list<br>removed | **Pass** |
| 3.3 | 1. upload pdf<br>2. check upload accepts only images | error jpg/png only<br>jpg/png only in form | **Pass** |

---

### 4.0 Manage Pricing Panel

**STD reference:** TC_A_008

**Figure 0.0 (a)** — ![Figure 0.0 (a)](/Academic%20version/test-execution/uat-screenshots/TC_A_008_01_s1.png) **Figure 0.0 (b)** — ![Figure 0.0 (b)](/Academic%20version/test-execution/uat-screenshots/TC_A_008_01_s2.png) **Figure 0.0 (c)** — ![Figure 0.0 (c)](/Academic%20version/test-execution/uat-screenshots/TC_A_008_01_s3.png) **Figure 0.0 (d)** — ![Figure 0.0 (d)](/Academic%20version/test-execution/uat-screenshots/TC_A_008_02_s1.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 4.1 | 1. create tier<br>2. public pricing<br>3. deactivate tier | in admin list<br>tier visible<br>hidden public | **Pass** |
| 4.2 | 1. empty tier name<br>2. non-numeric price | validation blocks<br>blocked | **Pass** |

---

### 5.0 Manage Calendar & Inquiries

**STD reference:** TC_A_009

**Figure 0.0 (f)** — ![Figure 0.0 (f)](/Academic%20version/test-execution/uat-screenshots/TC_A_009_01_s1.png) **Figure 0.0 (g)** — ![Figure 0.0 (g)](/Academic%20version/test-execution/uat-screenshots/TC_A_009_01_s2.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](/Academic%20version/test-execution/uat-screenshots/TC_A_009_01_s3.png) **Figure 0.0 (i)** — ![Figure 0.0 (i)](/Academic%20version/test-execution/uat-screenshots/TC_A_009_01_s4.png)

*(additional figures: 2 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 5.1 | 1. open admin calendar<br>2. set 2026-09-18 booked<br>3. visitor calendar same date<br>4. grid refresh no jump | grid shown<br>db updated<br>shows booked<br>… | **Pass** |
| 5.2 | 1. open inquiry detail<br>2. update status contacted | status dropdown shown<br>saved in list | **Pass** |

---

### 6.0 View Traffic Analytics

**STD reference:** TC_A_010

**Figure 0.0 (l)** — ![Figure 0.0 (l)](/Academic%20version/test-execution/uat-screenshots/TC_A_010_01_s1.png) **Figure 0.0 (m)** — ![Figure 0.0 (m)](/Academic%20version/test-execution/uat-screenshots/TC_A_010_01_s2.png) **Figure 0.0 (n)** — ![Figure 0.0 (n)](/Academic%20version/test-execution/uat-screenshots/TC_A_010_02_s1.png) **Figure 0.0 (o)** — ![Figure 0.0 (o)](/Academic%20version/test-execution/uat-screenshots/TC_A_010_02_s2.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 6.1 | 1. open analytics<br>2. empty data graceful | charts render<br>no crash | **Pass** |
| 6.2 | 1. page loads<br>2. wait ai insight<br>3. page not blocked | ai spinner first<br>bullets or graceful error<br>dashboard rendered first | **Pass** |

---

### 7.0 Manage Payment Status

**STD reference:** TC_A_013

**Figure 0.0 (y)** — ![Figure 0.0 (y)](/Academic%20version/test-execution/uat-screenshots/TC_A_013_01_s1.png) **Figure 0.0 (z)** — ![Figure 0.0 (z)](/Academic%20version/test-execution/uat-screenshots/TC_A_013_01_s2.png) **Figure 0.0 (a)** — ![Figure 0.0 (a)](/Academic%20version/test-execution/uat-screenshots/TC_A_013_02_s2.png)

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 7.1 | 1. open proof detail<br>2. set verified | receipt image visible<br>inquiry confirmed slot booked | **Pass** |
| 7.2 | 1. reject proof | status rejected | **Pass** |

---

### 8.0 View / Generate Reports

**STD reference:** TC_A_014

**Figure 0.0 (b)** — ![Figure 0.0 (b)](/Academic%20version/test-execution/uat-screenshots/TC_A_014_01_s1.png) **Figure 0.0 (c)** — ![Figure 0.0 (c)](/Academic%20version/test-execution/uat-screenshots/TC_A_014_01_s2.png) **Figure 0.0 (d)** — ![Figure 0.0 (d)](/Academic%20version/test-execution/uat-screenshots/TC_A_014_01_s3.png) **Figure 0.0 (e)** — ![Figure 0.0 (e)](/Academic%20version/test-execution/uat-screenshots/TC_A_014_02_s1.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 8.1 | 1. open reports<br>2. date filter<br>3. empty range | charts + counts<br>figures update<br>zero state ok | **Pass** |
| 8.2 | 1. open reports<br>2. wait ai report advisor | ai spinner<br>3 bullets or error | **Pass** |

---

### 9.0 Manage Feedback

**STD reference:** TC_A_015

**Figure 0.0 (g)** — ![Figure 0.0 (g)](/Academic%20version/test-execution/uat-screenshots/TC_A_015_01_s1.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](/Academic%20version/test-execution/uat-screenshots/TC_A_015_02_s1.png)

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 9.1 | 1. mark reviewed from list | list updated | **Pass** |
| 9.2 | 1. feedback ai panel | async insight | **Pass** |

---

### 10.0 Manage WhatsApp Notification Templates

**STD reference:** TC_A_016

**Figure 0.0 (i)** — ![Figure 0.0 (i)](/Academic%20version/test-execution/uat-screenshots/TC_A_016_01_s1.png) **Figure 0.0 (j)** — ![Figure 0.0 (j)](/Academic%20version/test-execution/uat-screenshots/TC_A_016_01_s2.png) **Figure 0.0 (k)** — ![Figure 0.0 (k)](/Academic%20version/test-execution/uat-screenshots/TC_A_016_01_s3.png) **Figure 0.0 (l)** — ![Figure 0.0 (l)](/Academic%20version/test-execution/uat-screenshots/TC_A_016_02_s1.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail |
|----|---------|------------------|-------------|
| 10.1 | 1. open templates<br>2. create template<br>3. inquiry template dropdown | seeded list<br>in list<br>preview resolves placeholders | **Pass** |
| 10.2 | 1. delete custom template<br>2. seeded templates remain | removed from list<br>defaults still there | **Pass** |

---

## Appendix — Full Case Index

| Case ID | Result |
|---------|--------|
| TC_A_000_01 | Pass |
| TC_A_000_02 | Pass |
| TC_A_006_01 | Pass |
| TC_A_006_02 | Pass |
| TC_A_007_01 | Pass |
| TC_A_007_02 | Pass |
| TC_A_007_03 | Pass |
| TC_A_008_01 | Pass |
| TC_A_008_02 | Pass |
| TC_A_009_01 | Pass |
| TC_A_009_02 | Pass |
| TC_A_010_01 | Pass |
| TC_A_010_02 | Pass |
| TC_A_013_01 | Pass |
| TC_A_013_02 | Pass |
| TC_A_014_01 | Pass |
| TC_A_014_02 | Pass |
| TC_A_015_01 | Pass |
| TC_A_015_02 | Pass |
| TC_A_016_01 | Pass |
| TC_A_016_02 | Pass |
| TC_V_001_01 | Pass |
| TC_V_001_02 | Pass |
| TC_V_002_01 | Pass |
| TC_V_002_02 | Blocked |
| TC_V_003_01 | Pass |
| TC_V_003_02 | Pass |
| TC_V_004_01 | Pass |
| TC_V_004_02 | Blocked |
| TC_V_005_01 | Pass |
| TC_V_005_02 | Pass |
| TC_V_005_03 | Pass |
| TC_V_005_04 | Pass |
| TC_V_005_05 | Pass |
| TC_V_011_01 | Pass |
| TC_V_011_02 | Pass |
| TC_V_012_01 | Fail |
| TC_V_012_02 | Pass |

---

*End of UAT Document*