# UNIVERSITI TEKNOLOGI MALAYSIA

## FACULTY OF COMPUTING - UTM Johor Bahru

**SECJ 3032:** Software Engineering PSM1

**Semester:** 01, 2024/2025

# Software Test Documentation

**Project:** Al-Muneer Online Portal

**Version:** 1.1

**Prepared by:** Ahmed Ghaleb

**Appendix:** APPENDIX D - STD

## Revision Page

### a. Overview

This document is Version 1.0 of the Software Test Documentation (STD) for the Al-Muneer Online Portal. It outlines the strategy, scope, resources, and schedule of intended testing activities. This document provides the necessary information about testing activities, including test descriptions for various modules and functionalities of the system, based on the requirements specified in the Software Requirements Specification (SRS) Version 1.0 and the design detailed in the Software Design Description (SDD) Version 1.0. Test results are not included in this version as per course guidelines; the focus is on test case design.

### b. Target Audience

The target audience for this STD document includes:

- **The project developer** (Ahmed Hani Ahmed Ghaleb), as a guide for executing tests during and after development.

- **The project supervisor** (Dr. Muhammad Luqman bin Mohd Shafie), for review and assess the testing strategy and coverage.

- **Examiners**, for evaluation purposes of the planned testing approach.

### c. Project Team Members

The project is being undertaken by a single student developer responsible for all aspects, including testing.

- **Team Member:** Ahmed Hani Ahmed Ghaleb

- **Assigned Module(s) for Testing:** All modules of the Al-Muneer Online Portal.

### d. Version Control History

|   |   |   |   |
|---|---|---|---|
|**Version**|**Primary Author(s)**|**Description of Version**|**Date Completed**|
|1.0|Ahmed Hani Ahmed Ghaleb|Initial draft of STD document, including test case design.|30/05/2025|
|1.1|Ahmed Hani Ahmed Ghaleb|Use case 6 removed|30/05/2025|

## Table of Contents

1. Introduction 1.1 Purpose 1.2 Scope 1.3 Definitions, Acronyms and Abbreviations 1.4 References 1.5 System Overview

2. Test Cases, Data and Expected Results 2.1 Test TC_V_001 for Module : <View Venue Information (UC001)> 2.2 Test TC_V_002 for Module : <View Media Gallery (UC002)> 2.3 Test TC_V_003 for Module : <Check Availability (UC003)> 2.4 Test TC_V_004 for Module : <View Pricing Panel (UC004)> 2.5 Test TC_V_005 for Module : <Submit Booking Inquiry (UC005)> 2.6 Test TC_A_006 for Module : <Manage Hall Information (UC006)> 2.7 Test TC_A_007 for Module : <Manage Media Gallery (UC007)> 2.8 Test TC_A_008 for Module : <Manage Pricing Panel (UC008)> 2.9 Test TC_A_009 for Module : <Manage Calendar & Inquiries (UC009)> 2.10 Test TC_A_010 for Module : <View Traffic Analytics (UC010)> 2.11 Test TC_V_011 for Module : <Submit Payment Proof (UC011)> 2.12 Test TC_V_012 for Module : <Submit Feedback (UC012)> 2.13 Test TC_A_013 for Module : <Manage Payment Status (UC013)> 2.14 Test TC_A_014 for Module : <View/Generate Reports (UC014)> 2.15 Test TC_A_015 for Module : <Manage Feedback (UC015)> 2.16 Test TC_A_016 for Module : <Configure/Manage Notifications (UC016)>

## 1. Introduction

### 1.1 Purpose

The purpose of this Software Test Documentation (STD) for the Al-Muneer Online Portal is to define the scope, approach, resources, and schedule of all testing activities. This document provides the necessary information about testing activities that include test descriptions (test cases) designed to verify and validate that the Al-Muneer Online Portal system functions as specified in the Software Requirements Specification (SRS Version 1.0) and is implemented according to the Software Design Description (SDD Version 1.0). It aims to ensure that the developed system is robust, reliable, and meets the stakeholder's and users' needs.

This STD provides a comprehensive plan for various levels of testing, including unit, integration, and system testing (with a focus on functional and usability aspects). Note: For the scope of this software engineering course, actual test results and execution logs are not included in this document; the primary focus is on the planning and design of test cases.

### 1.2 Scope

This STD covers the testing of all functional and key non-functional aspects of the Al-Muneer Online Portal. The software product is a web-based application designed to provide information, facilitate booking inquiries, and enable administrative management for Al-Muneer Hall.

The scope of testing includes:

**Visitor Functionalities:**

- Viewing venue information, media gallery, availability calendar, and pricing.

- Submitting booking inquiries.

- Submitting payment proofs.

- Submitting feedback.

**Administrator Functionalities:**

- Secure login to the admin panel.

- Management of venue content (information, media, FAQs, pricing).

- Management of the availability calendar.

- Viewing and managing booking inquiries.

- Viewing and managing payment proof statuses.

- Viewing and managing user feedback.

- Generation and viewing of basic reports.

- Configuration of system notifications.

**Non-Functional Aspects:**

- Basic usability testing for both Visitor and Administrator interfaces.

- Security testing related to admin authentication and basic input validation.

- Performance testing concerning page load times and form submission response times (as defined in NFRs).

- Compatibility testing across major web browsers.

**Out of Scope for this STD (unless explicitly stated otherwise in test cases):**

- Full-scale stress testing or load testing beyond basic performance checks.

- Automated GUI testing tool implementation (manual testing is assumed primarily).

- Exhaustive testing of all possible data combinations; focus will be on representative and boundary value cases.

- Actual test execution results (as per course guidelines).

This will provide the basis for the brief description of your product testing strategy. The software product is the Al-Muneer Online Portal, a custom web application. The goals of testing are to identify defects, ensure compliance with requirements, and provide confidence in the quality of the delivered system. The objectives are to achieve adequate test coverage for all critical functionalities and to verify key non-functional attributes. The benefits include early defect detection, reduced risk of system failure post-deployment, and ultimately, a higher quality product for the stakeholder.

### 1.3 Definitions, Acronyms and Abbreviations

Definitions of all terms, acronyms, and abbreviations that might exist to properly interpret the STD are defined here. Many of these are consistent with those defined in the SRS and SDD documents.

- **Admin:** Administrator of the Al-Muneer Online Portal.

- **API:** Application Programming Interface.

- **CSS:** Cascading Style Sheets.

- **DB:** Database.

- **ERD:** Entity-Relationship Diagram.

- **FAQ:** Frequently Asked Questions.

- **FR:** Functional Requirement.

- **FYP:** Final Year Project.

- **GUI:** Graphical User Interface.

- **HTML:** HyperText Markup Language.

- **HTTP:** Hypertext Transfer Protocol.

- **HTTPS:** Hypertext Transfer Protocol Secure.

- **JS:** JavaScript.

- **NFR:** Non-Functional Requirement.

