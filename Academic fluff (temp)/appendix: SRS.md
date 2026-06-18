# APPENDIX B - SRS

**UNIVERSITI TEKNOLOGI MALAYSIA** **FACULTY OF COMPUTING** **UTM Johor Bahru**

**SECJ 3032: Software Engineering PSM1** **Semester 01, 2024/2025**

# Software Requirements Specification (SRS)

**Al-Muneer Online Portal**

**Version 1.1**

**Prepared by:** Ahmed Ghaleb

## Revision Page

### a. Overview

This document is Version 1.0 of the Software Requirements Specification (SRS) for the Al-Muneer Online Portal. It details the functional and non-functional requirements of the system, defines the scope, outlines user characteristics, specifies system features through use cases, and sets the foundation for the system's design and development. This SRS is prepared as part of the SECJ 3032: Software Engineering PSM1 course requirements.

### b. Target Audience

The target audience for this SRS document includes:

- The project supervisor, for review and assessment of project scope and requirements.
    
- The student developer (Ahmed Hani Ahmed Ghaleb), as a guide for system design, development, and testing.
    
- Examiners, for evaluation purposes.
    
- The project stakeholder (Mr. Ahmed Almunajid), for confirmation that the documented requirements accurately reflect his needs for the Al-Muneer Online Portal.
    

### c. Version Control History

|   |   |   |   |
|---|---|---|---|
|**Version**|**Primary Author(s)**|**Description of Version**|**Date Completed**|
|1.0|Ahmed Hani Ahmed Ghaleb|Initial complete draft of SRS document for FYP1 Progress 2.|30/05/2025|
|1.1|Ahmed Hani Ahmed Ghaleb|Use case 6 "Admin Login" removed as instructed|06/08/2025|

_(Note: This Software Requirements Specification (SRS) template is adapted from IEEE Recommended Practice for Software Requirements Specification (SRS) (IEEE Std. 830-1998) that is simplified and customized to meet the need of SECJ3203 FYP1 SE course at Faculty of Computing, UTM. Examples of models are from Arlow and Neustadt (2002) and other sources stated accordingly. This template is tailored for SRS in a plan-driven software development approach.)_

## Table of Contents

1. **Introduction** (2) 1.1 Purpose (2) 1.2 Scope (2) 1.3 Definitions, Acronyms and Abbreviation (3) 1.4 References (4) 1.5 Overview (5)
    
2. **Specific Requirements** (5) 2.1 User characteristics (6) 2.2 System Features (8) 2.3 Use Case Details (11) 2.3.1 UC001: Use Case View Venue Information (11) 2.3.2 UC002: Use Case View Media Gallery (12) 2.3.3 UC003: Use Case Check Availability (14) 2.3.4 UC004: Use Case View Pricing Panel (17) 2.3.5 UC005: Use Case Submit Booking Inquiry (19) 2.3.6 UC006: Use Case Manage Hall Information (21) 2.3.7 UC007: Use Case Manage Media Gallery (24) 2.3.8 UC008: Use Case Manage Pricing Panel (27) 2.3.9 UC009: Use Case Manage Calendar & Inquiries (29) 2.3.10 UC010: Use Case View Traffic Analytics (31) 2.3.11 UC011: Use Case Submit Payment Proof (32) 2.3.12 UC012: Use Case Submit Feedback (34) 2.3.13 UC013: Use Case Manage Payment Status (36) 2.3.14 UC014: Use Case View/Generate Reports (39) 2.3.15 UC015: Use Case Manage Feedback (40) 2.3.16 UC016: Use Case Configure/Manage Notifications (43) 2.4 Performance and Other Requirements (44) 2.5 Design Constraints (48) **Appendix A:** Evidence of Requirements Elicitation Artefacts (1)
    

## 1. Introduction

The following sections of the Software Requirements Specification (SRS) document provide the details of the entire document.

### 1.1 Purpose

This Software Requirements Specification (SRS) document delineates the requirements for the Al-Muneer Online Portal. Its purpose is to provide a comprehensive and unambiguous description of the system's functionalities, capabilities, constraints, and interfaces. This SRS serves as the foundational agreement between the stakeholder, the developer, and academic supervisors regarding what the Al-Muneer Online Portal will achieve. It is intended to guide the design, development, testing, and eventual deployment of the system.

This SRS describes the features and behavior of the Al-Muneer Online Portal from an external perspective, focusing on what the system does rather than how it does it. It is intended for use by the project developer (Ahmed Hani Ahmed Ghaleb) as the primary guide for system development, by the project supervisor (Dr. Muhammad Luqman bin Mohd Shafie) for monitoring progress and adherence to objectives, and by the stakeholder (Mr. Ahmed Almunajid) to ensure the final product aligns with his business needs for Al-Muneer Hall.

### 1.2 Scope

The software product to be produced is the "Al-Muneer Online Portal," a web-based application designed to facilitate online inquiry, information dissemination, and operational management for Al-Muneer Hall for Weddings and Events, located in Ibb, Yemen.

**The Al-Muneer Online Portal will:**

- Provide a public-facing interface for visitors to view detailed venue information, browse a media gallery, check event availability via an interactive calendar, view pricing tiers, read FAQs, submit booking inquiries, optionally upload proof of payment (e.g., transfer screenshots), and provide feedback.
    
- Provide a secure administrator panel for the venue owner to manage all content (venue details, media, FAQs, pricing), manage the availability calendar, view and manage booking inquiries, view and update payment proof statuses, review user feedback, generate basic operational reports, and configure system notifications (WhatsApp focused).
    

**The Al-Muneer Online Portal will not:**

- Process direct online payments or integrate with third-party payment gateways or banks. Payment confirmation will be based on uploaded proof and manual verification.
    
- Integrate with external calendar systems (e.g., Google Calendar).
    
- Include a live chat feature for real-time communication; standard contact information (phone/WhatsApp) will be provided.
    
- Offer advanced event management or Customer Relationship Management (CRM) functionalities beyond the scope defined.
    
- Provide advanced, customizable analytics; reporting will be basic.
    
- Support multiple languages beyond Arabic and potentially basic English translations for navigation, unless explicitly expanded later.
    

The application of the software is to modernize and streamline the current manual processes of Al-Muneer Hall. The relevant benefits include increased efficiency for the owner by reducing repetitive inquiries, improved customer experience through 24/7 access to comprehensive information, enhanced marketing presence, simplified booking confirmation processes suitable for the local context, and a structured way to gather feedback for service improvement. The primary objective is to create a user-friendly, informative, and culturally appropriate online presence that supports the hall's business operations. This scope is consistent with the project proposal and subsequent discussions with the stakeholder.

The software product is a custom-developed web application, encompassing both frontend (user interface for visitors and admin) and backend (server-side logic and database interaction) components.

### 1.3 Definitions, Acronyms and Abbreviation

Definitions of all terms, acronyms and abbreviation used are to be defined here.

- **Admin:** Administrator; refers to the owner or authorized manager of Al-Muneer Hall who has privileged access to the system's management features.
    
- **API:** Application Programming Interface.
    
- **CGPA:** Cumulative Grade Point Average.
    
- **CRM:** Customer Relationship Management.
    
- **CRUD:** Create, Read, Update, Delete - basic data operations.
    
- **CSS:** Cascading Style Sheets.
    
- **DB:** Database.
    
- **ERD:** Entity-Relationship Diagram.
    
- **FAQ:** Frequently Asked Questions.
    
- **FR:** Functional Requirement.
    
- **FYP:** Final Year Project.
    
- **HTML:** HyperText Markup Language.
    
- **HTTP:** Hypertext Transfer Protocol.
    
- **HTTPS:** Hypertext Transfer Protocol Secure.
    
- **JS:** JavaScript.
    
- **MVC:** Model-View-Controller - a software architectural pattern.
    
- **NFR:** Non-Functional Requirement.
    
- **PaaS:** Platform as a Service.
    
- **PK:** Primary Key.
    
- **FK:** Foreign Key.
    
