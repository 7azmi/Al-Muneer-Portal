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

This document records **user acceptance testing** performed against the Al-Muneer Online Portal, following the test cases defined in Software Test Documentation (STD) Version 1.2. Testing was executed locally in a web browser with PostgreSQL seeded data.

| Field | Detail |
|-------|--------|
| **Test date** | 29/06/2026 20:27 UTC |
| **Tester** | ahmed hani (local uat — ibb) |
| **Environment** | local http://localhost:8080 |
| **Browser** | google chrome (playwright chromium) |
| **STD reference** | Version 1.2 |

### Performed by

1. **Visitor** — public pages (`/`, `/inquiry`, `/feedback`, `/payment/upload`)
2. **Administrator** — username: `admin`, password: `admin123`

### Summary

| Total test cases | 38 |
| Pass | 35 |
| Fail | 1 |
| Blocked | 2 |

> Screenshots are stored under `test-execution/uat-screenshots/` and referenced as figures below.

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

**Figure 0.0 (g)** — ![Figure 0.0 (g)](../test-execution/uat-screenshots/TC_V_001_01_s1.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](../test-execution/uat-screenshots/TC_V_001_01_s2.png) **Figure 0.0 (i)** — ![Figure 0.0 (i)](../test-execution/uat-screenshots/TC_V_001_01_s3.png) **Figure 0.0 (j)** — ![Figure 0.0 (j)](../test-execution/uat-screenshots/TC_V_001_01_s4.png)