- **PSM:** Projek Sarjana Muda (Bachelor's Degree Project).

- **SDD:** System Design Document.

- **SRS:** Software Requirements Specification.

- **STD:** Software Test Documentation.

- **TC:** Test Case.

- **UC:** Use Case.

- **UI:** User Interface.

- **UX:** User Experience.

- **UTM:** Universiti Teknologi Malaysia.

- **VPS:** Virtual Private Server.

### 1.4 References

This subsection lists any documents which were used as sources of information for this STD. a) Ahmed Hani Ahmed Ghaleb. (2025). Software Requirements Specification (SRS) Al-Muneer Online Portal, Version 1.0. Universiti Teknologi Malaysia. (Internal Project Document) b) Ahmed Hani Ahmed Ghaleb. (2025). Software Design Description (SDD) - Al-Muneer Online Portal, Version 1.0. Universiti Teknologi Malaysia. (Internal Project Document) c) IEEE. (2008). IEEE Standard for Software Test Documentation (IEEE Std 829-2008). Institute of Electrical and Electronics Engineers. (Adaptations made for course requirements) d) Al-Muneer Online Portal FYP1 Progress 2 Report (Containing Chapters 3, 4, 5 of the main report). Ahmed Hani Ahmed Ghaleb. May 2025. (Internal Project Document)

### 1.5 System Overview

This subsection describes what the rest of the STD contains and explains how the STD is organised.

a) This Software Test Documentation (STD) is organized to provide a clear plan for testing the Al-Muneer Online Portal.

- **Section 1 (Introduction):** Defines the purpose, scope, definitions, references, and overview of this STD document.

- **Section 2 (Test Cases, Data and Expected Results):** This is the core section containing detailed test cases for various modules and functionalities of the system. Each test case will specify its ID, name, description, preconditions, input data, steps, and expected results. Test cases are grouped by system modules and their corresponding use cases.

- **Section 3 (Appendices):** Includes supplementary information, such as a Traceability Matrix linking test cases back to requirements.

b) The STD is organized to allow the developer and supervisor to understand the testing planned for each part of the Al-Muneer Online Portal. Section 2 provides a structured breakdown of test cases, categorized for clarity and to ensure comprehensive coverage of the system's features as outlined in the SRS and SDD.

## 2. Test Cases, Data and Expected Results

### 2.1 Test TC_V_001 for Module : <View Venue Information (UC001)>

This section covers tests for verifying the functionality of viewing venue information as described in Use Case UC001. This test suite contains the following test cases:

- **TC_V_001_01:** Verify successful display of complete venue information.

- **TC_V_001_02:** Verify system behavior when venue information content is missing or not configured.

- **TC_V_001_03:** Verify system behavior when there is a temporary error retrieving venue information.

#### 2.1.1 TC_V_001_01: Verify successful display of complete venue information.

**Test Case ID:** TC_V_001_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that when a visitor navigates to the venue information page, all configured details (description, services, location, contact) are displayed accurately and completely.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Venue Information" page.|
|2|Venue information (description, services, location, contact details) has been configured in the database by the Administrator.|2|Database State: VenueInfo table contains complete and accurate data for description, services, location, contactInfo, and faqJson.|

**Test Conditions:** Test with various browsers (Chrome, Firefox) to ensure consistent display. Ensure all sections of the venue information (description, services, location, contact) are populated.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Venue Information" (or "About Us", "القاعة" etc.).|The browser navigates to the Venue Information page.|
|3|Observe the displayed content on the Venue Information page.|Main description of the hall.<br><br>List of services offered.<br><br>Hall capacity (if specified).<br><br>Physical location/address.<br><br>Contact information (phone number, etc.).|

#### 2.1.2 TC_V_001_02: Verify system behavior when venue information content is missing or not configured.

**Test Case ID:** TC_V_001_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the system handles gracefully and displays an appropriate message if the venue information content is not found in the database (e.g., admin deleted it or it was never set up).

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Venue Information" page.|
|2|The VenueInfo table in the database has no record, or the relevant fields (description, services, etc.) are empty or NULL.|2|Database State: VenueInfo table has no record for infoId=1. OR the description, services, location, contactInfo fields are left blank or NULL/empty.|

**Test Conditions:** Test scenario where the specific content is intentionally deleted by an admin.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Venue Information".|The browser navigates to the Venue Information page.|
|3|Observe the content displayed on the Venue Information page.|The page should display a user-friendly message indicating that the content is not currently available (e.g., "Venue information is currently unavailable," or "Content not found."). The page should not crash or show a technical error/stack trace.|

#### 2.1.3 TC_V_001_03: Verify system behavior when there is a temporary error retrieving venue information.

**Test Case ID:** TC_V_001_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that if there's a temporary backend or database connectivity issue preventing retrieval of venue information, the system displays a user-friendly error message rather than crashing.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Venue Information" page.|
|2|The backend service responsible for fetching venue information is simulated to return an error (e.g., database connection timeout, 500 server error).|2|System State: Simulate a condition where the backend API endpoint `/api/venue-info` returns a server-side error status (e.g., 500, 503).|

**Test Conditions:** This test might require simulating a backend failure condition (e.g., temporarily stopping the database service if testing locally, or mocking an error response from the API endpoint).

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Venue Information".|The browser attempts to navigate to the Venue Information page.|
|3|Observe the displayed content on the Venue Information page.|The page should display a user-friendly error message indicating a temporary problem (e.g., "Information is temporarily unavailable. Please try again shortly," or "A system error occurred."). The page should not crash or show a technical error/stack trace to the user.|

### 2.2 Test TC_V_002 for Module : <View Media Gallery (UC002)>

This section covers tests for verifying the functionality of viewing the media gallery as described in Use Case UC002. This test suite contains the following test cases:

- **TC_V_002_01:** Verify successful display of media gallery with items.

- **TC_V_002_02:** Verify system behavior when the media gallery is empty.

- **TC_V_002_03:** Verify viewing a specific media item (image/video).

#### 2.2.1 TC_V_002_01: Verify successful display of media gallery with items.

**Test Case ID:** TC_V_002_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that when a visitor navigates to the media gallery page, all configured media items (images/videos) are displayed correctly as thumbnails or previews.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Media Gallery" page.|
|2|Several media items (e.g., 3 images, 1 video) have been uploaded and configured in the gallery by the Administrator.|2|Database State: MediaItem table contains multiple records with valid file paths, types (image/video), and captions.|

**Test Conditions:** Test with different numbers of media items to check layout.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Media Gallery" (or "الصور والفيديو" etc.).|The browser navigates to the Media Gallery page.|
|3|Observe the content displayed on the Media Gallery page.|The page should display all configured media items, typically as a grid of thumbnails for images and placeholders/previews for videos. Each item should be identifiable.|

#### 2.2.2 TC_V_002_02: Verify system behavior when the media gallery is empty.

**Test Case ID:** TC_V_002_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the system displays an appropriate message if no media items have been uploaded to the gallery by the administrator.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Media Gallery" page.|
|2|No media items are configured in the MediaItem table in the database.|2|Database State: MediaItem table is empty.|