- **PSM:** Projek Sarjana Muda (Bachelor's Degree Project).
    
- **SDD:** System Design Document.
    
- **SDLC:** Software Development Life Cycle.
    
- **SRS:** Software Requirements Specification.
    
- **SSL/TLS:** Secure Sockets Layer/Transport Layer Security cryptographic protocols for secure communication.
    
- **STD:** Software Test Document.
    
- **UC:** Use Case.
    
- **UI:** User Interface.
    
- **UX:** User Experience.
    
- **UTM:** Universiti Teknologi Malaysia.
    
- **VPS:** Virtual Private Server.
    
- **Visitor:** A potential client or any member of the public accessing the portal's informational content.
    

### 1.4 References

1. Al-Muneer Online Portal - Project Proposal & Supervision Consent Form (PSM1.PF_.05u-1). Version 1.0. March 2025. (Internal Document)
    
2. Al-Muneer Online Portal - NABC Supplement Document (PSM1.PF_.05u-1 - NABC Supplement Document v2.1). Version 2.1. March 2025. (Internal Document)
    
3. Al-Muneer Online Portal - FYP1 Progress 1 Report. Ahmed Hani Ahmed Ghaleb. May 2025. (Internal Document)
    
4. Al-Muneer Online Portal - FYP1 Progress 2 Report (Chapters 3, 4, 5). Ahmed Hani Ahmed Ghaleb. May 2025. (Internal Document)
    
5. Stakeholder Communication Logs (Summary of Google Meet discussion on March 15, 2025, and subsequent WhatsApp clarifications with Mr. Ahmed Almunajid). (Internal Notes/Logs)
    
6. IEEE. (1998). IEEE Recommended Practice for Software Requirements Specifications (IEEE Std 830-1998). Institute of Electrical and Electronics Engineers.
    

Sources for these documents are primarily internal project documentation, academic course materials, and standard software engineering literature. Project-specific documents are maintained by the student developer.

### 1.5 Overview

This SRS document is organized into three main sections:

- **Section 1: Introduction:** Provides an overview of the SRS document itself, including its purpose, scope, definitions, references, and a summary of its organization.
    
- **Section 2: Specific Requirements:** Details all software requirements. This includes descriptions of user characteristics (Section 2.1), an overview of system features with a conceptual domain model and use case diagram (Section 2.2), detailed specifications for each use case including activity and sequence diagrams (Section 2.3), performance and other non-functional requirements (Section 2.4), and any design constraints (Section 2.5).
    
- **Section 3: Appendices:** Contains supporting information, such as evidence of requirements elicitation artefacts (Appendix A).
    

This organization is intended to provide a clear and structured presentation of the requirements for the Al-Muneer Online Portal, facilitating understanding and serving as a reliable basis for subsequent design and development efforts.

## 2. Specific Requirements

This section of the SRS contains all the software requirements to a level of detail sufficient to enable designers to design a system to satisfy those requirements, and testers to test that the system satisfies those requirements. Throughout this section, every stated requirement should be externally perceivable by users, operators, or other external systems. These requirements include at a minimum a description of every input (stimulus) into the system, every output (response) from the system, and all functions performed by the system in response to an input or in support of an output.

### 2.1 User characteristics

The Al-Muneer Online Portal is designed to be used by two main types of users, each with distinct characteristics and ways of interacting with the system: Visitors and the Administrator (Owner).

#### 2.1.1 Visitors

Visitors are potential clients or any member of the public seeking information about Al-Muneer Hall.

- **Technical Expertise:** Visitors are expected to have basic computer literacy and familiarity with using web browsers on various devices (desktops, smartphones, tablets). They may not possess advanced technical skills.
    
- **Domain Knowledge:** Visitors are looking for information about event venues, specifically for weddings or other occasions in Ibb, Yemen. They understand the general concept of booking a hall and are seeking details on availability, pricing, features, and visual appeal.
    
- **Language and Cultural Context:** The primary language for many visitors will be Arabic. The interface and information should be culturally appropriate for the Yemeni context, particularly considering how event planning (especially for weddings) is approached. An option for basic English navigation may be useful for a wider audience but is secondary.
    
- **Accessibility Needs:** While no specific advanced accessibility features are mandated beyond standard web responsiveness, the design should aim for clarity and ease of use for a general audience.
    
- **Motivation:** Their primary motivation is to gather sufficient information to evaluate Al-Muneer Hall for their event, check its availability, understand pricing, and initiate an inquiry or booking process if interested. They value convenience and quick access to comprehensive details. The ability to perform these tasks remotely is particularly important for some user segments (e.g., female family members involved in initial selection).
    

#### 2.1.2 Administrator (Owner)

The Administrator is the owner of Al-Muneer Hall (Mr. Ahmed Almunajid) or a designated staff member responsible for managing the portal's content and operations.

- **Technical Expertise:** The Administrator is expected to have moderate computer literacy, capable of using a web-based admin panel, filling forms, and uploading media. While not necessarily a technical expert, the admin interface should be intuitive enough for someone with general office software experience. Training on using the admin panel will be provided if necessary.
    
- **Domain Knowledge:** The Administrator has in-depth knowledge of Al-Muneer Hall's operations, booking procedures, pricing strategies, and client communication.
    
- **Language:** The Administrator is fluent in Arabic and may have a working knowledge of English. The admin panel should primarily be in Arabic for ease of use.
    
- **Motivation:** The Administrator's motivation is to efficiently manage the hall's online presence, reduce the time spent on manual inquiries, streamline the booking information process, manage payment confirmations, gather client feedback, and gain basic insights into operational trends. The system should empower them to manage these tasks with minimal external technical support.
    
- **Responsibilities:** The Administrator is responsible for keeping the portal's information (venue details, availability, pricing, gallery, FAQs) accurate and up-to-date, responding to inquiries, managing booking statuses, and overseeing the overall client interaction facilitated by the portal.
    

Both types of users will interact with the system via standard web browsers. Visitors will not need to register or log in to access public information but will provide contact details for inquiries. The Administrator will require secure login credentials to access the management functionalities.

### 2.2 System Features

The Al-Muneer Online Portal is designed as a web-based application to serve as the primary online interface for Al-Muneer Hall. Its product perspective is that of a specialized venue management and client interaction tool tailored to the specific needs of the hall and its cultural context. The system provides functionalities for information dissemination, inquiry management, booking status tracking (via payment proof), feedback collection, and basic operational reporting for the administrator.

The system features are illustrated in the Use Case Diagram (Figure 2.1) below. The detailed description of each module (grouped by primary actor) and its functions (use cases) will be elaborated in Section 2.3.

```
@startuml
left to right direction
actor Visitor
actor Admin

rectangle "Al-Muneer Online Portal" {
  usecase "UC001: View Venue Information" as UC1
  usecase "UC002: View Media Gallery" as UC2
  usecase "UC003: Check Availability (Interactive Calendar)" as UC3
  usecase "UC004: View Pricing Customisation Panel" as UC4
  usecase "UC005: Submit Booking Inquiry" as UC5
  usecase "UC011: Submit Payment Proof" as UC11
  usecase "UC012: Submit Feedback" as UC12

  usecase "UC006: Manage Hall Information" as UC6
  usecase "UC007: Manage Media Gallery" as UC7
  usecase "UC008: Manage Pricing Panel" as UC8
  usecase "UC009: Manage Calendar & Inquiries" as UC9
  usecase "UC013: Manage Payment Status" as UC13
  usecase "UC015: Manage Feedback" as UC15
  usecase "UC010: View Traffic (Analytics)" as UC10
  usecase "UC014: View / Generate Reports" as UC14
  usecase "UC016: Configure / Manage Notifications" as UC16
}

Visitor --> UC1
Visitor --> UC2
Visitor --> UC3
Visitor --> UC4
Visitor --> UC5
Visitor --> UC11
Visitor --> UC12

Admin --> UC6
Admin --> UC7
Admin --> UC8
Admin --> UC9
Admin --> UC13
Admin --> UC15
Admin --> UC10
Admin --> UC16
Admin --> UC14

UC14 ..> UC10 : <<includes>>

@enduml
```

_Figure 2.1: Use Case Diagram for Al-Muneer Online Portal_

**Table 2.1: Description of Modules and Functions for Al-Muneer Online Portal**

|   |   |   |
|---|---|---|
|**Module**|**Function**|**Description**|
|**Visitor Interaction Module**|Handles all functionalities accessible to public visitors.||
||UC001: View Venue Information|Allows visitors to view detailed descriptions, services, and location of the hall.|
||UC002: View Media Gallery|Allows visitors to browse images and videos of the hall.|
||UC003: Check Availability|Enables visitors to check for available dates using an interactive calendar.|
||UC004: View Pricing Panel|Allows visitors to see pricing information and package details.|
||UC005: Submit Booking Inquiry|Provides a form for visitors to send booking requests or inquiries to the hall management.|
||UC011: Submit Payment Proof|Allows visitors to upload a screenshot or image as proof of payment for a booking.|
||UC012: Submit Feedback|Enables visitors to provide feedback about the venue or portal.|
|**Administrator Control Module**|Handles all functionalities accessible to the authenticated administrator for managing the portal.||
||UC006: Manage Hall Information|Enables the admin to update venue descriptions, services, FAQs, etc.|
||UC007: Manage Media Gallery|Allows the admin to upload, delete, or modify media items in the gallery.|
||UC008: Manage Pricing Panel|Enables the admin to set and update pricing structures and package details.|
||UC009: Manage Calendar & Inquiries|Allows the admin to update the availability calendar and view/manage submitted inquiries.|
||UC010: View Traffic Analytics|Provides the admin with basic insights into website visitor traffic (conceptual, very basic implementation).|
||UC013: Manage Payment Status|Enables the admin to view uploaded payment proofs and update booking confirmation statuses.|
||UC014: View/Generate Reports|Allows the admin to access basic reports on inquiries, bookings, and feedback.|
||UC015: Manage Feedback|Enables the admin to view and manage user-submitted feedback.|
||UC016: Configure/Manage Notifications|Allows the admin to set up or manage templates/triggers for system notifications.|

The domain model illustrating the key entities and their attributes within the Al-Muneer Online Portal is shown in Figure 2.2. This model focuses on the data aspects of the system from a requirements perspective.

```
@startuml
class AdminUser {
  userId
  username
  passwordHash
  role
}

class VenueInfo {
  infoId
  description
  services
  location
  contactInfo
}

class MediaItem {
  mediaId
  fileName
  filePath
  mediaType
  caption
  uploadDate
}

class AvailabilitySlot {
  slotId
  date
  status
  notes
}

class PricingTier {
  pricingId
  tierName
  basePrice
  description
  isActive
}

class BookingInquiry {
  inquiryId
  visitorName
  visitorContact
  inquiryDate
  eventDate
  status
  notes
}

class PaymentProof {
  proofId
  fileName
  filePath
  uploadTimestamp
  verificationStatus
  adminNotes
}

class Feedback {
  feedbackId
  visitorName
  visitorContact
  feedbackText
  submissionDate
  rating
  isReviewed
}

AdminUser "1" -- "1" VenueInfo : Manages >
AdminUser "1" -- "0..*" MediaItem : Manages >
AdminUser "1" -- "0..*" AvailabilitySlot : Manages >
AdminUser "1" -- "0..*" PricingTier : Manages >
AdminUser "1" -- "0..*" BookingInquiry : Manages >
AdminUser "1" -- "0..*" Feedback : Manages >

BookingInquiry "1" -- "0..1" PaymentProof : Has >
@enduml
```

_Figure 2.2: Domain Model for Al-Muneer Online Portal_

This domain model depicts the core entities: `AdminUser` for system administrators; `VenueInfo` for static hall details; `MediaItem` for gallery content; `AvailabilitySlot` for calendar dates and booking status; `PricingTier` for different costings; `BookingInquiry` for client requests; `PaymentProof` for uploaded payment confirmations; and `Feedback` for user comments. Relationships indicate how administrators manage these entities and how inquiries can lead to payment proofs.

### 2.3 Use Case Details

This section provides detailed descriptions for each use case identified for the Al-Muneer Online Portal. Each use case includes a specification table, an activity diagram, and a sequence diagram to illustrate its flow and interactions.

#### 2.3.1 UC001: Use Case

**Table 2.2: Use Case Description for View Venue Information**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC001|
|**Use Case Name**|View Venue Information|
|**Description**|This use case allows a Visitor to view comprehensive descriptive information about Al-Muneer Hall.|
|**Actor(s)**|Visitor|
|**Pre-condition(s)**|Visitor has access to a web browser and an internet connection, and has navigated to the portal's URL.|
|**Normal Flow(s)- NF**|1. Visitor navigates to the "Venue Information" or "About Us" section of the portal (e.g., via a menu link or homepage section).<br><br>  <br><br>2. System retrieves the latest venue details (description, services offered, capacity, location, contact information) from the database.<br><br>  <br><br>3. System renders and displays the formatted venue information page to the Visitor.|
|**Alternative Flow(s) - AF**|AF1: Information Temporarily Unavailable:<br><br>  <br><br>1. If the venue information content cannot be retrieved from the database due to a temporary issue (e.g., brief database connection problem), the system displays a user-friendly message indicating that the information is currently unavailable and suggests trying again shortly.|
|**Exception Flow(s) - EF**|EF1: Content Not Found:<br><br>  <br><br>1. If the specific venue information content is missing or not configured in the database (e.g., after a faulty admin update), the system displays a "Content not available" message or redirects to a relevant section of the homepage.|
|**Post-condition(s)**|Visitor has viewed the venue information.<br><br>  <br><br>The system state remains unchanged for other users.|

```
@startuml
start
:Visitor Navigates to Venue Info Section;
if (Retrieve Venue Details from DB?) then (Success)
  :System Renders Venue Information Page;
  :Display Venue Information to Visitor;
else (Failure)
  if (Content Missing?) then (Yes)
    :Display "Content Not Available" Message;
  else (No, e.g. Temp DB Issue)
    :Display "Information Temporarily Unavailable" Message;
  endif
endif
stop
@enduml
```

_Figure 2.3: Activity Diagram for View Venue Information_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
database "Database (DB)" as DB

Visitor -> UI : Clicks 'Venue Info' Link
UI -> App : GET /api/venue-info
App -> DB : RetrieveVenueDetails()
DB --> App : VenueDetailsData
App --> UI : VenueInfoResponse (VenueDetailsData)
UI --> Visitor : Displays Venue Information Page
@enduml
```

_Figure 2.4: Sequence Diagram for View Venue Information_

#### 2.3.2 UC002: Use Case

**Table 2.3: Use Case Description for View Media Gallery**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC002|
|**Use Case Name**|View Media Gallery|
|**Description**|This use case allows a Visitor to browse a collection of images and potentially videos of Al-Muneer Hall.|
|**Actor(s)**|Visitor|
|**Pre-condition(s)**|Visitor has access to a web browser and an internet connection, and has navigated to the portal's URL.|
|**Normal Flow(s)- NF**|1. Visitor navigates to the "Media Gallery" section of the portal.<br><br>  <br><br>2. System retrieves a list of media items (image thumbnails, video placeholders) from the database.<br><br>  <br><br>3. System displays the gallery layout with the retrieved media items.<br><br>  <br><br>4. Visitor clicks on a specific media item (e.g., an image thumbnail).<br><br>  <br><br>5. System displays the full-size image or plays the video in a lightbox or dedicated view.|
|**Alternative Flow(s) - AF**|AF1: Empty Gallery:<br><br>  <br><br>1. If no media items are currently configured in the gallery, the system displays a message like "Our gallery is currently being updated. Please check back soon!"<br><br>  <br><br>AF2: Media Item Not Found/Corrupted:<br><br>  <br><br>1. If a specific media file linked in the gallery cannot be loaded (e.g., file deleted, path incorrect), the system displays a placeholder or an error icon for that specific item, while other items remain accessible.|
|**Exception Flow(s) - EF**|EF1: Error Loading Gallery Data:<br><br>  <br><br>1. If the system encounters an error while trying to retrieve the list of media items (e.g., database error), it displays a general error message to the Visitor.|
|**Post-condition(s)**|Visitor has viewed the media gallery and potentially individual media items.<br><br>  <br><br>The system state remains unchanged for other users.|

```
@startuml
start
:Visitor Navigates to Media Gallery Section;
if (Retrieve Media List from DB?) then (Success)
  if (Gallery Empty?) then (Yes)
    :Display "Gallery Empty" Message;
  else (No)
    :System Renders Gallery Layout with Media Items;
    :Display Gallery to Visitor;
    if (Visitor Clicks on Media Item?) then (Yes)
      if (Load Specific Media Item?) then (Success)
        :Display Full-Size Image / Play Video;
      else (Failure - e.g. Corrupted)
        :Display Placeholder/Error for Item;
      endif
    else (No)
    endif
  endif
else (Failure - DB Error)
  :Display General Error Message;
endif
stop
@enduml
```

_Figure 2.5: Activity Diagram for View Media Gallery_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
database "Database (DB)" as DB

Visitor -> UI : Clicks 'Media Gallery' Link
UI -> App : GET /api/gallery/media-items
App -> DB : RetrieveMediaItemList()
DB --> App : MediaItemListData
App --> UI : GalleryResponse (MediaItemListData)
UI --> Visitor : Displays Gallery with Thumbnails
Visitor -> UI : Clicks on a Thumbnail (MediaItemID)
UI --> Visitor : Displays Full-Size Image / Video Player
@enduml
```

_Figure 2.6: Sequence Diagram for View Media Gallery_

#### 2.3.3 UC003: Use Case

**Table 2.4: Use Case Description for Check Availability**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC003|
|**Use Case Name**|Check Availability|
|**Description**|This use case allows a Visitor to view an interactive calendar displaying booked and available dates for Al-Muneer Hall.|
|**Actor(s)**|Visitor|
|**Pre-condition(s)**|Visitor has access to a web browser and an internet connection, and has navigated to the portal's URL.|
|**Normal Flow(s)- NF**|1. Visitor navigates to the "Availability" or "Calendar" section of the portal.<br><br>  <br><br>2. System retrieves current booking status data (booked dates, pending dates if applicable) from the database for a default period (e.g., current month and next few months).<br><br>  <br><br>3. System renders and displays an interactive calendar, visually differentiating between available, booked, and potentially pending dates.<br><br>  <br><br>4. Visitor may interact with the calendar (e.g., navigate to different months/years).<br><br>  <br><br>5. For each navigation, System retrieves and updates the calendar display with the relevant booking status data for the selected period.|
|**Alternative Flow(s) - AF**|AF1: No Booking Data Available:<br><br>  <br><br>1. If there's an issue retrieving booking data, or if all dates are currently marked as available (unlikely for an active hall but possible for a new setup), the calendar displays all dates in the default view as available, or a message indicates no specific bookings are marked for the current view.|
|**Exception Flow(s) - EF**|EF1: Error Loading Calendar Data:<br><br>  <br><br>1. If the system encounters a persistent error while trying to retrieve booking status data (e.g., database connection error), it displays a general error message to the Visitor, suggesting they try again later or contact the hall directly.|
|**Post-condition(s)**|Visitor has viewed the availability status of the hall for the selected period(s).<br><br>  <br><br>The system state remains unchanged for other users.|

```
@startuml
start
:Visitor Navigates to Availability Section;
:System Retrieves Booking Status Data (Default Period);
if (Data Retrieval Successful?) then (Yes)
  :System Renders Interactive Calendar;
  :Display Calendar to Visitor;
  repeat
    :Visitor Interacts with Calendar (e.g., Change Month);
    if (Navigation Action?) then (Yes)
      :System Retrieves Booking Status Data (Selected Period);
      if (Data Retrieval Successful?) then (Yes)
        :Update Calendar Display;
      else (No)
        :Display Error for Period Update;
      endif
    endif
  repeat while (Visitor continues interaction?) is (Yes)
else (No - Initial Load Error)
  :Display General Error Message;
endif
stop
@enduml
```

_Figure 2.7: Activity Diagram for Check Availability_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
database "Database (DB)" as DB

Visitor -> UI : Clicks 'Availability' Link
UI -> App : GET /api/availability?month=YYYY-MM (Default Period)
App -> DB : GetAvailabilitySlots(Period)
DB --> App : AvailabilityData
App --> UI : AvailabilityResponse (AvailabilityData)
UI --> Visitor : Displays Interactive Calendar

Visitor -> UI : Selects Next Month on Calendar
UI -> App : GET /api/availability?month=YYYY-MM (New Period)
App -> DB : GetAvailabilitySlots(NewPeriod)
DB --> App : AvailabilityDataForNewPeriod
App --> UI : AvailabilityResponse (AvailabilityDataForNewPeriod)
UI --> Visitor : Updates Calendar Display
@enduml
```

_Figure 2.8: Sequence Diagram for Check Availability (Initial Load & Month Navigation)_

#### 2.3.4 UC004: Use Case

**Table 2.5: Use Case Description for View Pricing Panel**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC004|
|**Use Case Name**|View Pricing Panel|
|**Description**|This use case allows a Visitor to view detailed pricing information, packages, and potentially customizable options for Al-Muneer Hall services.|
|**Actor(s)**|Visitor|
|**Pre-condition(s)**|Visitor has access to a web browser and an internet connection, and has navigated to the portal's URL.|
|**Normal Flow(s)- NF**|1. Visitor navigates to the "Pricing" or "Packages" section of the portal.<br><br>  <br><br>2. System retrieves current pricing tiers, package details, and any configurable options from the database.<br><br>  <br><br>3. System renders and displays the formatted pricing information to the Visitor.<br><br>  <br><br>4. (Optional) If interactive elements for customizing a package are present (e.g., selecting add-on services), Visitor interacts with these elements.<br><br>  <br><br>5. (Optional) System dynamically updates the displayed estimated price based on Visitor's selections.|
|**Alternative Flow(s) - AF**|AF1: No Specific Pricing Configured:<br><br>  <br><br>1. If detailed pricing tiers are not yet configured by the admin, the system may display a general statement about pricing or a prompt to contact the hall for a custom quote.|
|**Exception Flow(s) - EF**|EF1: Error Loading Pricing Data:<br><br>  <br><br>1. If the system encounters an error while trying to retrieve pricing information (e.g., database error), it displays a general error message to the Visitor.|
|**Post-condition(s)**|Visitor has viewed the pricing information for Al-Muneer Hall.<br><br>  <br><br>The system state remains unchanged for other users.|

```
@startuml
start
:Visitor Navigates to Pricing Section;
:System Retrieves Pricing Information from DB;
if (Data Retrieval Successful?) then (Yes)
  if (Pricing Data Configured?) then (Yes)
    :System Renders Pricing Information Page;
    :Display Pricing Details to Visitor;
    if (Interactive Customization Elements Present?) then (Yes)
      :Visitor Interacts with Customization Options;
      :System Updates Estimated Price Dynamically;
      :Display Updated Price;
    endif
  else (No)
    :Display "Contact for Quote" or General Pricing Info;
  endif
else (No - Data Load Error)
  :Display General Error Message;
endif
stop
@enduml
```

_Figure 2.9: Activity Diagram for View Pricing Panel_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
database "Database (DB)" as DB

Visitor -> UI : Clicks 'Pricing' Link
UI -> App : GET /api/pricing-info
App -> DB : RetrievePricingTiers()
DB --> App : PricingData
App --> UI : PricingResponse (PricingData)
UI --> Visitor : Displays Pricing Information Page
@enduml
```

_Figure 2.10: Sequence Diagram for View Pricing Panel_

#### 2.3.5 UC005: Use Case

**Table 2.6: Use Case Description for Submit Booking Inquiry**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC005|
|**Use Case Name**|Submit Booking Inquiry|
|**Description**|This use case allows a Visitor to submit a formal inquiry or booking request for Al-Muneer Hall.|
|**Actor(s)**|Visitor|
|**Pre-condition(s)**|Visitor has browsed venue information and is interested in making a booking or asking specific questions not covered in the FAQ/general info. Visitor is on the inquiry page/form.|
|**Normal Flow(s)- NF**|1. Visitor accesses the booking inquiry form on the portal.<br><br>  <br><br>2. Visitor fills in required fields such as name, contact number (phone/WhatsApp), email (optional), preferred event date(s), estimated number of guests, type of event, and any specific questions or requirements in a message field.<br><br>  <br><br>3. Visitor clicks the "Submit Inquiry" button.<br><br>  <br><br>4. System validates the submitted data (e.g., checks for mandatory fields, valid contact format).<br><br>  <br><br>5. System saves the inquiry details (including timestamp) into the database.<br><br>  <br><br>6. System displays a confirmation message to the Visitor (e.g., "Your inquiry has been successfully submitted. We will contact you shortly.").|
|**Alternative Flow(s) - AF**|AF1: Invalid Input Data:<br><br>  <br><br>1. If data validation fails (Step 4), the system highlights the fields with errors and displays appropriate error messages (e.g., "Please enter a valid phone number," "Required field missing.").<br><br>  <br><br>2. Visitor remains on the form page to correct the input and can re-submit (returns to Step 3).<br><br>  <br><br>  <br><br>AF2: Duplicate Inquiry Detection (Optional - Advanced):<br><br>  <br><br>1. System might check for very similar recent inquiries from the same contact to prevent accidental duplicates. If a potential duplicate is found, it might ask the visitor to confirm if they want to proceed.|
|**Exception Flow(s) - EF**|EF1: Database Save Error:<br><br>  <br><br>1. If the database fails to save the data, the system will display an error submitting the inquiry message.|
|**Post-condition(s)**|The booking inquiry is successfully recorded in the system's database.<br><br>  <br><br>The Administrator is (ideally) notified of the new inquiry.<br><br>  <br><br>The Visitor receives an on-screen confirmation of submission.|

```
@startuml
start
:Visitor Accesses Inquiry Form;
repeat
  :Visitor Fills Inquiry Form Fields;
  :Visitor Clicks "Submit Inquiry";
  if (Validate Input Data?) then (Valid)
    :System Saves Inquiry to Database;
    if (Save Successful?) then (Yes)
      :System Triggers Notification to Admin;
      :Display "Inquiry Submitted Successfully" Message to Visitor;
      stop
    else (No - DB Save Error)
      :Display "Error Submitting Inquiry" Message to Visitor;
    endif
  else (Invalid)
    :Display Validation Error Messages to Visitor;
  endif
repeat while
@enduml
```

_Figure 2.11: Activity Diagram for Submit Booking Inquiry_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
database "Database (DB)" as DB
participant "NotificationService" as NS

Visitor -> UI : Fills Inquiry Form & Clicks Submit
UI -> App : POST /api/inquiries (InquiryData)
App -> App : Validate(InquiryData)
alt Input Valid
  App -> DB : SaveInquiry(InquiryData)
  DB --> App : InquirySaved (Generated InquiryID)
  App -> NS : SendNewInquiryNotification(Admin, InquiryID)
  NS --> App : NotificationSentStatus
  App --> UI : InquirySubmissionSuccessResponse
else Input Invalid
  App --> UI : InquiryValidationErrorResponse
end
UI --> Visitor : Displays Success or Error Message
@enduml
```

_Figure 2.12: Sequence Diagram for Submit Booking Inquiry_

#### 2.3.6 UC006: Use Case

**Table 2.7: Use Case Description for Manage Hall Information**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC006|
|**Use Case Name**|Manage Hall Information|
|**Description**|This use case allows the Administrator to create, read, update, and delete (CRUD) general descriptive information about Al-Muneer Hall, such as venue description, services offered, capacity, location, contact details, and FAQs.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.|
|**Normal Flow(s)- NF**|NF1: View Hall Information:<br><br>  <br><br>1. Administrator navigates to the "Venue Information" or "Content Management" section in the admin panel.<br><br>  <br><br>2. System retrieves the current hall information from the database.<br><br>  <br><br>3. System displays the information in editable fields/text areas.<br><br>  <br><br>  <br><br>NF2: Update Hall Information:<br><br>  <br><br>1. Administrator modifies the content in the displayed fields (e.g., changes description, updates contact number, adds a new FAQ).<br><br>  <br><br>2. Administrator clicks a "Save" or "Update" button.<br><br>  <br><br>3. System validates the input (e.g., checks for required fields if any, character limits).<br><br>  <br><br>4. System saves the updated information to the database.<br><br>  <br><br>5. System displays a success message (e.g., "Information updated successfully.").|
|**Alternative Flow(s) - AF**|AF1: Input Validation Error (Update):<br><br>  <br><br>1. If system validation fails (Step NF2.3), the system displays an error message next to the problematic fields.<br><br>  <br><br>2. Administrator corrects the input and re-submits.<br><br>  <br><br>  <br><br>AF2: Create New FAQ Item (if FAQs are managed as separate items):<br><br>  <br><br>1. Admin clicks "Add New FAQ".<br><br>  <br><br>2. System displays fields for Question and Answer.<br><br>  <br><br>3. Admin fills fields and saves.<br><br>  <br><br>4. System validates and saves.|
|**Exception Flow(s) - EF**|EF1: Error Saving Data:<br><br>  <br><br>1. If the system encounters an error while trying to save the updated information to the database (e.g., database connection error), it displays a general error message (e.g., "Failed to update information. Please try again.").|
|**Post-condition(s)**|Hall information displayed on the public portal is updated (after successful update).<br><br>  <br><br>Database reflects the changes.|

```
@startuml
start
:Admin Navigates to Content Management Section;
:System Retrieves Current Hall Information;
:Display Information in Editable Form;
repeat
  :Admin Modifies Information;
  :Admin Clicks "Save/Update";
  if (Validate Input?) then (Valid)
    :System Saves Updated Information to DB;
    if (Save Successful?) then (Yes)
      :Display "Update Successful" Message;
      stop
    else (No - DB Error)
      :Display "Error Saving Data" Message;
    endif
  else (Invalid)
    :Display Validation Error Messages;
    :Admin Corrects Input;
  endif
repeat while
@enduml
```

_Figure 2.13: Activity Diagram for Manage Hall Information (Update Flow)_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI AdminPanel)" as UI
participant "Backend (AppServer ContentController)" as App
database "Database (DB VenueInfo)" as DB

Admin -> UI : Navigates to Edit Venue Info
UI -> App : GET /api/admin/venue-info
App -> DB : GetVenueInfo()
DB --> App : CurrentVenueInfoData
App --> UI : VenueInfoResponse (CurrentVenueInfoData)
UI --> Admin : Displays Current Info in Editable Form

Admin -> UI : Modifies Info & Clicks Save
UI -> App : PUT /api/admin/venue-info (UpdatedData)
App -> App : Validate(UpdatedData)
alt Input Valid
  App -> DB : UpdateVenueInfo(UpdatedData)
  DB --> App : UpdateSuccess
  App --> UI : SuccessResponse ("Info Updated")
else Input Invalid
  App --> UI : ValidationErrorResponse
end
UI --> Admin : Displays Success/Error Message
@enduml
```

_Figure 2.14: Sequence Diagram for Manage Hall Information (Update Flow)_

#### 2.3.7 UC007: Use Case

**Table 2.8: Use Case Description for Manage Media Gallery**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC007|
|**Use Case Name**|Manage Media Gallery|
|**Description**|This use case allows the Administrator to upload new media items (images, videos) to the gallery, view existing items, and delete items from the gallery.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.|
|**Normal Flow(s)- NF**|NF1: View Gallery Items:<br><br>  <br><br>1. Administrator navigates to the "Media Gallery Management" section.<br><br>  <br><br>2. System retrieves and displays a list or grid of existing media items (thumbnails, names).<br><br>  <br><br>  <br><br>NF2: Upload New Media Item:<br><br>  <br><br>1. Administrator clicks "Upload New Media".<br><br>  <br><br>2. System presents a file selection dialog and fields for optional caption/details.<br><br>  <br><br>3. Administrator selects a media file (image/video) and enters details.<br><br>  <br><br>4. Administrator clicks "Upload".<br><br>  <br><br>5. System validates the file (type, size).<br><br>  <br><br>6. System uploads the file to the server/file storage.<br><br>  <br><br>7. System saves metadata (filename, path, caption) to the database.<br><br>  <br><br>8. System displays a success message and refreshes the gallery list.<br><br>  <br><br>  <br><br>NF3: Delete Media Item:<br><br>  <br><br>1. Administrator selects a media item to delete (e.g., clicks a delete icon next to an item).<br><br>  <br><br>2. System prompts for confirmation.<br><br>  <br><br>3. Administrator confirms deletion.<br><br>  <br><br>4. System deletes the media file from storage.<br><br>  <br><br>5. System deletes the metadata from the database.<br><br>  <br><br>6. System displays a success message and refreshes the gallery list.|
|**Alternative Flow(s) - AF**|AF1: File Validation Error (Upload):<br><br>  <br><br>1. If file validation fails (NF2.5), system displays an error (e.g., "Invalid file type" or "File too large"). Admin can re-select.<br><br>  <br><br>  <br><br>AF2: Deletion Cancelled:<br><br>  <br><br>1. Administrator cancels the deletion at the confirmation prompt (NF3.3). System takes no action.|
|**Exception Flow(s) - EF**|EF1: Upload Failure:<br><br>  <br><br>1. If file upload fails due to server error (NF2.6), system displays an error message.<br><br>  <br><br>  <br><br>EF2: Database Save/Delete Failure:<br><br>  <br><br>1. If saving/deleting metadata fails (NF2.7 or NF3.5), system displays an error. May require manual cleanup if file operation succeeded but DB failed.|
|**Post-condition(s)**|Media gallery content is updated (new item added or existing item removed).<br><br>  <br><br>Public gallery reflects changes.|

```
@startuml
start
:Admin Navigates to Gallery Management;
:Admin Clicks "Upload New Media";
repeat
  :Display File Upload Form;
  :Admin Selects File & Enters Details;
  :Admin Clicks "Upload";
  if (Validate File (Type, Size)) then (Valid)
    :System Uploads File to Storage;
    if (File Upload Successful?) then (Yes)
      :System Saves Media Metadata to DB;
      if (DB Save Successful?) then (Yes)
        :Display "Upload Successful" Message;
        :Refresh Gallery List;
        stop
      else (No - DB Error)
        :Display "Error Saving Metadata" Message;
      endif
    else (No - Storage Error)
      :Display "File Upload Failed" Message;
    endif
  else (Invalid File)
    :Display File Validation Error Message;
  endif
repeat while
@enduml
```

_Figure 2.15: Activity Diagram for Manage Media Gallery (Upload Flow)_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - MediaController)" as App
participant "FileStorageService" as Storage
database "Database (DB - MediaItem)" as DB

