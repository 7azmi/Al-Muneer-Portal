# Al-Muneer Portal — Demo Test Execution Report

**Source:** `appendix: STD.md`  
**Environment:** Local (`http://localhost:8080`)  
**Executed:** 2026-06-29 19:35 UTC  
**Tool:** Playwright (Chromium) — automated browser execution

## Summary

| Metric | Value |
|--------|-------|
| Cases executed | 4 |
| Passed | 4 |
| Failed | 0 |

> This is a **demonstration** run covering a small subset of STD test cases.

## ✅ TC_A_000_01: Verify successful admin login with valid credentials

**Status:** PASS

| Step | Action | Expected | Actual | Result | Screenshot |
|------|--------|----------|--------|--------|------------|
| 1 | Navigate to /admin/login | Login form displayed | Login form visible | PASS | ![step 1](screenshots/TC_A_000_01_step1_login_form.png) |
| 2 | Enter valid credentials; submit | Redirect to /admin/dashboard; dashboard loads | URL=http://localhost:8080/admin/dashboard, title=Dashboard | PASS | ![step 2](screenshots/TC_A_000_01_step2_dashboard.png) |
| 3 | Access /admin/inquiries | Inquiry management page loads (not redirected to login) | URL=http://localhost:8080/admin/inquiries, title=Booking Inquiries | PASS | ![step 3](screenshots/TC_A_000_01_step3_inquiries.png) |

## ✅ TC_A_000_02: Verify failed login with invalid credentials

**Status:** PASS

| Step | Action | Expected | Actual | Result | Screenshot |
|------|--------|----------|--------|--------|------------|
| 1 | Navigate to /admin/login | Login form displayed | Login form visible | PASS | ![step 1](screenshots/TC_A_000_02_step1_login_form.png) |
| 2 | Enter valid username with wrong password; submit | Error message shown; remains on login page | URL=http://localhost:8080/admin/login, error='Invalid username or password' | PASS | ![step 2](screenshots/TC_A_000_02_step2_error.png) |
| 3 | Navigate to /admin/dashboard without logging in | Redirected to /admin/login | URL=http://localhost:8080/admin/login | PASS | ![step 3](screenshots/TC_A_000_02_step3_redirect.png) |

## ✅ TC_V_001_01: Verify venue section and FAQ accordion on home page

**Status:** PASS

| Step | Action | Expected | Actual | Result | Screenshot |
|------|--------|----------|--------|--------|------------|
| 1 | Open / | Home page loads | Hero section visible | PASS | ![step 1](screenshots/TC_V_001_01_step1_home.png) |
| 2 | Scroll to #venue (click Venue in nav) | Description, capacity, contact, location, services, map embed, Open in Google Maps link | venue visible=True, maps link=True | PASS | ![step 2](screenshots/TC_V_001_01_step2_venue.png) |
| 3 | Scroll to #faq (click FAQ in nav) | FAQ section visible with question summaries | FAQ visible=True, items=4 | PASS | ![step 3](screenshots/TC_V_001_01_step3_faq.png) |
| 4 | Click a FAQ question | Answer expands in accordion | Open FAQ items=2 | PASS | ![step 4](screenshots/TC_V_001_01_step4_faq_expand.png) |
| 5 | Open /faq in address bar | Redirects to /#faq | URL=http://localhost:8080/#faq | PASS | ![step 5](screenshots/TC_V_001_01_step5_faq_redirect.png) |

## ✅ TC_V_005_02: Verify validation for missing required fields

**Status:** PASS

| Step | Action | Expected | Actual | Result | Screenshot |
|------|--------|----------|--------|--------|------------|
| 0 | Open /inquiry | Inquiry form displayed | Form visible | PASS | ![step 0](screenshots/TC_V_005_02_step0_form.png) |
| 1 | Leave Full Name empty; submit | Browser HTML5 validation prevents submit | Submit blocked by validation | PASS | ![step 1](screenshots/TC_V_005_02_step1_name_validation.png) |
| 2 | Fill name; leave WhatsApp empty; submit | Submit blocked; required-field indication | Submit blocked by validation | PASS | ![step 2](screenshots/TC_V_005_02_step2_whatsapp_validation.png) |