**Test Conditions:** Scenario where an admin has not yet uploaded any media.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Media Gallery".|The browser navigates to the Media Gallery page.|
|3|Observe the displayed content on the Media Gallery page.|The page should display a user-friendly message indicating that the gallery is empty or being updated (e.g., "Our gallery is currently being updated. Please check back soon!").|

#### 2.2.3 TC_V_002_03: Verify viewing a specific media item (image/video).

**Test Case ID:** TC_V_002_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that when a visitor clicks on a media item thumbnail/preview in the gallery, the full-size image is displayed or the video plays correctly.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Clicks on specific image and video items within the gallery.|
|2|At least one image and one video are configured and displayed in the media gallery.|2|Database State: MediaItem table contains records for at least one valid image and one valid video, with correct filePath and mediaType.|
|3|The media files exist at their specified paths and are not corrupted.|3|File System State: The actual image and video files exist at the paths specified in the database and are accessible by the web server.|

**Test Conditions:** Test for both image types (e.g., JPG, PNG) and video playback.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the Media Gallery page (as per TC_V_002_01).|Media gallery with items is displayed.|
|2|Click on an image thumbnail from the gallery.|The full-size image should be displayed, typically in a lightbox overlay or a dedicated viewer. The image should be clear and correctly rendered.|
|3|Close the image viewer/lightbox.|The user should be returned to the gallery view.|
|4|If a video item is present, click on its preview/placeholder.|The video should start playing, either in a lightbox, an embedded player, or a dedicated view. Video controls (play, pause, volume) should be functional if provided by the player.|
|5|Close the video player/lightbox.|The user should be returned to the gallery view.|

### 2.3 Test TC_V_003 for Module : <Check Availability (UC003)>

This test contains the following test cases:

- **TC_V_003_01:** Verify successful display of availability calendar with correct date statuses.

- **TC_V_003_02:** Verify navigation through calendar months/years and correct data refresh.

- **TC_V_003_03:** Verify system behavior when availability data cannot be retrieved.

#### 2.3.1 TC_V_003_01: Verify successful display of availability calendar with correct date statuses.

**Test Case ID:** TC_V_003_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that the interactive calendar correctly displays dates as "available," "booked," or "pending_confirmation" based on data configured by the administrator.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Availability" page.|
|2|The Administrator has configured various dates in the AvailabilitySlot table with different statuses (e.g., some available, some booked, some pending).|2|Database State: AvailabilitySlot table contains records for the current/default display month with a mix of status values (e.g., '2025-07-10' is 'booked', '2025-07-15' is 'available', '2025-07-20' is 'pending_confirmation').|

**Test Conditions:** Test with a month that contains a mix of available, booked, and pending dates.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Availability" or "Calendar" (التقويم etc.).|The browser navigates to the Availability page.|
|3|Observe the displayed interactive calendar for the current/default month.|The calendar should display, with dates visually differentiated according to their status (e.g., different colors or icons for "available," "booked," "pending_confirmation"). Dates should match the statuses set in the database.|

#### 2.3.2 TC_V_003_02: Verify navigation through calendar months/years and correct data refresh.

**Test Case ID:** TC_V_003_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that when a visitor navigates to previous/next months or years in the interactive calendar, the display updates correctly with the availability statuses for the newly selected period.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: User clicks on calendar navigation controls (next month, previous month, select year/month).|
|2|Availability statuses are configured in the database for multiple months/years.|2|Database State: AvailabilitySlot table has records with various statuses spread across several consecutive and non-consecutive months/years.|
|3|The availability calendar page (as per TC_V_003_01) is currently displayed.|||

**Test Conditions:** Test navigation to both past and future months/years where data exists.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|On the displayed availability calendar, click the "Next Month" navigation control.|The calendar should update to display the next month. The availability statuses (available, booked, pending) shown for the dates in this new month should accurately reflect the data configured in the database for that specific month.|
|2|Click the "Previous Month" navigation control.|The calendar should update to display the previous month. Availability statuses should be accurate for this month.|
|3|(If year navigation is available) Navigate to a different year.|The calendar should update to display the selected year and month. Availability statuses should be accurate.|

#### 2.3.3 TC_V_003_03: Verify system behavior when availability data cannot be retrieved.

**Test Case ID:** TC_V_003_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that if there's an error fetching availability data (e.g., backend/database issue), the system displays a user-friendly error message.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Availability" page.|
|2|The backend service for fetching availability data is simulated to return an error.|2|System State: Simulate a condition where the backend API endpoint `/api/availability` returns a server-side error status (e.g., 500).|

**Test Conditions:** Simulate a server-side error when the frontend requests calendar data.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Availability" or "Calendar".|The browser attempts to load the Availability page and fetch calendar data.|
|3|Observe the displayed content on the Availability page.|The page (or calendar area) should display a user-friendly error message (e.g., "Could not load availability data. Please try again later."). The page should not crash or show technical error details.|

### 2.4 Test TC_V_004 for Module : <View Pricing Panel (UC004)>

This test contains the following test cases:

- **TC_V_004_01:** Verify successful display of configured pricing tiers and package details.

- **TC_V_004_02:** Verify system behavior when no specific pricing is configured (e.g., "Contact for Quote").

- **TC_V_004_03:** Verify display of interactive pricing elements if designed (e.g., price updates based on selections - conceptual).

#### 2.4.1 TC_V_004_01: Verify successful display of configured pricing tiers and package details.

**Test Case ID:** TC_V_004_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that when a visitor navigates to the pricing page, all active and configured pricing tiers, package names, base prices, and descriptions are displayed accurately.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Pricing" page.|
|2|Several pricing tiers/packages (e.g., "Standard Wedding Package," "VIP Package") have been configured with details (name, price, description, isActive=TRUE) in the Pricing Tier table by the Administrator.|2|Database State: Pricing Tier table contains multiple records.<br><br>Example:<br><br>Tier1: Name="Standard", Price=1000.00, Description="Basic setup", isActive=TRUE.<br><br>Tier2: Name="Premium", Price=2000.00, Description="Full service", isActive=TRUE.<br><br>Tier3: Name="Old Package", Price=500.00, isActive=FALSE.|
|3|At least one pricing tier is marked as inactive (isActive=FALSE).|||

**Test Conditions:** Ensure data includes various details like different price points and descriptions.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Pricing" or "Packages" (etc., "الأسعار").|The browser navigates to the Pricing Information page.|
|3|Observe the displayed content on the Pricing Information page.|The page should display all active pricing tiers/packages. For each active tier, its name, base price, and description should be clearly visible and match the database records. Inactive tiers should not be displayed on the public pricing page.|

#### 2.4.2 TC_V_004_02: Verify system behavior when no specific pricing is configured.

**Test Case ID:** TC_V_004_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the system displays an appropriate message or a "Contact for Quote" prompt if no active pricing tiers are configured by the administrator.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Pricing" page.|
|2|The Pricing Tier table in the database is empty, or all existing tiers are marked as inactive (isActive=FALSE).|2|Database State: PricingTier table is empty OR all records have isActive set to FALSE.|