Admin -> UI : Selects File & Clicks Upload
UI -> App : POST /api/admin/gallery/upload (File, Caption)
App -> App : ValidateFile(File)
alt File Valid
  App -> Storage : StoreFile(File)
  Storage --> App : FilePath
  App -> DB : SaveMediaMetadata(FilePath, Caption)
  DB --> App : SaveSuccess
  App --> UI : UploadSuccessResponse
else File Invalid
  App --> UI : FileValidationErrorResponse
end
UI --> Admin : Displays Success/Error Message & Refreshes Gallery
@enduml
```

_Figure 2.16: Sequence Diagram for Manage Media Gallery (Upload Flow)_

#### 2.3.8 UC008: Use Case

**Table 2.9: Use Case Description for Manage Pricing Panel**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC008|
|**Use Case Name**|Manage Pricing Panel|
|**Description**|This use case allows the Administrator to define, view, update, and delete pricing tiers, packages, and any configurable pricing options for Al-Muneer Hall services.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.|
|**Normal Flow(s)- NF**|NF1: View Pricing Tiers:<br><br>  <br><br>1. Administrator navigates to the "Pricing Management" section.<br><br>  <br><br>2. System retrieves and displays existing pricing tiers/packages.<br><br>  <br><br>  <br><br>NF2: Add New Pricing Tier:<br><br>  <br><br>1. Administrator clicks "Add New Tier".<br><br>  <br><br>2. System displays a form for tier name, base price, description, included services.<br><br>  <br><br>3. Administrator fills in the details and clicks "Save".<br><br>  <br><br>4. System validates input.<br><br>  <br><br>5. System saves the new pricing tier to the database.<br><br>  <br><br>6. System displays a success message and refreshes the list.<br><br>  <br><br>  <br><br>NF3: Update Existing Pricing Tier:<br><br>  <br><br>1. Administrator selects an existing tier to edit.<br><br>  <br><br>2. System populates a form with the tier's current details.<br><br>  <br><br>3. Administrator modifies details and clicks "Update".<br><br>  <br><br>4. System validates input.<br><br>  <br><br>5. System saves changes to the database.<br><br>  <br><br>6. System displays success message.<br><br>  <br><br>  <br><br>NF4: Delete Pricing Tier:<br><br>  <br><br>1. Administrator selects a tier to delete.<br><br>  <br><br>2. System prompts for confirmation.<br><br>  <br><br>3. Administrator confirms.<br><br>  <br><br>4. System deletes the tier from the database (may require checks if linked to active bookings out of simple scope).<br><br>  <br><br>5. System displays success message.|
|**Alternative Flow(s) - AF**|AF1: Input Validation Error (Add/Update):<br><br>  <br><br>1. If validation fails, display error messages. Admin corrects and resubmits.|
|**Exception Flow(s) - EF**|EF1: Database Error (Save/Update/Delete):<br><br>  <br><br>1. If a database operation fails, display a general error message.|
|**Post-condition(s)**|Pricing information is updated in the database.<br><br>  <br><br>Public pricing page reflects changes.|

```
@startuml
start
:Admin Navigates to Pricing Management;
:Admin Clicks "Add New Tier";
repeat
  :Display New Pricing Tier Form;
  :Admin Fills Tier Details;
  :Admin Clicks "Save";
  if (Validate Input?) then (Valid)
    :System Saves New Tier to DB;
    if (Save Successful?) then (Yes)
      :Display "Tier Added Successfully" Message;
      :Refresh Pricing Tier List;
      stop
    else (No - DB Error)
      :Display "Error Saving Tier" Message;
    endif
  else (Invalid)
    :Display Validation Error Messages;
  endif