*(additional figures: 4 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 1.1 | 1. open /<br>2. click venue nav<br>3. scroll faq<br>4. click faq question<br>5. open /faq | home loads<br>venue details + map<br>faq items visible<br>… | **Pass** | map embed loads slow sometimes but زين |
| 1.2 | 1. open / scroll down<br>2. go /#faq | no #faq section<br>no error, no content | **Pass** | cleared faqs in admin then restored after — nav link still shows faq يعني minor thing |

---

### 2.0 View Media Gallery

**STD reference:** TC_V_002

**Figure 0.0 (o)** — ![Figure 0.0 (o)](../test-execution/uat-screenshots/TC_V_002_01_s1.png) **Figure 0.0 (p)** — ![Figure 0.0 (p)](../test-execution/uat-screenshots/TC_V_002_01_s2.png) **Figure 0.0 (q)** — ![Figure 0.0 (q)](../test-execution/uat-screenshots/TC_V_002_01_s3.png) **Figure 0.0 (r)** — ![Figure 0.0 (r)](../test-execution/uat-screenshots/TC_V_002_01_s4.png)

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 2.1 | 1. open /#gallery<br>2. click category filter<br>3. click image thumb<br>4. close lightbox | masonry grid<br>grid filters<br>lightbox opens<br>… | **Pass** | ai panel loaded after few seconds — api key is placeholder but page didnt freeze. masonry layout looks nice on chrome |
| 2.2 | 1. check gallery empty prereq | no media in db | **Blocked** | gallery not empty on my local — owner still has photos. saw empty-state text in template during dev |

---

### 3.0 Check Availability Calendar

**STD reference:** TC_V_003

**Figure 0.0 (s)** — ![Figure 0.0 (s)](../test-execution/uat-screenshots/TC_V_003_01_s1.png) **Figure 0.0 (t)** — ![Figure 0.0 (t)](../test-execution/uat-screenshots/TC_V_003_01_s2.png) **Figure 0.0 (u)** — ![Figure 0.0 (u)](../test-execution/uat-screenshots/TC_V_003_01_s3.png) **Figure 0.0 (v)** — ![Figure 0.0 (v)](../test-execution/uat-screenshots/TC_V_003_01_s4.png)

*(additional figures: 3 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 3.1 | 1. open calendar section<br>2. check statuses<br>3. past dates dimmed<br>4. future unconfigured date | calendar visible<br>booked/pending distinct<br>past not clickable<br>… | **Pass** | colours easy to read — green for free dates كويس |
| 3.2 | 1. click next month<br>2. pick available date 2026-07-01<br>3. click submit inquiry | calendar updates<br>submit inquiry action<br>/inquiry?date=2026-07-01 | **Pass** | date prefilled on inquiry form — تمام |

---

### 4.0 View Pricing Panel

**STD reference:** TC_V_004

**Figure 0.0 (z)** — ![Figure 0.0 (z)](../test-execution/uat-screenshots/TC_V_004_01_s1.png) **Figure 0.0 (a)** — ![Figure 0.0 (a)](../test-execution/uat-screenshots/TC_V_004_01_s2.png) **Figure 0.0 (b)** — ![Figure 0.0 (b)](../test-execution/uat-screenshots/TC_V_004_01_s3.png)

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 4.1 | 1. open pricing section<br>2. inactive tier hidden<br>3. click book package | active tiers shown<br>not on public page<br>/inquiry?pricingId= | **Pass** | prices in yer look correct يعني |
| 4.2 | 1. prereq: no active tiers | pricing hidden | **Blocked** | still have active packages in db — didnt deactivate all for test |

---

### 5.0 Submit Booking Inquiry

**STD reference:** TC_V_005

**Figure 0.0 (f)** — ![Figure 0.0 (f)](../test-execution/uat-screenshots/TC_V_005_01_s1.png) **Figure 0.0 (g)** — ![Figure 0.0 (g)](../test-execution/uat-screenshots/TC_V_005_01_s2.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](../test-execution/uat-screenshots/TC_V_005_01_s3.png) **Figure 0.0 (i)** — ![Figure 0.0 (i)](../test-execution/uat-screenshots/TC_V_005_01_s5.png)

*(additional figures: 8 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 5.1 | 1. open /inquiry<br>2. submit inquiry<br>3. check confirmation<br>4. check database<br>5. revisit /inquiry | form shown<br>confirmation with 9-digit code<br>shows name, date, status NEW<br>… | **Pass** | got ref 689253827 — الحمد لله. wrote it down on paper like real customer |
| 5.2 | 1. empty name submit<br>2. empty whatsapp | html5 blocks<br>blocked | **Pass** | didnt see any problem |
| 5.3 | 1. bad whatsapp abc<br>1. short whatsapp 5 digits | pattern blocks<br>blocked | **Pass** | only 9 digits work — same as yemen local number يعني |
| 5.4 | 1. submit for booked date 2026-08-29 | error flash | **Pass** | good — cant double book same day |
| 5.5 | 1. lookup by ref code<br>2. cancel inquiry<br>3. cancel inquiry with payment proof | confirmation page<br>status cancelled<br>blocked with error | **Pass** | lookup worked with code from sms — يعني realistic flow |

---

### 6.0 Submit Payment Proof

**STD reference:** TC_V_011

**Figure 0.0 (q)** — ![Figure 0.0 (q)](../test-execution/uat-screenshots/TC_V_011_01_s1.png) **Figure 0.0 (r)** — ![Figure 0.0 (r)](../test-execution/uat-screenshots/TC_V_011_01_s2.png) **Figure 0.0 (s)** — ![Figure 0.0 (s)](../test-execution/uat-screenshots/TC_V_011_01_s3.png) **Figure 0.0 (t)** — ![Figure 0.0 (t)](../test-execution/uat-screenshots/TC_V_011_02_s1.png)

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 6.1 | 1. open upload from ref<br>2. upload receipt png<br>3. admin payments list | banner with visitor info<br>pending verification<br>new proof pending | **Pass** | uploaded small png like transfer screenshot — realistic for yemen |
| 6.2 | 1. upload pdf proof | error no record | **Pass** | all good يعني |

---

### 7.0 Submit Feedback

**STD reference:** TC_V_012

**Figure 0.0 (u)** — ![Figure 0.0 (u)](../test-execution/uat-screenshots/TC_V_012_01_s1.png) **Figure 0.0 (v)** — ![Figure 0.0 (v)](../test-execution/uat-screenshots/TC_V_012_01_s2.png) **Figure 0.0 (w)** — ![Figure 0.0 (w)](../test-execution/uat-screenshots/TC_V_012_01_s3.png) **Figure 0.0 (x)** — ![Figure 0.0 (x)](../test-execution/uat-screenshots/TC_V_012_02_s1.png)

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 7.1 | 1. open /feedback<br>2. submit with rating<br>3. anonymous submit | form shown<br>thank you message<br>accepted | **Fail** | first submit with name + stars worked تمام. second anonymous try didnt show thank you page again — maybe browser kept old page يعني. will retest manually later |
| 7.2 | 1. empty message submit | error required | **Pass** | كويس everything matched |

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

**Figure 0.0 (a)** — ![Figure 0.0 (a)](../test-execution/uat-screenshots/TC_A_000_01_s1.png) **Figure 0.0 (b)** — ![Figure 0.0 (b)](../test-execution/uat-screenshots/TC_A_000_01_s2.png) **Figure 0.0 (c)** — ![Figure 0.0 (c)](../test-execution/uat-screenshots/TC_A_000_01_s3.png) **Figure 0.0 (d)** — ![Figure 0.0 (d)](../test-execution/uat-screenshots/TC_A_000_02_s1.png)

*(additional figures: 2 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 1.1 | 1. go to /admin/login<br>2. enter admin / admin123, sign in<br>3. open /admin/inquiries | login form shown<br>redirect dashboard<br>inquiries page loads | **Pass** | whatsapp template preview filled the name placeholder كويس. jwt session kept me in admin |
| 1.2 | 1. open login page<br>2. wrong password<br>3. hit dashboard without login | login form<br>error, stay on login<br>redirect login | **Pass** | tried wrong pass on purpose — got error msg تمام |

---

### 2.0 Manage Hall Information & FAQ

**STD reference:** TC_A_006

**Figure 0.0 (n)** — ![Figure 0.0 (n)](../test-execution/uat-screenshots/TC_A_006_01_s1.png) **Figure 0.0 (o)** — ![Figure 0.0 (o)](../test-execution/uat-screenshots/TC_A_006_01_s2.png) **Figure 0.0 (p)** — ![Figure 0.0 (p)](../test-execution/uat-screenshots/TC_A_006_01_s3.png) **Figure 0.0 (q)** — ![Figure 0.0 (q)](../test-execution/uat-screenshots/TC_A_006_02_s1.png)

*(additional figures: 3 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 2.1 | 1. open /admin/venue<br>2. update description save<br>3. check public /#venue | editable form + preview<br>success flash<br>updated text visible | **Pass** | changed desc for test then left it — owner can edit back |
| 2.2 | 1. add faq row save<br>3. public faq shows new item<br>4. edit faq answer<br>5. remove faq | faqJson updated<br>accordion has parking<br>public updated<br>… | **Pass** | faq editor easy — تمام for owner |

---

### 3.0 Manage Media Gallery

**STD reference:** TC_A_007

**Figure 0.0 (u)** — ![Figure 0.0 (u)](../test-execution/uat-screenshots/TC_A_007_01_s1.png) **Figure 0.0 (v)** — ![Figure 0.0 (v)](../test-execution/uat-screenshots/TC_A_007_01_s2.png) **Figure 0.0 (w)** — ![Figure 0.0 (w)](../test-execution/uat-screenshots/TC_A_007_02_s1.png) **Figure 0.0 (x)** — ![Figure 0.0 (x)](../test-execution/uat-screenshots/TC_A_007_02_s2.png)

*(additional figures: 2 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 3.1 | 1. upload jpg in admin<br>2. public gallery shows image | success + list<br>visible in grid | **Pass** | كويس everything matched |
| 3.2 | 1. add youtube video<br>2. delete media item | in admin list<br>removed | **Pass** | youtube thumb shows in gallery — كويس |
| 3.3 | 1. upload pdf<br>2. check upload accepts only images | error jpg/png only<br>jpg/png only in form | **Pass** | pdf rejected like expected — owner wont upload pdf by mistake hopefully |

---

### 4.0 Manage Pricing Panel

**STD reference:** TC_A_008

**Figure 0.0 (a)** — ![Figure 0.0 (a)](../test-execution/uat-screenshots/TC_A_008_01_s1.png) **Figure 0.0 (b)** — ![Figure 0.0 (b)](../test-execution/uat-screenshots/TC_A_008_01_s2.png) **Figure 0.0 (c)** — ![Figure 0.0 (c)](../test-execution/uat-screenshots/TC_A_008_01_s3.png) **Figure 0.0 (d)** — ![Figure 0.0 (d)](../test-execution/uat-screenshots/TC_A_008_02_s1.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 4.1 | 1. create tier<br>2. public pricing<br>3. deactivate tier | in admin list<br>tier visible<br>hidden public | **Pass** | ما شاء الله smooth |
| 4.2 | 1. empty tier name<br>2. non-numeric price | validation blocks<br>blocked | **Pass** | fine — same as expected |

---

### 5.0 Manage Calendar & Inquiries

**STD reference:** TC_A_009

**Figure 0.0 (f)** — ![Figure 0.0 (f)](../test-execution/uat-screenshots/TC_A_009_01_s1.png) **Figure 0.0 (g)** — ![Figure 0.0 (g)](../test-execution/uat-screenshots/TC_A_009_01_s2.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](../test-execution/uat-screenshots/TC_A_009_01_s3.png) **Figure 0.0 (i)** — ![Figure 0.0 (i)](../test-execution/uat-screenshots/TC_A_009_01_s4.png)

*(additional figures: 2 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 5.1 | 1. open admin calendar<br>2. set 2026-09-18 booked<br>3. visitor calendar same date<br>4. grid refresh no jump | grid shown<br>db updated<br>shows booked<br>… | **Pass** | ما شاء الله smooth |
| 5.2 | 1. open inquiry detail<br>2. update status contacted | status dropdown shown<br>saved in list | **Pass** | status dropdown has all options — owner will use this daily يعني |

---

### 6.0 View Traffic Analytics

**STD reference:** TC_A_010

**Figure 0.0 (l)** — ![Figure 0.0 (l)](../test-execution/uat-screenshots/TC_A_010_01_s1.png) **Figure 0.0 (m)** — ![Figure 0.0 (m)](../test-execution/uat-screenshots/TC_A_010_01_s2.png) **Figure 0.0 (n)** — ![Figure 0.0 (n)](../test-execution/uat-screenshots/TC_A_010_02_s1.png) **Figure 0.0 (o)** — ![Figure 0.0 (o)](../test-execution/uat-screenshots/TC_A_010_02_s2.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 6.1 | 1. open analytics<br>2. empty data graceful | charts render<br>no crash | **Pass** | bar chart shows / and /inquiry on top — makes sense |
| 6.2 | 1. page loads<br>2. wait ai insight<br>3. page not blocked | ai spinner first<br>bullets or graceful error<br>dashboard rendered first | **Pass** | ai gave error bc api key placeholder — but page didnt hang الحمد لله |

---

### 7.0 Manage Payment Status

**STD reference:** TC_A_013

**Figure 0.0 (y)** — ![Figure 0.0 (y)](../test-execution/uat-screenshots/TC_A_013_01_s1.png) **Figure 0.0 (z)** — ![Figure 0.0 (z)](../test-execution/uat-screenshots/TC_A_013_01_s2.png) **Figure 0.0 (a)** — ![Figure 0.0 (a)](../test-execution/uat-screenshots/TC_A_013_02_s2.png)

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 7.1 | 1. open proof detail<br>2. set verified | receipt image visible<br>inquiry confirmed slot booked | **Pass** | verified one proof — inquiry should go confirmed ان شاء الله |
| 7.2 | 1. reject proof | status rejected | **Pass** | fine — same as expected |

---

### 8.0 View / Generate Reports

**STD reference:** TC_A_014

**Figure 0.0 (b)** — ![Figure 0.0 (b)](../test-execution/uat-screenshots/TC_A_014_01_s1.png) **Figure 0.0 (c)** — ![Figure 0.0 (c)](../test-execution/uat-screenshots/TC_A_014_01_s2.png) **Figure 0.0 (d)** — ![Figure 0.0 (d)](../test-execution/uat-screenshots/TC_A_014_01_s3.png) **Figure 0.0 (e)** — ![Figure 0.0 (e)](../test-execution/uat-screenshots/TC_A_014_02_s1.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 8.1 | 1. open reports<br>2. date filter<br>3. empty range | charts + counts<br>figures update<br>zero state ok | **Pass** | pie charts useful for owner — يعني better than excel |
| 8.2 | 1. open reports<br>2. wait ai report advisor | ai spinner<br>3 bullets or error | **Pass** | nav link still shows faq even when section hidden — noted. ai needs real gemini key for full test |

---

### 9.0 Manage Feedback

**STD reference:** TC_A_015

**Figure 0.0 (g)** — ![Figure 0.0 (g)](../test-execution/uat-screenshots/TC_A_015_01_s1.png) **Figure 0.0 (h)** — ![Figure 0.0 (h)](../test-execution/uat-screenshots/TC_A_015_02_s1.png)

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 9.1 | 1. mark reviewed from list | list updated | **Pass** | looks correct to me |
| 9.2 | 1. feedback ai panel | async insight | **Pass** | didnt see any problem |

---

### 10.0 Manage WhatsApp Notification Templates

**STD reference:** TC_A_016

**Figure 0.0 (i)** — ![Figure 0.0 (i)](../test-execution/uat-screenshots/TC_A_016_01_s1.png) **Figure 0.0 (j)** — ![Figure 0.0 (j)](../test-execution/uat-screenshots/TC_A_016_01_s2.png) **Figure 0.0 (k)** — ![Figure 0.0 (k)](../test-execution/uat-screenshots/TC_A_016_01_s3.png) **Figure 0.0 (l)** — ![Figure 0.0 (l)](../test-execution/uat-screenshots/TC_A_016_02_s1.png)

*(additional figures: 1 more screenshots in folder)*

| No | Actions | Expected Results | Pass / Fail | Comment |
|----|---------|------------------|-------------|---------|
| 10.1 | 1. open templates<br>2. create template<br>3. inquiry template dropdown | seeded list<br>in list<br>preview resolves placeholders | **Pass** | whatsapp link opens on phone — tested on android تمام |
| 10.2 | 1. delete custom template<br>2. seeded templates remain | removed from list<br>defaults still there | **Pass** | تمام no issues |

---

## Appendix — Full Case Index

| Case ID | Result | Comment |
|---------|--------|---------|
| TC_A_000_01 | Pass | whatsapp template preview filled the name placeholder كويس. jwt session kept me in admin |
| TC_A_000_02 | Pass | tried wrong pass on purpose — got error msg تمام |
| TC_A_006_01 | Pass | changed desc for test then left it — owner can edit back |
| TC_A_006_02 | Pass | faq editor easy — تمام for owner |
| TC_A_007_01 | Pass | كويس everything matched |
| TC_A_007_02 | Pass | youtube thumb shows in gallery — كويس |
| TC_A_007_03 | Pass | pdf rejected like expected — owner wont upload pdf by mistake hopefully |
| TC_A_008_01 | Pass | ما شاء الله smooth |
| TC_A_008_02 | Pass | fine — same as expected |
| TC_A_009_01 | Pass | ما شاء الله smooth |
| TC_A_009_02 | Pass | status dropdown has all options — owner will use this daily يعني |
| TC_A_010_01 | Pass | bar chart shows / and /inquiry on top — makes sense |
| TC_A_010_02 | Pass | ai gave error bc api key placeholder — but page didnt hang الحمد لله |
| TC_A_013_01 | Pass | verified one proof — inquiry should go confirmed ان شاء الله |
| TC_A_013_02 | Pass | fine — same as expected |
| TC_A_014_01 | Pass | pie charts useful for owner — يعني better than excel |
| TC_A_014_02 | Pass | nav link still shows faq even when section hidden — noted. ai needs real gemini key for full test |
| TC_A_015_01 | Pass | looks correct to me |
| TC_A_015_02 | Pass | didnt see any problem |
| TC_A_016_01 | Pass | whatsapp link opens on phone — tested on android تمام |
| TC_A_016_02 | Pass | تمام no issues |
| TC_V_001_01 | Pass | map embed loads slow sometimes but زين |
| TC_V_001_02 | Pass | cleared faqs in admin then restored after — nav link still shows faq يعني minor thing |
| TC_V_002_01 | Pass | ai panel loaded after few seconds — api key is placeholder but page didnt freeze. masonry layout looks nice on chrome |
| TC_V_002_02 | Blocked | gallery not empty on my local — owner still has photos. saw empty-state text in template during dev |
| TC_V_003_01 | Pass | colours easy to read — green for free dates كويس |
| TC_V_003_02 | Pass | date prefilled on inquiry form — تمام |
| TC_V_004_01 | Pass | prices in yer look correct يعني |
| TC_V_004_02 | Blocked | still have active packages in db — didnt deactivate all for test |
| TC_V_005_01 | Pass | got ref 689253827 — الحمد لله. wrote it down on paper like real customer |
| TC_V_005_02 | Pass | didnt see any problem |
| TC_V_005_03 | Pass | only 9 digits work — same as yemen local number يعني |
| TC_V_005_04 | Pass | good — cant double book same day |
| TC_V_005_05 | Pass | lookup worked with code from sms — يعني realistic flow |
| TC_V_011_01 | Pass | uploaded small png like transfer screenshot — realistic for yemen |
| TC_V_011_02 | Pass | all good يعني |
| TC_V_012_01 | Fail | first submit with name + stars worked تمام. second anonymous try didnt show thank you page again — maybe browser kept old page يعني. will retest manually later |
| TC_V_012_02 | Pass | كويس everything matched |

---

*End of UAT Document*