**Test Conditions:** Ensure data includes various details like different price points and descriptions.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Click on the navigation link for "Pricing" or "Packages".|The browser navigates to the Pricing Information page.|
|3|Observe the displayed content on the Pricing Information page.|The page should display a user-friendly message indicating that specific pricing is not listed and to contact the hall for a custom quote (e.g., "Please contact us for current pricing and custom packages.").|

#### 2.4.3 TC_V_004_03: Verify display of interactive pricing elements if designed (conceptual).

**Test Case ID:** TC_V_004_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that if the pricing page is designed with interactive elements (e.g., selecting optional add-on services), the estimated price updates dynamically and correctly based on user selections. (This is a conceptual test case as complex interactive pricing might be out of initial scope; if only static display, this TC might be simplified or N/A).

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: User interacts with checkboxes, dropdowns, or quantity fields on the pricing page.|
|2|The pricing page is designed with interactive elements (e.g., checkboxes for add-on services, quantity selectors) that affect the total price. Base prices and add-on prices are configured.|2|System Configuration: Predefined prices for base packages and each selectable add-on service.<br><br>Example: Base Package = 1000; Add-on A = 200; Add-on B = 150.|

**Test Conditions:** Applicable only if the design includes dynamic price calculation based on user choices on the page.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the Pricing page (as per TC_V_004_01).|Pricing page with interactive elements is displayed. An initial base price or total estimated price is shown.|
|2|Select an optional add-on service (e.g., check a box for "Premium Decoration").|The displayed total estimated price should update immediately and accurately reflect the cost of the base package plus the selected add-on service.|
|3|Deselect the add-on service.|The displayed total estimated price should revert to the previous value (base package price).|
|4|If quantity selectors are present (e.g., for extra number of chairs), change the quantity.|The total estimated price should update based on the new quantity and per-item cost.|

### 2.5 Test TC_V_005 for Module : <Submit Booking Inquiry (UC005)>

This test contains the following test cases:

- **TC_V_005_01:** Verify successful submission of a booking inquiry with all valid required and optional data.

- **TC_V_005_02:** Verify system behavior when submitting an inquiry with missing required fields.

- **TC_V_005_03:** Verify system behavior when submitting an inquiry with invalid data formats (e.g., incorrect email, phone format).

#### 2.5.1 TC_V_005_01: Verify successful submission of a booking inquiry with all valid required and optional data.

**Test Case ID:** TC_V_005_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that a visitor can successfully fill out and submit the booking inquiry form with all fields (required and optional) correctly populated, and the inquiry is recorded in the system and admin is notified.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input:<br><br>- Full Name: "Test Visitor Name"<br><br>- Contact Number: "+967 XXX XXXXXX" (Valid Yemeni format)<br><br>- Email: "test.visitor@example.com" (Valid email format)<br><br>- Event Date: A future date (e.g., "2025-12-15")<br><br>- Event Type: "Wedding"<br><br>- Number of Guests: 150<br><br>- Message: "Interested in booking for a wedding ceremony."|
|2|The booking inquiry form is available on the portal.|2|Database State (Post-Test): A new record should exist in the BookingInquiry table matching the input data, with status "new".|
|3|Admin notification system is configured to receive alerts.|||

**Test Conditions:** Ensure all data entered is valid according to specified formats.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Open a web browser and navigate to the Al-Muneer Online Portal homepage.|Homepage loads successfully.|
|2|Navigate to the "Booking Inquiry" page.|The booking inquiry form is displayed.|
|3|Fill in all required fields (e.g., Full Name, Contact Number, Event Date, Event Type) and optional fields (e.g., Email, Number of Guests, Message) with valid data.|All fields accept valid input.|
|4|Click the "Submit Inquiry" button.|The system should process the form.<br><br>A success message (e.g., "Your inquiry has been successfully submitted. We will contact you shortly.") should be displayed to the visitor.<br><br>The inquiry data should be saved to the BookingInquiry table in the database.|

#### 2.5.2 TC_V_005_02: Verify system behavior when submitting an inquiry with missing required fields.

**Test Case ID:** TC_V_005_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that the system performs validation and prevents submission if mandatory fields in the booking inquiry form are left empty, displaying appropriate error messages.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input (Scenario 1):<br><br>Full Name: (empty)<br><br>Contact Number: "+967 XXX XXXXXX"<br><br>Event Date: "2025-12-15"|
|2|The booking inquiry form is displayed, with fields like "Full Name" and "Contact Number" marked as required.|2|Input (Scenario 2):<br><br>Full Name: (empty)<br><br>Contact Number: (empty)<br><br>Event Date: "2025-12-15"|

**Test Conditions:** Test by leaving one required field empty, then multiple required fields empty.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Booking Inquiry" page.|The booking inquiry form is displayed.|
|2|Fill in some optional fields but leave a required field (e.g., "Full Name") empty.|The form should not submit.<br><br>An error message should be displayed next to or near the "Full Name" field (e.g., "Full Name is required.").<br><br>The data entered in other fields should persist.<br><br>No inquiry record should be created in the database.|
|3|Click the "Submit Inquiry" button.|(Action covered in expected result of step 2)|
|4|Now leave both "Full Name" and "Contact Number" empty and click "Submit Inquiry".|The form should not submit.<br><br>Error messages should appear for both "Full Name" and "Contact Number."|

#### 2.5.3 TC_V_005_03: Verify system behavior when submitting an inquiry with invalid data formats.

**Test Case ID:** TC_V_005_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the system validates data formats for fields like contact number or email (if provided) and displays appropriate error messages if the format is incorrect.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input (Scenario 1 - Invalid Contact):<br><br>Full Name: "Valid Name"<br><br>Contact Number: "InvalidPhone123"<br><br>Event Date: "2025-12-15"|
|2|The booking inquiry form is displayed. Validation rules for contact number/email formats are implemented.|2|Input (Scenario 2 - Invalid Email):<br><br>Full Name: "Valid Name"<br><br>Contact Number: "+967 XXX XXXXXX"<br><br>Email: "invalid-email"<br><br>Event Date: "2025-12-15"|

**Test Conditions:** Ensure all data entered is valid according to specified formats.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Booking Inquiry" page.|Homepage loads successfully.|
|2|Fill in all required fields correctly, but enter an invalid contact number (e.g., "abcdef" or "123").||
|3|Click the "Submit Inquiry" button.|The form should not submit.<br><br>An error message should be displayed next to the "Contact Number" field (e.g., "Please enter a valid phone number.").<br><br>No inquiry record created.|
|4|Correct the contact number. Now enter an invalid email address in the optional email field (e.g., "test@domain" or "test.com").||
|5|Click the "Submit Inquiry" button.|The form should not submit.<br><br>An error message should be displayed next to the "Email" field (e.g., "Please enter a valid email address.").|

### 2.6 Test TC_A_006 for Module : <Manage Hall Information (UC006)>

This test contains the following test cases:

- **TC_A_006_01:** Verify successful update of existing hall information.

- **TC_A_006_02:** Verify input validation for managing hall information (e.g., preventing empty essential content if applicable).

#### 2.6.1 TC_A_006_01: Verify successful update of existing hall information.