repeat while
@enduml
```

_Figure 2.17: Activity Diagram for Manage Pricing Panel (Add New Tier Flow)_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - PricingController)" as App
database "Database (DB - PricingTier)" as DB

Admin -> UI : Clicks "Add New Tier", Fills Form & Saves
UI -> App : POST /api/admin/pricing-tiers (NewTierData)
App -> App : Validate(NewTierData)
alt Input Valid
  App -> DB : SavePricingTier(NewTierData)
  DB --> App : SaveSuccess
  App --> UI : SuccessResponse ("Tier Added")
else Input Invalid
  App --> UI : ValidationErrorResponse
end
UI --> Admin : Displays Success/Error Message & Refreshes List
@enduml
```

_Figure 2.18: Sequence Diagram for Manage Pricing Panel (Add New Tier Flow)_

#### 2.3.9 UC009: Use Case <Manage Calendar & Inquiries>

**Table 2.10: Use Case Description for Manage Calendar & Inquiries**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC009|
|**Use Case Name**|Manage Calendar & Inquiries|
|**Description**|This use case allows the Administrator to manually update the availability calendar (mark dates as booked/available/pending) and to view details of submitted booking inquiries.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.|
|**Normal Flow(s)- NF**|NF1: View Inquiries:<br><br>  <br><br>1. Administrator navigates to the "Booking Inquiries" section.<br><br>  <br><br>2. System retrieves and displays a list of submitted inquiries (e.g., visitor name, contact, event date, status).<br><br>  <br><br>3. Administrator can click on an inquiry to view its full details.<br><br>  <br><br>  <br><br>NF2: Update Calendar Manually:<br><br>  <br><br>1. Administrator navigates to the "Calendar Management" section.<br><br>  <br><br>2. System displays an interactive calendar showing current statuses.<br><br>  <br><br>3. Administrator selects a date or date range.<br><br>  <br><br>4. Administrator chooses a new status (e.g., Booked, Available, Pending).<br><br>  <br><br>5. Administrator saves the change.<br><br>  <br><br>6. System updates the availability slot(s) in the database.<br><br>  <br><br>7. System displays a success message and refreshes the calendar view.<br><br>  <br><br>  <br><br>NF3: Update Inquiry Status (Simple):<br><br>  <br><br>1. When viewing an inquiry (NF1.3), Administrator can update its status (e.g., "Viewed", "Contacted", "Tentatively Booked" - prior to payment proof).<br><br>  <br><br>2. Administrator saves the status change.<br><br>  <br><br>3. System updates the inquiry status in the database.|
|**Alternative Flow(s) - AF**|AF1: No Inquiries:<br><br>  <br><br>1. If no inquiries exist when viewing (NF1.2), system displays "No new inquiries."|
|**Exception Flow(s) - EF**|EF1: Database Error (Saving Calendar/Inquiry Status):<br><br>  <br><br>1. If a database operation fails, display a general error message.|
|**Post-condition(s)**|Availability calendar is updated.<br><br>  <br><br>Status of inquiries may be updated.|