**Test Case ID:** TC_A_006_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that the administrator can successfully modify and save changes to the hall's descriptive content (e.g., main description, services list, contact details, FAQs).

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin modifies text in description field to "New Updated Hall Description Test".|
|2|Administrator is successfully logged into the admin panel (as per TC_A_006_01).|2|Database State (Initial): VenueInfo table has existing data, e.g., description="Old Hall Description".|
|3|Existing hall information is present in the VenueInfo table (or equivalent content management section).|3|Database State (Post-Test): VenueInfo table description field should now contain "New Updated Hall Description Test".|

**Test Conditions:** Test updating different fields (e.g., description, then contact info).

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Venue Information" or "Content Management" section within the admin panel.|The current hall information is displayed in editable fields/text areas.|
|2|Modify the content of one or more fields (e.g., update the main hall description with new text "New Updated Hall Description Test").|The changes are reflected in the input fields.|
|3|Click the "Save Changes" or "Update Information" button.|The system should process the update.<br><br>A success message (e.g., "Venue information updated successfully.") should be displayed.<br><br>The VenueInfo table in the database should reflect the "New Updated Hall Description Test" and any other changes.<br><br>The public-facing venue information page should display the updated content.|

#### 2.6.2 TC_A_006_02: Verify input validation for managing hall information.

**Test Case ID:** TC_A_006_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that if certain content fields (e.g., main description, contact number) are considered essential and have validation (e.g., not empty), the system prevents saving empty or invalid data and provides appropriate error messages.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin clears the "Hall Description" text area.|
|2|Administrator is logged into the admin panel.|2|System Validation Rule: The 'description' field in VenueInfo is configured with server-side "not empty" validation.|
|3|The system has defined validation rules for certain essential hall information fields (e.g., 'description' cannot be empty).|||

**Test Conditions:** Test by attempting to save after clearing a field that has a "not empty" validation rule.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Venue Information" or "Content Management" section.|Current hall information is displayed in editable fields.|
|2|Clear the content of an essential field (e.g., delete all text from the "Hall Description" field, assuming it's validated as non-empty).|The input field for "Hall Description" is now empty.|
|3|Click the "Save Changes" or "Update Information" button.|The system should prevent the update.<br><br>An error message should be displayed indicating that the "Hall Description" (or the specific validated field) cannot be empty.<br><br>The database should not be updated with the empty value. The previously existing data should remain.|

### 2.7 Test TC_A_007 for Module : <Manage Media Gallery (UC007)>

This test contains the following test cases:

- **TC_A_007_01:** Verify successful upload of a new valid media item (image).

- **TC_A_007_02:** Verify successful deletion of an existing media item.

- **TC_A_007_03:** Verify system behavior when attempting to upload an invalid file type or oversized file.

#### 2.7.1 TC_A_007_01: Verify successful upload of a new valid media item (image).

**Test Case ID:** TC_A_007_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can successfully upload a new image file (e.g., JPG, PNG) to the media gallery, with an optional caption, and it appears in the gallery list and on the public site.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input:<br><br>File: "hall_image_1.jpg" (valid image, e.g., 800KB, JPG format)<br><br>Caption: "Main Hall View"|
|2|Administrator is logged into the admin panel.|2|Database State (Post-Test): A new record in MediaItem table with details of "hall_image_1.jpg", its server path, type "image", and caption.|
|3|A valid image file (e.g., "hall_image_1.jpg", within size limits) is available on the administrator's local machine.|||

**Test Conditions:** Test with a common image format like JPG.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Media Gallery Management" section in the admin panel.|The existing media gallery items (if any) are displayed. An option to "Upload New Media" is visible.|
|2|Click the "Upload New Media" button/link.|A file upload form/dialog appears, allowing file selection and an input field for an optional caption.|
|3|Select the valid image file (e.g., "hall_image_1.jpg") and optionally enter a caption (e.g., "Main Hall View").|The file name appears in the selection field, and caption is entered.|
|4|Click the "Upload" or "Save" button.|The system should process the upload.<br><br>A success message (e.g., "Media uploaded successfully.") should be displayed.<br><br>The new image (with caption, if provided) should be added to the MediaItem table in the database.<br><br>The uploaded image should appear in the admin gallery management list.<br><br>The new image should be visible on the public-facing media gallery page.|

#### 2.7.2 TC_A_007_02: Verify successful deletion of an existing media item.

**Test Case ID:** TC_A_007_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can successfully select and delete an existing media item from the gallery, and it is removed from the system (database and file storage) and the public site.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin selects a specific existing media item (e.g., mediaId=5) for deletion and confirms.|
|2|Administrator is logged into the admin panel.|2|Database State (Initial): Record with mediaId=5 exists in MediaItem. Corresponding file exists.|
|3|At least one media item exists in the gallery and MediaItem table, with corresponding file in storage.|3|Database State (Post-Test): Record with mediaId=5 is removed from MediaItem. Corresponding file is deleted.|

**Test Conditions:** Ensure the item to be deleted is clearly identifiable.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Media Gallery Management" section in the admin panel.|The list of existing media gallery items is displayed.|
|2|Identify a specific media item to delete and click its "Delete" icon/button.|A confirmation prompt/dialog should appear (e.g., "Are you sure you want to delete this item?").|
|3|Confirm the deletion (e.g., click "Yes" or "Confirm Delete").|The system should process the deletion.<br><br>A success message (e.g., "Media item deleted successfully.") should be displayed.<br><br>The record for the item should be removed from the MediaItem table.<br><br>The physical media file should be deleted from server storage.<br><br>The item should no longer appear in the admin gallery list or on the public gallery page.|

#### 2.7.3 TC_A_007_03: Verify system behavior when attempting to upload an invalid file type or oversized file.

**Test Case ID:** TC_A_007_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the system validates uploaded files and rejects those that are not of an accepted type (e.g., not JPG/PNG/MP4) or exceed the defined maximum file size, displaying an appropriate error.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input (Scenario A): File "document.pdf"|
|2|Administrator is logged into the admin panel.|2|Input (Scenario B): File="large_image.jpg" (10MB, assuming 5MB limit for images)|
|3|System has defined validation rules for accepted file types (e.g., JPG, PNG for images; MP4 for videos) and a maximum file size (e.g., 5MB for images, 50MB for videos).|3|System Config: Accepted image types: JPG, PNG. Max image size: 5MB.<br><br>Accepted video types: MP4. Max video size: 50MB.|
|4|An invalid file (e.g., "document.pdf") and an oversized valid file (e.g., "large_image.jpg" > 5MB) are available.|||

**Test Conditions:** Test both invalid file type and oversized file scenarios.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to "Media Gallery Management" and click "Upload New Media".|File upload form is displayed.|
|2|Scenario A (Invalid Type): Select an invalid file type (e.g., "document.pdf"). Click "Upload".|The system should reject the upload.<br><br>An error message should be displayed (e.g., "Invalid file type. Please upload JPG, PNG, or MP4 files.").<br><br>No file should be uploaded, and no database record created.|
|3|Scenario B (Oversized File): Select a valid file type that exceeds the size limit (e.g., "large_image.jpg" which is 10MB, limit is 5MB). Click "Upload".|The system should reject the upload.<br><br>An error message should be displayed (e.g., "File size exceeds the maximum limit of 5MB.").<br><br>No file should be uploaded, and no database record created.|

### 2.8 Test TC_A_008 for Module : <Manage Pricing Panel (UC008)>

This test contains the following test cases:

- **TC_A_008_01:** Verify successful creation of a new pricing tier with valid data.

- **TC_A_008_02:** Verify successful update of an existing pricing tier.

- **TC_A_008_03:** Verify input validation when creating/updating a pricing tier (e.g., empty required field, invalid price format).

#### 2.8.1 TC_A_008_01: Verify successful creation of a new pricing tier with valid data.

**Test Case ID:** TC_A_008_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can successfully add a new pricing tier/package with all required details (name, price, description), and it becomes active and visible where appropriate.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input:<br><br>Tier Name: "Gold Package"<br><br>Base Price: "1500.00"<br><br>Description: "Includes premium catering and decoration"<br><br>IsActive: TRUE|
|2|Administrator is logged into the admin panel.|2|Database State (Post-Test): A new record in PricingTier table with the input details.|

**Test Conditions:** Use valid data for all fields of the new pricing tier.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Pricing Management" section in the admin panel.|The list of existing pricing tiers (if any) is displayed. An option to "Add New Tier" is visible.|
|2|Click the "Add New Tier" button/link.|A form for entering new pricing tier details (Tier Name, Base Price, Description, IsActive status) is displayed.|
|3|Fill in all fields with valid data (e.g., Tier Name: "Gold Package", Base Price: "1500.00", Description: "Includes premium catering", IsActive: TRUE).|Data is entered into the form fields.|
|4|Click the "Save" or "Create Tier" button.|The system should process the creation.<br><br>A success message (e.g., "Pricing tier created successfully.") should be displayed.<br><br>The new pricing tier should be added to the PricingTier table in the database.<br><br>The new tier should appear in the admin list of pricing tiers.<br><br>If active, it should be visible on the public pricing page.|

#### 2.8.2 TC_A_008_02: Verify successful update of an existing pricing tier.

**Test Case ID:** TC_A_008_02

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can select an existing pricing tier, modify its details (e.g., price, description, active status), and save the changes successfully.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: For existing "Gold Package" (Price: 1500.00, IsActive: TRUE):<br><br>New Base Price: "1600.00"<br><br>New Description: "Updated premium catering..."<br><br>New IsActive: FALSE|
|2|Administrator is logged into the admin panel.|2|Database State (Initial): Record for "Gold Package" exists with old details.|
|3|At least one pricing tier exists in the Pricing Tier table (e.g., created via TC_A_008_01).|3|Database State (Post-Test): Record for "Gold Package" is updated with the new details.|

**Test Conditions:** Test updating multiple fields of an existing tier.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Pricing Management" section in the admin panel.|The list of existing pricing tiers is displayed.|
|2|Select an existing pricing tier (e.g., "Gold Package") and click its "Edit" option.|A form is displayed, pre-filled with the current details of the "Gold Package".|
|3|Modify one or more fields (e.g., change Base Price to "1600.00", update Description to "Updated premium catering and enhanced decoration", set IsActive to FALSE).|Changes are reflected in the form fields.|
|4|Click the "Update" or "Save Changes" button.|The system should process the update.<br><br>A success message (e.g., "Pricing tier updated successfully.") should be displayed.<br><br>The PricingTier table in the database should reflect the new price, description, and inactive status for "Gold Package".<br><br>The tier, being inactive, should no longer appear on the public pricing page (or appear differently if inactive items are shown to admin).|

#### 2.8.3 TC_A_008_03: Verify input validation when creating/updating a pricing tier.

**Test Case ID:** TC_A_008_03

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that the system validates input when an administrator creates or updates a pricing tier, preventing submission if required fields (e.g., Tier Name, Base Price) are empty or if data is in an invalid format (e.g., non-numeric price).

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input (Scenario A): Tier Name="", Base Price="1000", Description="Test"|
|2|Administrator is logged into the admin panel.|2|Input (Scenario B): Tier Name="Test Tier", Base Price="abc", Description="Test"|
|3|Validation rules are implemented for pricing tier fields (e.g., Tier Name not empty, Base Price is a positive number).|3|Input (Editing): Select existing tier, clear Base Price field.|

**Test Conditions:** Test scenarios: empty Tier Name, empty Base Price, non-numeric Base Price.

|            |                                                                                                                                           |                                                                                                                                                                                                                      |
| ---------- | ----------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Step #** | **Step Details**                                                                                                                          | **Expected Result**                                                                                                                                                                                                  |
| 1          | Navigate to "Pricing Management" and click "Add New Tier".                                                                                | New pricing tier form is displayed.                                                                                                                                                                                  |
| 2          | Scenario A (Empty Name): Leave "Tier Name" empty, fill other fields validly. Click "Save".                                                | Form should not submit.<br><br>Error message for "Tier Name" (e.g., "Tier Name cannot be empty.").<br><br>No new record in Pricing Tier table.                                                   |
| 3          | Scenario B (Invalid Price): Enter a valid "Tier Name", but for "Base Price" enter non-numeric text (e.g., "Free" or "abc"). Click "Save". | Form should not submit.<br><br>Error message for "Base Price" (e.g., "Base Price must be a valid number.").<br><br>No new record in Pricing Tier table.                                          |
| 4          | Select an existing tier for editing. Clear its "Base Price" field. Click "Update".                                                        | Form should not submit.<br><br>Error message for "Base Price" (e.g., "Base Price cannot be empty.").<br><br>The existing record in PricingTier table should not be modified with an empty price. |

### 2.9 Test TC_A_009 for Module : <Manage Calendar & Inquiries (UC009)>

This test contains the following test cases:

- **TC_A_009_01:** Verify successful manual update of a date's status on the availability calendar.

#### 2.9.1 TC_A_009_01: Verify successful manual update of a date's status on the availability calendar.

**Test Case ID:** TC_A_009_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can select a specific date on the admin calendar, change its status (e.g., from "available" to "booked" or "pending_confirmation"), and the change is saved and reflected on both admin and public calendars.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin selects date "2025-11-10" and changes its status to "booked".|
|2|Administrator is logged into the admin panel.|2|Database State (Initial): AvailabilitySlot record for "2025-11-10" has status "available".|
|3|The "Calendar Management" interface is accessible. A specific date (e.g., "2025-11-10") is currently marked as "available" in the AvailabilitySlot table.|3|Database State (Post-Test): AvailabilitySlot record for "2025-11-10" has status "booked".|

**Test Conditions:** Ensure the date selected for update is clearly identifiable.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Calendar Management" section in the admin panel.|The interactive admin calendar is displayed, showing current date statuses. The target date (e.g., "2025-11-10") shows as "available".|
|2|Select the target date (e.g., "2025-11-10") on the calendar.|The date is selected/highlighted.|
|3|Choose a new status for the selected date, for example, "booked".|The new status ("booked") is selected for the date.|
|4|Click the "Save Change" or "Update Status" button.|The system should process the update.<br><br>A success message (e.g., "Calendar status updated successfully.") should be displayed.<br><br>The AvailabilitySlot table in the database should now show "2025-11-10" with status "booked".<br><br>The admin calendar view should refresh to show the new status.<br><br>The public-facing availability calendar should also reflect that "2025-11-10" is now "booked".|

### 2.10 Test TC_A_010 for Module : <View Traffic Analytics (UC010)>

This test contains the following test cases:

#### 2.10.1 TC_A_010_01: Verify viewing traffic analytics data or placeholder.

**Test Case ID:** TC_A_010_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure that the administrator can access the analytics section and view either very basic logged traffic data (e.g., page visit counts) or a placeholder message indicating the current status/scope of the analytics feature.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Navigation to the "Analytics" page by the admin.|
|2|Administrator is logged into the admin panel.|2|System State (Scenario A - Data Exists): Simple log files or database table contains some visit count data.|
|3|The "Analytics" or "Site Statistics" section is implemented, either with simple logging capability or as a placeholder.|3|System State (Scenario B - No Data/Placeholder): No visit count data logged, or the section is a designed placeholder.|

**Test Conditions:** Test when some basic data might have been logged (if implemented) and when no data is available.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Analytics" or "Site Statistics" section in the admin panel.|The browser navigates to the Analytics page.|
|2|Observe the content displayed on the Analytics page.|The page should display either:<br><br>a) Basic logged data (e.g., "Homepage Visits: 150", "Gallery Views: 80") if simple logging is active and data exists.<br><br>OR<br><br>b) A message indicating "No traffic data available yet" if logging is active but no data collected.<br><br>OR<br><br>c) A placeholder message like "Basic analytics view. Advanced analytics planned for future development." if the feature is largely conceptual at this stage.<br><br>The page should not show errors.|