```
@startuml
start
:Admin Navigates to Calendar Management;
:System Displays Interactive Calendar;
:Admin Selects Date(s);
:Admin Chooses New Status (Booked/Available/Pending);
:Admin Clicks "Save Change";
:System Updates Availability Slot(s) in DB;
if (Save Successful?) then (Yes)
  :Display "Calendar Updated" Message;
  :Refresh Calendar View;
else (No - DB Error)
  :Display "Error Updating Calendar" Message;
endif
stop
@enduml
```

_Figure 2.19: Activity Diagram for Manage Calendar (Update Manually)_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - CalendarController)" as App
database "Database (DB - AvailabilitySlot)" as DB

Admin -> UI : Selects Date & New Status, Clicks Save
UI -> App : POST /api/admin/calendar/update-slot (Date, NewStatus)
App -> DB : UpdateAvailabilitySlot(Date, NewStatus)
DB --> App : UpdateSuccess
App --> UI : SuccessResponse ("Calendar Updated")
UI --> Admin : Displays Success Message & Refreshes Calendar
@enduml
```

_Figure 2.20: Sequence Diagram for Manage Calendar (Update Manually)_

#### 2.3.10 UC010: Use Case

**Table 2.11: Use Case Description for View Traffic Analytics**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC010|
|**Use Case Name**|View Traffic Analytics|
|**Description**|This use case allows the Administrator to view very basic website traffic analytics or visit counts. This feature is conceptual for PSM1 and may involve simple logging or a placeholder for future integration with a basic analytics tool.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.|
|**Normal Flow(s)- NF**|1. Administrator navigates to the "Analytics" or "Site Statistics" section in the admin panel.<br><br>  <br><br>2. System retrieves basic logged data (e.g., total page visits for key pages like homepage, gallery, inquiry page, if such logging is implemented) or displays a placeholder for this feature.<br><br>  <br><br>3. System displays the basic analytics data or a message indicating the feature's status (e.g., "Basic visit count logging active" or "Advanced analytics planned for future").|
|**Alternative Flow(s) - AF**|AF1: No Data Logged Yet:<br><br>  <br><br>1. If no traffic data has been logged (e.g., new system, logging not fully active), the system displays "No traffic data available yet."|
|**Exception Flow(s) - EF**|EF1: Error Retrieving Log Data:<br><br>  <br><br>1. If the system encounters an error while trying to access any stored log files or simple analytic data, it displays a general error message.|
|**Post-condition(s)**|Administrator has viewed the available basic traffic analytics or the status of this feature.|

```
@startuml
start
:Admin Navigates to Analytics Section;
:System Attempts to Retrieve Basic Log/Analytics Data;
if (Data Retrieval Successful?) then (Yes)
  if (Data Available?) then (Yes)
    :System Displays Basic Analytics Data;
  else (No Data Yet)
    :Display "No Traffic Data Available Yet";
  endif
else (No - Retrieval Error or Feature Placeholder)
  :Display "Analytics Feature Status" or Error Message;
endif
stop
@enduml
```

_Figure 2.21: Activity Diagram for View Traffic Analytics_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - AnalyticsController)" as App
database "LogStorage/SimpleAnalyticsDB (Conceptual)" as DB

Admin -> UI : Navigates to Analytics Section
UI -> App : GET /api/admin/analytics/basic-traffic
App -> DB : GetBasicTrafficData()
DB --> App : TrafficData (or status)
App --> UI : AnalyticsResponse (TrafficData/Status)
UI --> Admin : Displays Basic Analytics or Feature Status
@enduml
```

_Figure 2.22: Sequence Diagram for View Traffic Analytics (Conceptual)_

#### 2.3.11 UC011: Use Case

**Table 2.12: Use Case Description for Submit Payment Proof**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC011|
|**Use Case Name**|Submit Payment Proof|
|**Description**|This use case allows a Visitor, typically after making a booking inquiry and arranging an offline payment (e.g., local bank transfer), to upload a proof of payment (e.g., screenshot of transfer receipt) to the portal.|
|**Actor(s)**|Visitor|
|**Pre-condition(s)**|Visitor has made a booking inquiry (UC005).<br><br>  <br><br>Visitor has been instructed by the Administrator to submit payment proof, or a booking has reached a stage where payment proof is expected.<br><br>  <br><br>Visitor has an image file of the payment proof.|
|**Normal Flow(s)- NF**|1. Visitor navigates to a designated "Submit Payment Proof" page or a link provided (e.g., in an email/message from admin, or on their inquiry status page if available).<br><br>  <br><br>2. System may require Visitor to input their Inquiry ID or other identifying information if not already session-based or link-specific.<br><br>  <br><br>3. System presents a file upload form.<br><br>  <br><br>4. Visitor selects the payment proof image file from their device.<br><br>  <br><br>5. Visitor clicks "Upload Proof" or "Submit".<br><br>  <br><br>6. System validates the file (e.g., file type JPG/PNG, file size limit).<br><br>  <br><br>7. System uploads the file to a secure storage location.<br><br>  <br><br>8. System records the metadata of the uploaded proof (filename, path, timestamp, associated Inquiry ID) in the database.<br><br>  <br><br>9. System displays a success message to the Visitor (e.g., "Payment proof submitted successfully. We will verify it shortly.").<br><br>  <br><br>10. System triggers a notification to the Administrator about the new payment proof submission.|
|**Alternative Flow(s) - AF**|AF1: Invalid File Type/Size:<br><br>  <br><br>1. If file validation fails (Step 6), the system displays an error message specifying the issue (e.g., "Invalid file format. Please upload JPG or PNG.", "File size exceeds limit.").<br><br>  <br><br>2. Visitor remains on the form to select a valid file.<br><br>  <br><br>  <br><br>AF2: Invalid Inquiry ID (if required):<br><br>  <br><br>1. If an Inquiry ID is required and the one entered is invalid or not found (Step 2), the system displays an error message.|
|**Exception Flow(s) - EF**|EF1: File Upload Failure:<br><br>  <br><br>1. If the system fails to upload the file to storage due to a server-side error (Step 7), it displays a general error message (e.g., "Failed to upload payment proof. Please try again.").<br><br>  <br><br>  <br><br>EF2: Database Save Failure:<br><br>  <br><br>1. If the system fails to save the payment proof metadata to the database (Step 8), it displays an error. This might require reconciliation if the file was uploaded but DB record failed.|
|**Post-condition(s)**|Payment proof file is uploaded and its metadata is stored in the system, linked to the relevant booking inquiry.<br><br>  <br><br>Administrator is notified of the new submission.<br><br>  <br><br>Visitor receives confirmation of submission.|

```
@startuml
start
:Visitor Navigates to Payment Proof Submission Page;
:Optional - Visitor Enters Inquiry ID;
repeat
  :System Displays File Upload Form;
  :Visitor Selects Payment Proof File;
  :Visitor Clicks "Upload Proof";
  if (Validate File (Type, Size)) then (Valid)
    :System Uploads File to Storage;
    if (File Upload Successful?) then (Yes)
      :System Saves Proof Metadata to DB (links to inquiry);
      if (DB Save Successful?) then (Yes)
        :System Triggers Notification to Admin;
        :Display "Proof Submitted Successfully" Message to Visitor;
        stop
      else (No - DB Save Error)
        :Display "Error Saving Proof Information" Message;
      endif
    else (No - File Storage Error)
      :Display "File Upload Failed" Message;
    endif
  else (Invalid File)
    :Display File Validation Error Message;
  endif
repeat while
@enduml
```

_Figure 2.23: Activity Diagram for Submit Payment Proof_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
participant "FileStorageService" as Storage
database "Database (DB)" as DB
participant "NotificationService" as NS

Visitor -> UI : Accesses Upload Form, Selects File, Enters InquiryID (optional), Clicks Submit
UI -> App : POST /api/payment-proof/upload (File, InquiryID, otherData)
App -> App : ValidateFile(File)
alt File Valid and InquiryID Valid (if needed)
  App -> Storage : StoreProofFile(File)
  Storage --> App : FilePath
  App -> DB : SavePaymentProofMetadata(FilePath, InquiryID)
  DB --> App : SaveSuccess (ProofID)
  App -> NS : SendNewPaymentProofNotification(Admin, ProofID)
  NS --> App : NotificationSentStatus
  App --> UI : UploadSuccessResponse
else File or InquiryID Invalid
  App --> UI : ValidationErrorResponse
end
UI --> Visitor : Displays Success or Error Message
@enduml
```

_Figure 2.24: Sequence Diagram for Submit Payment Proof_

#### 2.3.12 UC012: Use Case

**Table 2.13: Use Case Description for Submit Feedback**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC012|
|**Use Case Name**|Submit Feedback|
|**Description**|This use case allows a Visitor or client to submit feedback about their experience with Al-Muneer Hall or the online portal itself.|
|**Actor(s)**|Visitor (can be anonymous or provide contact details)|
|**Pre-condition(s)**|Visitor has accessed the Al-Muneer Online Portal.|
|**Normal Flow(s)- NF**|1. Visitor navigates to the "Feedback" or "Contact Us" section that includes a feedback form.<br><br>  <br><br>2. Visitor optionally enters their name and contact details (email/phone).<br><br>  <br><br>3. Visitor types their feedback message in a text area.<br><br>  <br><br>4. Visitor optionally selects a rating (e.g., star rating) if provided.<br><br>  <br><br>5. Visitor clicks the "Submit Feedback" button.<br><br>  <br><br>6. System validates input (e.g., checks if feedback text is provided, validates contact format if entered).<br><br>  <br><br>7. System saves the feedback (including timestamp, contact details if provided, rating) to the database.<br><br>  <br><br>8. System displays a success message to the Visitor (e.g., "Thank you for your feedback!").<br><br>  <br><br>9. System may trigger a notification to the Administrator about the new feedback.|
|**Alternative Flow(s) - AF**|AF1: Anonymous Feedback:<br><br>  <br><br>1. Visitor chooses not to enter name or contact details (Step 2). System proceeds with saving anonymous feedback.<br><br>  <br><br>  <br><br>AF2: Input Validation Error:<br><br>  <br><br>1. If validation fails (e.g., feedback text is empty, Step 6), the system displays an error message. Visitor remains on the form to correct.|
|**Exception Flow(s) - EF**|EF1: System Failure to Save Feedback:<br><br>  <br><br>1. If the system encounters an error while saving feedback to the database (Step 7), it displays a general error message to the Visitor.|
|**Post-condition(s)**|Feedback is successfully recorded in the system.<br><br>  <br><br>Administrator may be notified.|

```
@startuml
start
:Visitor Navigates to Feedback Form;
repeat
  :Visitor Optionally Enters Name/Contact;
  :Visitor Enters Feedback Message;
  :Visitor Optionally Selects Rating;
  :Visitor Clicks "Submit Feedback";
  if (Validate Input (e.g., Feedback Text not empty)?) then (Valid)
    :System Saves Feedback to Database;
    if (Save Successful?) then (Yes)
      :[Optional] System Triggers Notification to Admin;
      :Display "Feedback Submitted Successfully" Message;
      stop
    else (No - DB Save Error)
      :Display "Error Submitting Feedback" Message;
    endif
  else (Invalid)
    :Display Validation Error Message;
  endif
repeat while
@enduml
```

_Figure 2.25: Activity Diagram for Submit Feedback_

```
@startuml
actor Visitor
participant "Frontend (WebUI)" as UI
participant "Backend (AppServer)" as App
database "Database (DB)" as DB
participant "NotificationService (Optional)" as NS

Visitor -> UI : Fills Feedback Form & Clicks Submit
UI -> App : POST /api/feedback (FeedbackData)
App -> App : Validate(FeedbackData)
alt Input Valid
  App -> DB : SaveFeedback(FeedbackData)
  DB --> App : FeedbackSaved (Generated FeedbackID)
  App -> NS : SendNewFeedbackNotification(Admin, FeedbackID)
  NS --> App : NotificationSentStatus
  App --> UI : FeedbackSubmissionSuccessResponse
else Input Invalid
  App --> UI : FeedbackValidationErrorResponse
end
UI --> Visitor : Displays Success or Error Message
@enduml
```

_Figure 2.26: Sequence Diagram for Submit Feedback_

#### 2.3.13 UC013: Use Case

**Table 2.14: Use Case Description for Manage Payment Status**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC013|
|**Use Case Name**|Manage Payment Status|
|**Description**|This use case allows the Administrator to view submitted payment proofs and update the verification status of a payment, thereby confirming or rejecting a booking associated with it.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.<br><br>  <br><br>One or more payment proofs have been submitted by visitors (UC011).|
|**Normal Flow(s)- NF**|1. Administrator navigates to the "Payment Proofs" or "Booking Management" section in the admin panel.<br><br>  <br><br>2. System retrieves and displays a list of submitted payment proofs, typically showing associated Inquiry ID, visitor name, upload date, and current verification status (e.g., "Pending Verification").<br><br>  <br><br>3. Administrator selects a specific payment proof to review.<br><br>  <br><br>4. System displays the details of the payment proof, including a link to view/download the uploaded image file.<br><br>  <br><br>5. Administrator views the image and verifies the payment offline (e.g., checks their bank account or local transfer records).<br><br>  <br><br>6. Administrator updates the verification status in the system (e.g., to "Verified" or "Rejected").<br><br>  <br><br>7. Administrator may add internal notes regarding the verification.<br><br>  <br><br>8. Administrator saves the changes.<br><br>  <br><br>9. System updates the payment proof status and potentially the associated booking inquiry status in the database.<br><br>  <br><br>10. System displays a success message.<br><br>  <br><br>11. System may trigger a notification to the Visitor about their payment verification status (e.g., "Your payment has been confirmed!").|
|**Alternative Flow(s) - AF**|AF1: No Payment Proofs Submitted:<br><br>  <br><br>1. If no payment proofs are pending review, the system displays a message like "No new payment proofs to verify."|
|**Exception Flow(s) - EF**|EF1: Error Viewing Image File:<br><br>  <br><br>1. If the uploaded image file is corrupted or cannot be accessed, the system displays an error. Admin may need to contact visitor for re-submission.<br><br>  <br><br>  <br><br>EF2: Database Update Failure:<br><br>  <br><br>1. If the system fails to save the updated status to the database, it displays an error message.|
|**Post-condition(s)**|The verification status of the payment proof is updated in the system.<br><br>  <br><br>The status of the associated booking inquiry may also be updated.<br><br>  <br><br>Visitor may be notified of the outcome.|

```
@startuml
start
:Admin Navigates to Payment Proofs Section;
:System Displays List of Submitted Payment Proofs;
if (Payment Proofs Exist?) then (Yes)
  :Admin Selects a Payment Proof;
  :System Displays Proof Details & Image Link;
  :Admin Views/Downloads Proof Image;
  :Admin Verifies Payment Offline;
  :Admin Updates Verification Status (Verified/Rejected) in System;
  :[Optional] Admin Adds Notes;
  :Admin Clicks "Save Status";
  :System Updates Payment Proof & Booking Status in DB;
  if (Save Successful?) then (Yes)
    :[Optional] System Triggers Notification to Visitor;
    :Display "Status Updated Successfully" Message;
  else (No - DB Save Error)
    :Display "Error Updating Status" Message;
  endif
else (No Proofs)
  :Display "No New Payment Proofs" Message;
endif
stop
@enduml
```

_Figure 2.27: Activity Diagram for Manage Payment Status_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - PaymentController)" as App
database "Database (DB)" as DB
participant "NotificationService (Optional)" as NS

Admin -> UI : Navigates to Payment Proofs, Selects a Proof
UI --> Admin : Displays Proof Details (incl. image link from previous load)
Admin -> UI : Shows Proof image (Admin views it)
Admin -> UI : Updates Status (Verified/Rejected) & Clicks Save
UI -> App : PUT /api/admin/payment-proofs/{proofId}/status (NewStatus)
App -> DB : UpdatePaymentProofStatus(ProofID, NewStatus)
DB --> App : UpdateSuccess
App -> DB : UpdateBookingInquiryStatus(AssociatedInquiryID)
App -> NS : SendPaymentStatusNotification(Visitor, ProofID, NewStatus)
NS --> App : NotificationSentStatus
App --> UI : StatusUpdateSuccessResponse
UI --> Admin : Displays Success Message
@enduml
```

_Figure 2.28: Sequence Diagram for Manage Payment Status_

#### 2.3.14 UC014: Use Case <View/Generate Reports>