### 2.11 Test TC_V_011 for Module : <Submit Payment Proof (UC011)>

This test contains the following test cases:

- **TC_V_011_01:** Verify successful submission of a valid payment proof file.

#### 2.11.1 TC_V_011_01: Verify successful submission of a valid payment proof file.

**Test Case ID:** TC_V_011_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure a visitor can successfully upload an accepted image file (e.g., JPG/PNG, within size limits) as payment proof, associate it with an inquiry (if applicable), and receive confirmation.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input:<br><br>File: "transfer_receipt.jpg" (valid image, e.g., 1MB, JPG format)<br><br>Inquiry ID (optional): "INQ12345"|
|2|Visitor has access to the "Submit Payment Proof" page/form.|2|Database State (Post-Test): New record in PaymentProof table linked to "INQ12345" (if provided), with details of "transfer_receipt.jpg".|
|3|Visitor has a valid payment proof image file (e.g., "transfer_receipt.jpg") meeting system criteria (type, size).|||
|4|(Optional) An Inquiry ID exists for the visitor to associate the proof with.|||

**Test Conditions:** Test with a common valid image format and size.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Submit Payment Proof" page.|The payment proof upload form is displayed, including a file chooser and an optional field for Inquiry ID or notes.|
|2|(If applicable) Enter a valid Inquiry ID.|Inquiry ID is accepted.|
|3|Click the "Choose File" button and select the valid payment proof image file (e.g., "transfer_receipt.jpg").|The selected file name appears in the form.|
|4|Click the "Upload Proof" or "Submit" button.|The system should process the file upload.<br><br>A success message (e.g., "Payment proof submitted successfully. We will verify it shortly.") should be displayed.<br><br>The file should be uploaded to server storage.<br><br>A record for the payment proof (linking to Inquiry ID if provided, file path, timestamp) should be created in the PaymentProof table.<br><br>Admin should be notified (if configured).|

### 2.12 Test TC_V_012 for Module : <Submit Feedback (UC012)>

This test contains the following test cases:

- **TC_V_012_01:** Verify successful submission of feedback with optional contact details.

#### 2.12.1 TC_V_012_01: Verify successful submission of feedback with optional contact details.

**Test Case ID:** TC_V_012_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure a visitor can successfully submit feedback text, optionally including their name and contact details, and receive confirmation.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input (Scenario A): Name="Feedback User", Contact="user@example.com", Message="Great service!", Rating=5|
|2|The "Feedback" page/form is accessible to visitors.|2|Input (Scenario B): Name="", Contact="", Message="Portal is easy to use.", Rating-(not selected)|
|3||3|Database State (Post-Test): Two new records in Feedback table corresponding to the inputs.|

**Test Conditions:** Test one scenario with contact details provided, and another without (anonymous).

|            |                                                                                                                                                                           |                                                                                                                                                                                                                                                                 |
| ---------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Step #** | **Step Details**                                                                                                                                                          | **Expected Result**                                                                                                                                                                                                                                             |
| 1          | Navigate to the "Feedback" page.                                                                                                                                          | The feedback form is displayed with fields for Name (optional), Contact (optional), Feedback Message (required), and Rating (optional).                                                                                                                         |
| 2          | Scenario A (With Details): Fill in Name ("Feedback User"), Contact ("user@example.com"), Feedback Message ("Great service!"), and select a Rating (e.g., 5 stars).        | Data is entered.                                                                                                                                                                                                                                                |
| 3          | Click the "Submit Feedback" button.                                                                                                                                       | System processes the submission.<br><br>A success message (e.g., "Thank you for your feedback!") is displayed.<br><br>Feedback data (including details from Step 2) is saved to the Feedback table.<br><br>Admin may be notified. |
| 4          | Scenario B (Anonymous): Navigate to the feedback page again. Leave Name and Contact blank. Enter Feedback Message ("Portal is easy to use.") and click "Submit Feedback". | System processes the submission.<br><br>A success message is displayed.<br><br>Feedback data (message only, name/contact blank) is saved.                                                                                                   |

### 2.13 Test TC_A_013 for Module : <Manage Payment Status (UC013)>

This test contains the following test cases:

- **TC_A_013_01:** Verify successful update of a payment proof status to "Verified".

#### 2.13.1 TC_A_013_01: Verify successful update of a payment proof status to "Verified".

**Test Case ID:** TC_A_013_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can view a submitted payment proof, verify it (offline), and update its status in the system to "Verified", potentially triggering a client notification.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin selects payment proof "PP789" and updates its status to "Verified". Admin Note: "Payment confirmed via local transfer."|
|2|Administrator is logged into the admin panel.|2|Database State (Initial): PaymentProof record "PP789" has verificationStatus="pending". BookingInquiry "INQ12345" has status="payment_pending".|
|3|At least one payment proof has been submitted by a visitor and is in "pending" verification status in the PaymentProof table (e.g., proofId="PP789" linked to inquiryId="INQ12345"). The image file for the proof exists.|3|Database State (Post-Test): PaymentProof "PP789" verificationStatus="Verified", adminNotes updated. BookingInquiry "INQ12345" status="Confirmed".|