**Table 2.15: Use Case Description for View/Generate Reports**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC014|
|**Use Case Name**|View/Generate Reports|
|**Description**|This use case allows the Administrator to view or generate basic predefined reports related to portal activities, such as summaries of booking inquiries, booking statuses (based on payment verification), or an overview of feedback received.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.<br><br>  <br><br>Data (inquiries, payment proofs, feedback) exists in the system.|
|**Normal Flow(s)- NF**|1. Administrator navigates to the "Reports" section in the admin panel.<br><br>  <br><br>2. System displays a list of available predefined report types (e.g., "Inquiry Summary," "Booking Confirmation Status," "Feedback Overview").<br><br>  <br><br>3. Administrator selects a report type.<br><br>  <br><br>4. Administrator may specify parameters if applicable (e.g., date range for the report).<br><br>  <br><br>5. Administrator clicks "Generate Report" or "View Report".<br><br>  <br><br>6. System retrieves the relevant data from the database based on the report type and parameters.<br><br>  <br><br>7. System processes the data and generates the report in a simple display format (e.g., a table on the web page, or a simple list).<br><br>  <br><br>8. System displays the generated report to the Administrator.|
|**Alternative Flow(s) - AF**|AF1: No Data for Report:<br><br>  <br><br>1. If no data matches the selected report type or parameters (e.g., no inquiries in the specified date range), the system displays a message like "No data available for this report."|
|**Exception Flow(s) - EF**|EF1: Error Generating Report:<br><br>  <br><br>1. If the system encounters an error while retrieving data or generating the report (e.g., complex query fails, unexpected data format), it displays a general error message.|
|**Post-condition(s)**|Administrator has viewed the generated report.<br><br>  <br><br>System data remains unchanged by report generation.|

```
@startuml
start
:Admin Navigates to Reports Section;
:System Displays Available Report Types;
:Admin Selects Report Type;
:[Optional] Admin Specifies Report Parameters (e.g., Date Range);
:Admin Clicks "Generate Report";
:System Retrieves Data from DB based on Report Type & Parameters;
if (Data Retrieval Successful?) then (Yes)
  if (Data Exists for Report?) then (Yes)
    :System Processes Data and Generates Report;
    :Display Generated Report to Admin;
  else (No Data)
    :Display "No Data Available for Report" Message;
  endif
else (No - Retrieval Error)
  :Display "Error Generating Report" Message;
endif
stop
@enduml
```

_Figure 2.29: Activity Diagram for View/Generate Reports_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - ReportController)" as App
database "Database (DB)" as DB

Admin -> UI : Navigates to Reports, Selects Report Type & Parameters, Clicks Generate
UI -> App : GET /api/admin/reports/{reportType} (Parameters)
App -> DB : FetchReportData(ReportType, Parameters)
DB --> App : ReportData
App -> App : ProcessDataAndFormatReport(ReportData)
App --> UI : ReportResponse (FormattedReport)
UI --> Admin : Displays Generated Report
@enduml
```

_Figure 2.30: Sequence Diagram for View/Generate Reports_

#### 2.3.15 UC015: Use Case

**Table 2.16: Use Case Description for Manage Feedback**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC015|
|**Use Case Name**|Manage Feedback|
|**Description**|This use case allows the Administrator to view submitted user feedback, and potentially mark it as reviewed or take internal notes.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.<br><br>  <br><br>User feedback may have been submitted (UC012).|
|**Normal Flow(s)- NF**|1. Administrator navigates to the "Feedback Management" section in the admin panel.<br><br>  <br><br>2. System retrieves and displays a list of submitted feedback entries (e.g., showing submission date, visitor name if provided, part of the message, current review status).<br><br>  <br><br>3. Administrator selects a feedback entry to view its full details.<br><br>  <br><br>4. System displays the complete feedback message, contact details (if any), rating (if any), and submission timestamp.<br><br>  <br><br>5. Administrator can optionally mark the feedback as "Reviewed" or add internal notes regarding any action taken or observations.<br><br>  <br><br>6. Administrator saves any changes (e.g., review status, notes).<br><br>  <br><br>7. System updates the feedback entry in the database.<br><br>  <br><br>8. System displays a success message.|
|**Alternative Flow(s) - AF**|AF1: No Feedback Submitted:<br><br>  <br><br>1. If no feedback entries exist, the system displays a message like "No user feedback submitted yet."|
|**Exception Flow(s) - EF**|EF1: Database Update Failure:<br><br>  <br><br>1. If the system fails to save changes to a feedback entry (e.g., updated review status), it displays an error message.|
|**Post-condition(s)**|Administrator has reviewed user feedback.<br><br>  <br><br>Review status or notes for feedback entries may be updated in the system.|

```
@startuml
start
:Admin Navigates to Feedback Management;
:System Displays List of Submitted Feedback;
if (Feedback Entries Exist?) then (Yes)
  :Admin Selects a Feedback Entry;
  :System Displays Full Feedback Details;
  :[Optional] Admin Marks as Reviewed or Adds Notes;
  if (Admin Made Changes to Status/Notes?) then (Yes)
    :Admin Clicks "Save Changes";
    :System Updates Feedback Entry in DB;
    if (Save Successful?) then (Yes)
      :Display "Feedback Updated" Message;
    else (No - DB Error)
      :Display "Error Updating Feedback" Message;
    endif
  endif
else (No Feedback)
  :Display "No Feedback Submitted Yet" Message;
endif
stop
@enduml
```

_Figure 2.31: Activity Diagram for Manage Feedback_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - FeedbackController)" as App
database "Database (DB - Feedback)" as DB

Admin -> UI : Navigates to Feedback, Selects an Entry
UI --> Admin : Displays Full Feedback Details
Admin -> UI : Marks as Reviewed, Clicks Save
UI -> App : PUT /api/admin/feedback/{feedbackId}/status (NewStatus, Notes)
App -> DB : UpdateFeedbackEntry(FeedbackID, NewStatus, Notes)
DB --> App : UpdateSuccess
App --> UI : FeedbackUpdateSuccessResponse
UI --> Admin : Displays Success Message
@enduml
```

_Figure 2.32: Sequence Diagram for Manage Feedback (Reviewing and Updating Status)_

#### 2.3.16 UC016: Use Case <Configure/Manage Notifications>

**Table 2.17: Use Case Description for Configure/Manage Notifications**

|   |   |
|---|---|
|**Attribute**|**Description**|
|**Use Case ID**|UC016|
|**Use Case Name**|Configure/Manage Notifications|
|**Description**|This use case allows the Administrator to manage settings related to system notifications, specifically editing template content and placeholders for pre-filled WhatsApp messages to facilitate direct client communication.|
|**Actor(s)**|Administrator|
|**Pre-condition(s)**|Administrator is logged into the admin panel.|
|**Normal Flow(s)- NF**|1. Administrator navigates to the "Notification Settings" or "Message Templates" section.<br><br>  <br><br>2. System displays current message templates (e.g., "New Inquiry Acknowledgment," "Payment Proof Verified").<br><br>  <br><br>3. For each event, system provides an option to "Edit Template".<br><br>  <br><br>4. Administrator modifies the template text (e.g., adding dynamic placeholders like [ClientName] or [Date] for the pre-filled WhatsApp message).<br><br>  <br><br>5. Administrator saves the changes.<br><br>  <br><br>6. System validates the template changes (e.g., checking for valid placeholder syntax and URL-safe characters for WhatsApp deep linking).<br><br>  <br><br>7. System saves the updated templates to the database.<br><br>  <br><br>8. System displays a success message.|
|**Alternative Flow(s) - AF**|AF1: Template Validation Error:<br><br>  <br><br>1. If edited template content contains invalid syntax (Step 6), system displays an error and prevents saving until corrected.|
|**Exception Flow(s) - EF**|EF1: Error Saving Configuration:<br><br>  <br><br>1. If the system fails to save the updated configurations to the database, it displays an error message.|
|**Post-condition(s)**|System notification templates are updated according to the new configurations and are ready to be generated as pre-filled links.|

```
@startuml
start
:Admin Navigates to Notification Templates;
:System Displays Current WhatsApp Message Templates;
repeat
  :Admin Modifies Templates (e.g., edits text, inserts placeholders);
  :Admin Clicks "Save Templates";
  if (Validate Template Syntax (e.g., valid placeholders)?) then (Valid)
    :System Saves Updated Templates to DB;
    if (Save Successful?) then (Yes)
      :Display "Templates Updated Successfully" Message;
      stop
    else (No - Save Error)
      :Display "Error Saving Templates" Message;
    endif
  else (Invalid Template)
    :Display Template Validation Error Message;
  endif
repeat while
@enduml
```

_Figure 2.33: Activity Diagram for Configure/Manage Notifications_

```
@startuml
actor Administrator as Admin
participant "Frontend (WebUI - AdminPanel)" as UI
participant "Backend (AppServer - NotificationTemplateController)" as App
database "Database (TemplateConfig)" as DB

Admin -> UI : Navigates to Notification Templates, Modifies Text, Clicks Save
UI -> App : POST /api/admin/notifications/templates (NewTemplateData)
App -> App : Validate(NewTemplateData)\ne.g., check placeholder syntax\nand URL-safe character limits
alt Valid
  App -> DB : SaveTemplateConfiguration(NewTemplateData)
  DB --> App : SaveSuccess
  App --> UI : TemplateUpdateSuccessResponse
  UI --> Admin : Displays Success Message
else Invalid
  App --> UI : TemplateValidationErrorResponse
  UI --> Admin : Displays Validation Error Message
end
@enduml
```

_Figure 2.34: Sequence Diagram for Configure/Manage Notifications_

### 2.4 Performance and Other Requirements

This section specifies the non-functional requirements that describe how the Al-Muneer Online Portal should operate, focusing on performance, usability, security, reliability, maintainability, and any other pertinent quality attributes.

#### 2.4.1 Software System Attributes

**Usability:**

- The system shall be intuitive and easy to learn for both Visitor and Administrator user types. Visitors with basic web browsing skills should be able to navigate the portal, find information, and submit inquiries or feedback without specific training.
    
- The Administrator panel shall be designed for ease of content management, requiring minimal training for an individual with general computer and office software proficiency.
    
- Error messages displayed to users shall be clear, understandable, and provide guidance where possible.
    
- The primary language for the public-facing site will be Arabic. Key navigational elements and administrative functions may also be available in English to support broader usability, if deemed feasible within the project timeline. The cultural context of Yemen shall be considered in design elements and information presentation.
    
- The system shall feature a responsive design, ensuring a consistent and usable experience across common devices including desktops, tablets, and smartphones.
    

**Reliability:**

- The system shall be available for use at least 99% of the time, excluding scheduled maintenance periods.
    
- In the event of an error during data submission (e.g., inquiry, feedback, payment proof), the system shall provide an appropriate error message and prevent data loss where possible (e.g., by allowing the user to retry or by preserving entered form data if feasible).
    
- The system's database operations should ensure data integrity. Regular (e.g., daily) backups of the database and uploaded files (payment proofs) should be planned as an operational procedure to prevent data loss in case of system failure.
    

**Maintainability:**

- The system shall be developed using well-structured, commented code following the MVC pattern with Spring Boot for the backend, and clear separation of concerns for the frontend (HTML, CSS, JS).
    
- Configuration settings (e.g., database connection strings, notification service credentials) shall be externalized from the application code where possible to simplify updates.
    

**Portability:**

- As a web-based application, the visitor and admin interfaces shall be accessible using common modern web browsers (e.g., Chrome, Firefox, Safari, Edge) on standard operating systems (Windows, macOS, Linux, Android, iOS).
    
- The backend application (Spring Boot) is Java-based and containerization (e.g., Docker, though not explicitly in PSM1 scope for full implementation) could be considered for future deployment to enhance portability across different hosting environments. The initial deployment target is a Cloud VPS.
    