**Test Conditions:** Ensure the payment proof is clearly identifiable for the admin.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Payment Proofs" or "Booking Management" section in the admin panel.|A list of submitted payment proofs is displayed, showing "PP789" with status "pending".|
|2|Select/click on the payment proof "PP789" to view its details.|Details of "PP789" are displayed, including a link/view of the uploaded image.|
|3|Admin views the image and performs offline verification of the payment. (Assumed successful for this test case).|(Offline action)|
|4|In the admin panel, change the verification status for "PP789" to "Verified". Optionally add an admin note.|The status selection is made.|
|5|Click the "Save Status" or "Update" button.|System processes the update.<br><br>A success message (e.g., "Payment status updated successfully.") is displayed.<br><br>The status of "PP789" in the PaymentProof table is updated to "Verified".<br><br>The status of the linked BookingInquiry "INQ12345" may be updated to "Confirmed".<br><br>A notification may be triggered to the visitor about payment verification.|

### 2.14 Test TC_A_014 for Module : <View/Generate Reports (UC014)>

This test contains the following test cases:

- **TC_A_014_01:** Verify successful generation and display of an "Inquiry Summary" report for a given date range.

#### 2.14.1 TC_A_014_01: Verify successful generation and display of an "Inquiry Summary" report for a given date range.

**Test Case ID:** TC_A_014_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can select the "Inquiry Summary" report type, specify a date range, and the system generates and displays a report showing relevant inquiry data.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Report Type="Inquiry Summary", Start Date="2025-05-01", End Date="2025-05-30".|
|2|Administrator is logged into the admin panel.|2|Database State: Multiple records in BookingInquiry table, with some submissionDate values falling between 2025-05-01 and 2025-05-30, and some outside this range.|
|3|Several booking inquiries exist in the BookingInquiry table with submission dates spanning across different days/weeks/months.|||

**Test Conditions:** Select a date range that is known to contain some inquiry records.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Reports" section in the admin panel.|A list of available report types is displayed (e.g., "Inquiry Summary," "Booking Status Report").|
|2|Select the "Inquiry Summary" report type.|Options for report parameters (e.g., "Start Date," "End Date") are displayed.|
|3|Enter a valid "Start Date" and "End Date" (e.g., "2025-05-01" to "2025-05-30").|Dates are entered.|
|4|Click the "Generate Report" button.|The system processes the request.<br><br>A report is displayed (e.g., in a table format on the page) showing a summary of inquiries submitted within the specified date range. The report should include relevant details like Inquiry ID, Visitor Name, Event Date, Status for each inquiry.<br><br>If no inquiries match the criteria, a "No data available" message should be shown.|

### 2.15 Test TC_A_015 for Module : <Manage Feedback (UC015)>

This test contains the following test cases:

- **TC_A_015_01:** Verify successful viewing of submitted feedback and updating its review status.

#### 2.15.1 TC_A_015_01: Verify successful viewing of submitted feedback and updating its review status.

**Test Case ID:** TC_A_015_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can view the details of submitted feedback, mark it as "Reviewed", and add internal notes if necessary.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin selects feedback "FB001", marks isReviewed=TRUE, Admin Note="Issue addressed."|
|2|Administrator is logged into the admin panel.|2|Database State (Initial): Feedback record "FB001" has isReviewed=FALSE, adminNotes=NULL.|
|3|At least one feedback entry exists in the Feedback table, with isReviewed status as FALSE (e.g., feedbackId="FB001").|3|Database State (Post-Test): Feedback record "FB001" has isReviewed=TRUE, adminNotes="Issue addressed."|

**Test Conditions:** Ensure the feedback entry is clearly identifiable.

|   |   |   |
|---|---|---|
|**Step #**|**Step Details**|**Expected Result**|
|1|Navigate to the "Feedback Management" section in the admin panel.|A list of submitted feedback entries is displayed, showing "FB001" with review status as "Not Reviewed" (or equivalent).|
|2|Select/click on the feedback entry "FB001" to view its full details.|The full details of feedback "FB001" are displayed (visitor name/contact if provided, message, rating, submission date). An option to mark as "Reviewed" and add notes is visible.|
|3|Mark the feedback "FB001" as "Reviewed". Optionally, add an internal admin note (e.g., "Issue addressed by contacting user.").|The "Reviewed" status is selected/checked. Notes are entered.|
|4|Click the "Save Changes" or "Update Feedback" button.|The system should process the update.<br><br>A success message (e.g., "Feedback status updated successfully.") should be displayed.<br><br>The isReviewed field for "FB001" in the Feedback table should be updated to TRUE.<br><br>Admin notes should be saved if entered.<br><br>The feedback list view should reflect the updated "Reviewed" status.|

### 2.16 Test TC_A_016 for Module : <Configure/Manage Notifications (UC016)>

This test contains the following test cases:

- **TC_A_016_01:** Verify successful update of a notification setting (e.g., enabling/disabling a specific notification type).

#### 2.16.1 TC_A_016_01: Verify successful update of a notification setting.

**Test Case ID:** TC_A_016_01

**Created by:** Ahmed Hani Ahmed Ghaleb | **Version:** 1.0

**Test Case Description:** To ensure the administrator can change a notification setting (e.g., toggle enabling/disabling notification for "New Inquiry") and the change is saved.

|   |   |   |   |
|---|---|---|---|
|**No.**|**Prerequisites**|**No.**|**Test Data**|
|1|The Al-Muneer Online Portal is deployed and accessible.|1|Input: Admin changes "Admin New Inquiry Notification" setting from "Enabled" to "Disabled".|
|2|Administrator is logged into the admin panel.|2|System Config (Initial): Setting for "Admin New Inquiry Notification" is "Enabled".|
|3|The "Notification Settings" interface is implemented, allowing toggling of specific notification events (e.g., "Admin New Inquiry Notification" is currently "Enabled").|3|System Config (Post-Test): Setting for "Admin New Inquiry Notification" is "Disabled".|

**Test Conditions:** Test changing a setting from enabled to disabled, or vice-versa.

|            |                                                                                                                                     |                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| ---------- | ----------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Step #** | **Step Details**                                                                                                                    | **Expected Result**                                                                                                                                                                                                                                                                                                                                                                                                                      |
| 1          | Navigate to the "Notification Settings" or "System Configuration" section in the admin panel.                                       | The current notification settings are displayed (e.g., "Admin New Inquiry Notification: Enabled").                                                                                                                                                                                                                                                                                                                                       |
| 2          | Change the setting for "Admin - New Inquiry Notification" from "Enabled" to "Disabled" (e.g., uncheck a checkbox, toggle a switch). | The UI reflects the change to "Disabled".                                                                                                                                                                                                                                                                                                                                                                                                |
| 3          | Click the "Save Configurations" or "Update Settings" button.                                                                        | The system should process the update.<br><br>A success message (e.g., "Notification settings updated successfully.") should be displayed.<br><br>The corresponding configuration (in database or config file) for "Admin New Inquiry Notification" should be updated to reflect "Disabled".<br><br>Subsequently, when a new inquiry is submitted, the admin should not receive this specific notification. |