**Compatibility:**

- The system must be compatible with the chosen database (MySQL or PostgreSQL).
    

#### 2.4.2 Performance Requirements

**Response Time:**

- Standard informational pages (e.g., Venue Info, Gallery thumbnails, Pricing, FAQ) shall load within 3-5 seconds on an average broadband internet connection.
    
- The interactive availability calendar shall update within 2-3 seconds when navigating between months.
    
- Form submissions (inquiry, feedback, payment proof upload) shall be processed and a response (success/error) returned to the user within 5-7 seconds, excluding file upload time which is dependent on file size and user's connection speed.
    
- Admin panel operations (e.g., saving content changes, updating calendar, viewing lists of inquiries) shall complete within 3-5 seconds.
    
- Basic report generation for the admin should complete within 10-15 seconds for typical data volumes anticipated.
    

**Throughput:**

- The system is initially designed to handle up to approximately 10,000 monthly visitors, which translates to a moderate number of concurrent users (e.g., 10-20 simultaneous active sessions during peak times). The system should not exhibit significant performance degradation under this load.
    

**Capacity:**

- The system shall support storage for all venue information, an expanding media gallery (e.g., hundreds of images/videos, with appropriate file size limits per item).
    
- The database shall be capable of storing records for several years of inquiries, bookings, payment proofs, and feedback. Specific storage capacity will depend on the hosting plan chosen for the VPS and database.
    
- The payment proof upload feature shall support common image file types (JPG, PNG) with a maximum file size of, for example, 5MB per file.
    

**Availability:**

- The system is expected to be operational 24 hours a day, 7 days a week.
    
- Scheduled downtime for maintenance (e.g., software updates, backups) should be minimal and planned for off-peak hours where possible.
    

#### 2.4.3 Other Requirements

**3. Security:**

- 3.1 Authentication: The administrator login shall be protected by strong password policies (enforced manually by admin choice initially, but system should support robust hashed storage). Session management should prevent unauthorized access.
    
- 3.2 Authorization: Access to administrative functionalities shall be restricted to authenticated administrators only.
    
- 3.3 Data Encryption: All communication between the client browser and the server shall be encrypted using HTTPS (SSL/TLS). Sensitive data stored in the database (e.g., admin passwords) shall be hashed. Consideration should be given to the secure storage and access control of uploaded payment proof files.
    
- 3.4 Input Validation: The system shall validate all user inputs on both client-side (for immediate feedback) and server-side (for security and integrity) to prevent common web vulnerabilities such as Cross-Site Scripting (XSS) or SQL Injection.
    
- 3.5 Protection against Automated Abuse: Basic measures (e.g., CAPTCHA on forms if spam becomes an issue) may be considered if automated abuse of inquiry or feedback forms is detected post-deployment (out of initial scope for PSM1 implementation planning).
    

**4. Legal and Regulatory:**

- 4.1 The system shall be developed with consideration for general data privacy principles, even if specific data protection laws in Yemen are not as stringent as GDPR. User data collected (names, contact details) should be used solely for the purpose of managing inquiries and bookings for Al-Muneer Hall.
    
- 4.2 The system should not store full payment card details, as it only handles proof of offline payments.
    

**5. Environmental (Not directly applicable to software, but noted if physical server was self-hosted):**

- 5.1 Not applicable as the system will be deployed on a Cloud VPS.
    

### 2.5 Design Constraints

This section outlines any constraints that limit the design choices for the Al-Muneer Online Portal.

- **Development Technology Stack:**
    
    - The backend must be developed using the Spring Boot framework (Java) with an MVC architecture.
        
    - The database must be a relational database, specifically MySQL or PostgreSQL.
        
    - The frontend must be developed using HTML5, CSS3, and JavaScript (without mandatory use of a specific large JavaScript framework like React/Angular/Vue for core PSM1/PSM2 delivery unless student proficiency allows and it significantly benefits the project within the timeline).
        
- **Deployment Environment:** The system is planned for deployment on a Cloud Virtual Private Server (VPS). This choice impacts considerations for server management, security configurations, and cost. Serverless or highly specialized PaaS solutions are not the primary target unless the VPS proves unsuitable.
    
- **Project Timeline:** The system must be developed within the academic timeline of the FYP1 (PSM1 for analysis and design, PSM2 for implementation and deployment) and FYP2. This constrains the complexity of features that can be reliably implemented and tested. Progress 2 submission deadline is May 30, 2025. Final PSM1 report submission is June 26, 2025.
    
- **Solo Developer:** The project is undertaken by a single student developer. This impacts the breadth and depth of features that can be implemented, favoring a focused approach on core functionalities.
    
- **Stakeholder Technical Proficiency:** The admin panel must be designed for ease of use by an administrator (Mr. Almunajid) who may not have extensive technical expertise. Complex configuration options should be avoided unless essential.
    
- **Local Internet Infrastructure (Yemen):** While the portal is web-based, consideration for users with potentially slower or less stable internet connections in Yemen should guide design choices towards reasonably optimized page loads and clear feedback during data submission. This might influence image optimization strategies and the avoidance of overly heavy client-side frameworks if they impede performance significantly on lower-end connections/devices.
    
- **Communication Preferences:** The preference for WhatsApp for notifications over email in the target user environment (Yemen) influences the design of the notification module, favoring simpler or more readily available integration options for these channels.
    
- **No Direct Payment Integration:** A core constraint is that the system will not integrate with online payment gateways or banks due to the local business context (cash and local transfers are king) and project complexity. Only proof of offline payment will be handled.
    

## Appendix A: Evidence of Requirements Elicitation Artefacts

The requirements detailed in this Software Requirements Specification (SRS) document for the Al-Muneer Online Portal were gathered and refined through an iterative process involving direct stakeholder interaction, project documentation, and an understanding of similar systems. This appendix provides a reference to the key artefacts and processes that constitute the evidence of requirements elicitation, structured chronologically by engagement phase.

### A.1 Initial Requirements Elicitation (Pre-SRS - March 15, 2025)

The primary source of initial requirements stemmed from a formal video conference meeting (Google Meet) held on March 15, 2025, between the student developer, Ahmed Ghaleb, and the key stakeholder, Mr. Ahmed Almunajid, owner of Al-Muneer Hall. This session aimed to understand the current operational challenges and Mr. Almunajid's high-level vision for an online presence. This initial engagement was formally documented.

_[Image: Screenshot of WhatsApp chat indicating missed voice calls and messages reading "نحن هنا" and "این انتم" and a discussion about reservations done through phone calls or office visits.]_ _Figure A.1: Google Meet Session for Initial Requirements Elicitation_

During this meeting, while the developer already knows the business of the stakeholder, the current manual processes of Al-Muneer Hall were discussed. It was identified that reservations were primarily handled via phone or in-office visits, leading to frequent, repetitive calls regarding booking and pricing details, and that media (photos/videos) was inefficiently shared via the WhatsApp Business application. Mr. Almunajid articulated a clear vision for an online presence: a website to modernize and promote his business. Key desired functionalities included an interactive calendar to display available and booked slots, a system for the owner to manually set and change base prices for booking slots, and a feature allowing customers to transparently view all pricing options based on their selections. Furthermore, he emphasized the need for a "fancy" website to effectively showcase the hall's photos and videos, along with essential details like location and contact information.

_[Image: Screenshot of WhatsApp chat detailing current system issues and what the owner wants. The text notes repetitive calls, media shared over WhatsApp Business, and the owner's desire for a website with an interactive calendar, dynamic pricing, and a media gallery.]_ _Figure A.2: Key Findings from Initial Stakeholder Meeting_

The developer's initial understanding from this meeting was that the stakeholder aimed to enhance business promotion through an appealing online presence and to alleviate the burden of repetitive manual inquiries by transforming them into automated website functionalities.

### A.2 Refinement and Clarification (Post-SRS Interview - August 06, 2025)

Following the initial draft of the SRS and subsequent internal review, further discussions were conducted, primarily via asynchronous communication and a focused interview, to refine specific requirements and clarify ambiguities. This "post-SRS interview" aimed to drill down into the details of complex features, particularly pricing, and ensure alignment with practical business needs. During this subsequent phase, it was clarified that pricing is dynamic, varying by demand and subject to administrative changes, and is also significantly influenced by the specific "type & size of wedding," necessitating predefined packages. This consolidated understanding confirmed that pricing would be determined by both the administrative-specified package type and the demand-driven availability of dates. The client interaction flow was detailed: visitors would first select a desired date, then choose a pricing tier (or opt for direct consultation), with tiers serving to provide transparent pricing guidelines. The typical pathway for a client would involve selecting the event type before choosing a package, or engaging in direct communication for bespoke arrangements. To ensure a comprehensive understanding and capture nuanced requirements, the developer proactively asked probing questions. These included inquiries about specific daily pain points the website should address beyond general promotion, a deeper exploration of how pricing currently varies (e.g., by date, event type, or add-on services), details on the information exchanged during booking inquiries, current booking confirmation and payment processes, desired basic reports for business insights, and existing methods for gathering client feedback.

### A.3 Project Proposal Documents

The initial project proposal documents served as a foundational baseline for requirements, developed collaboratively with the stakeholder and project supervisor. Specifically, the Project Proposal & Supervision Consent Form (PSM1.PF_.05u-1) outlined the preliminary project idea, problem statement, proposed solution, and high-level objectives for the Al-Muneer Online Portal, capturing the initial understanding of the stakeholder's needs (Version 1.0, March 2025). This was further expanded by the NABC Supplement Document (PSM1.PF_.05u-1 NABC Supplement Document v2.1), which detailed the Need, Approach, Benefits, and Competition. It provided deeper context on the problem, a refined solution, and crucial evidence of stakeholder agreement, including translated communications. This supplement was subsequently updated to incorporate examiner feedback, leading to the inclusion of features such as payment proof, feedback, reporting, and notifications (Version 2.1, March 2025, with updates).

### A.4 FYP1 Progress Reports

The structuring and detailing of gathered requirements, an iterative process of analysis and refinement, were significantly advanced through the development of the FYP1 Progress Reports. The FYPI Progress 1 Report (Chapters 1 & 2) documented the project's initial problem background, objectives, scope, and a contextualizing literature review. Notably, its Case Study section elaborated on the stakeholder and the outcomes of the initial engagement (May 2025). Building on this, the FYPI Progress 2 Report (Chapters 3, 4, & 5), which forms the direct basis for Section 2 of this SRS with its detailed Requirements Analysis (Chapter 4), system design, and methodology, marked a crucial step in formalizing and documenting the elicited requirements to a granular level (May 2025).

### A.5 Comparative Analysis of Existing Systems (Implied Requirements)

During the literature review and project planning phase, an analysis of existing online venue management systems, booking platforms, and typical features of business websites was conducted. While not a direct elicitation method from the stakeholder, this analysis proved instrumental in informing discussions about generally expected features and potential value-added functionalities. These insights were then validated or adapted based on Mr. Almunajid's specific context and needs (e.g., the preference for simple payment proof over full online payment). (Reference: FYPI Progress 1 Report, Chapter 2 - Literature Review).