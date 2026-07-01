# Al-Muneer Online Portal: A Web-Based Booking and Management System for an Event Venue

**Ahmed Hani Ahmed Ghaleb**
Faculty of Computing
Universiti Teknologi Malaysia
81310 Johor Bahru, Malaysia
ahmed@graduate.utm.my

**Dr. Muhammad Luqman bin Mohd Shafie**
Faculty of Computing
Universiti Teknologi Malaysia
81310 Johor Bahru, Malaysia
luqman@utm.my

**Abstract**— This paper presents the Al-Muneer Online Portal, a web-based booking and management platform developed for Al-Muneer Hall for Weddings and Events, a single-site venue in Ibb, Yemen. The hall's operations currently rely on manual phone calls and direct messaging to manage inquiries, availability, and payment confirmations, which consumes a disproportionate amount of the owner's time and offers no centralized way for prospective clients — particularly female stakeholders who, due to local cultural norms, prefer to assess a venue remotely — to explore the venue or track a booking. The Al-Muneer Online Portal addresses this by consolidating venue information, an interactive availability calendar, pricing, an FAQ section, and a media gallery into a single visitor-facing interface, while adding structured workflows for booking-inquiry submission, optional payment-proof upload (screenshots of local bank transfers, reflecting a cash-dominant economy with limited formal banking penetration), and post-consultation feedback. For the administrator, the system provides a secure panel to manage all content, availability, and pricing; review inquiries and payment proofs; generate WhatsApp deep-link notifications pre-filled from configurable templates; and view operational reports and traffic analytics enhanced with Gemini-generated advisory insights. The portal was implemented as a monolithic Spring Boot (Java) application following the Model-View-Controller pattern, backed by a PostgreSQL database, and secured with JWT-based administrator authentication. The completed system was verified through white-box, black-box, and stakeholder user testing, with all 38 documented test cases across 17 functional modules passing. The result is a culturally adapted, low-overhead digital front door for a small venue business that reduces the owner's administrative burden while giving clients a self-service way to evaluate and book the hall.

## I. Introduction

Small, owner-operated event venues in many parts of the world continue to run their client-facing operations almost entirely through voice calls and instant messaging. This is workable at low volume, but it does not scale: every inquiry about a date, a price, or a package requires the owner's direct attention, and there is no persistent record that a client, or the owner, can refer back to later. As clients increasingly expect to research and initiate a booking on their own time, venues that cannot offer any digital self-service option are put at a disadvantage relative to larger, better-resourced competitors, even when the manual approach still "works" from the owner's point of view.

This problem is sharpened by context. In a market where formal online payment gateways are not widely used, booking confirmation instead depends on informal, in-person, or verbally negotiated steps that are hard to track. And in a socially conservative setting, some prospective clients — particularly female family members who are often heavily involved in choosing a wedding venue — may prefer not to visit or call a venue directly before a booking decision is made, and instead want to evaluate it remotely through photos, pricing, and written information. A purely manual, phone-first process serves this group poorly.

### A. Problem Background

Al-Muneer Hall for Weddings and Events ("قاعة المنير للأفراح والمناسبات"), located in Ibb, Yemen, is an owner-operated venue whose booking, inquiry, and payment-confirmation processes are handled manually by the owner, Mr. Ahmed Almunajid. The owner spends a considerable amount of time answering repetitive questions about availability, pricing, capacity, and appearance, and there is no systematic way to track booking confirmations, collect client feedback, or review basic operational trends. Because the local economy is cash-dominant and formal online banking is limited, booking confirmation increasingly relies on local bank transfers verified through a screenshot sent to the owner directly — a workflow that, absent a dedicated platform, has no structured place to live. Combined with the cultural preference of some clients for remote assessment rather than an in-person visit before deciding, the lack of a centralized, visually rich, self-service platform limits both the venue's operational efficiency and its accessibility to certain client segments.

### B. Project Importance

The Al-Muneer Online Portal addresses operational inefficiency by automating the distribution of venue information and structuring processes — booking-inquiry handling, payment-proof review, and feedback collection — that were previously ad hoc. It improves the client experience by making detailed venue information available around the clock rather than only during the owner's working hours. Most importantly for this context, it functions as an inclusivity tool: it enables remote venue assessment for clients who prefer not to engage by phone or in person, and it formalizes a payment-confirmation method (transfer-screenshot upload) that is already familiar to the local population rather than imposing an unfamiliar payment gateway. Structured feedback and basic reporting further give the owner a continuous, low-effort way to understand and improve service quality.

### C. Project Objectives

- To elicit and document the functional and non-functional requirements of the Al-Muneer Hall owner and prospective clients, covering the availability calendar, pricing display, media gallery, FAQ, inquiry submission, payment-proof upload, feedback, and admin-side reporting and notification management.
- To design a system architecture — including the database schema and the visitor/administrator interface design — that translates these requirements into a robust, intuitive, and culturally appropriate platform.
- To implement a functional, monolithic web application using Spring Boot and the MVC pattern, covering both the client-facing features and the secure administrative controls.
- To test and validate the completed system through white-box, black-box, and stakeholder user testing to confirm it meets the documented requirements and behaves reliably across the core workflows.
- To deploy the completed portal to a production Cloud VPS environment, accessible via standard web browsers with a responsive layout for desktop and mobile clients.

## II. Literature Review

This section reviews the category of system that Al-Muneer Hall currently relies on and the categories of alternative systems it could adopt instead of a purpose-built portal, in order to position the value that the Al-Muneer Online Portal specifically contributes.

### A. Current System (Manual) Analysis

The manual process presently used at Al-Muneer Hall is representative of many small and medium-sized venue businesses. It suffers from several structural weaknesses: staff time is consumed by repetitive queries and by manually tracking bookings, payments, and feedback; information and process progress are tied to the owner's availability, so nothing can happen outside working hours; the information given to different clients can be inconsistent, and there is no standardized way to track booking confirmations or feedback; the approach does not scale gracefully as inquiry volume grows; clients increasingly expect a streamlined, self-service online interaction rather than a phone call; and, because everything is tracked informally, the owner has no reliable way to generate operational insight or systematically follow up on past client interactions.

### B. Generic Booking Platforms

Third-party platforms such as Eventbrite and Cvent offer standardized interfaces for event listing, ticketing, and, in some cases, broader event management. While capable, they impose their own branding, fee structures, and workflows, which do not necessarily correspond to the specific needs, pricing model, or cultural context of an independent venue, and their primary emphasis on ticketed events sits awkwardly against a tailored venue-rental inquiry process such as Al-Muneer Hall's.

### C. Website Builders

General-purpose website builders such as Wix, Squarespace, and GoDaddy Website Builder let small businesses construct a website quickly using templates and drag-and-drop tools, and are effective for establishing a basic informational presence. However, their built-in support for dynamic, venue-specific processes — a custom availability calendar, tailored pricing logic, an integrated payment-proof workflow, or a backend reporting dashboard — is typically limited unless extended with third-party plugins, which add cost and complexity that a small venue business may not be equipped to manage.

### D. Static Competitor Websites

Many local venue businesses instead rely on static, brochure-style websites that display contact details, a gallery, and a list of services, but that offer no interactive functionality. These sites are purely informational: checking availability, obtaining pricing, or submitting a structured inquiry still requires the visitor to fall back on a phone call or a direct message, which reproduces the same bottleneck the manual process already has.

### E. Comparison Between Existing Systems

**Table II-1. Comparison Between the Al-Muneer Online Portal and Existing Approaches**

| Feature | Al-Muneer Portal (Proposed) | Manual (Phone/WhatsApp) | Generic Booking Platforms | Website Builders | Static Competitor Site |
|---|---|---|---|---|---|
| Interactive availability calendar | Yes | No | Often, with complex setup | Limited / plugin-dependent | Usually no |
| Dynamic pricing display | Yes, admin-managed | Verbal / manual | Sometimes, complex | Usually no | Usually no |
| Custom admin panel | Yes, purpose-built | N/A | Platform-specific | General CMS only | N/A |
| Payment-proof workflow | Yes, simple upload + admin verification | Manual exchange, untracked | Often not direct | No | No |
| Feedback system | Yes, structured | Ad hoc | Sometimes, platform-level | Usually no | No |
| Basic reporting | Yes, with AI-assisted insights | No | Limited (platform-level) | No | No |
| Client notifications | Admin-triggered WhatsApp deep-links | Manual | Email-centric | No | No |
| Cultural/local-context fit | High (custom-built) | High (direct human interaction) | Low (standardized) | Medium (template-dependent) | Medium |

The manual process offers direct human contact but no structure, no persistent record, and no way to scale. Generic booking platforms and website builders bring some of the missing structure but are not built around a cash-dominant payment context or WhatsApp-first communication, and they add cost or complexity disproportionate to a single-site venue's needs. The Al-Muneer Online Portal is deliberately narrower and more tailored than any of these alternatives: its payment-proof upload workflow matches how clients already pay locally, and its notification mechanism generates pre-filled WhatsApp messages rather than relying on email or a paid messaging API, matching the channel clients and the owner already use. This combination — rather than any single feature in isolation — is what differentiates the portal from the available alternatives.

## III. System Development Methodology

### A. Technology Used

The Al-Muneer Online Portal is implemented as a single, monolithic web application in Java 21 using Spring Boot 3.4.4 with Maven, following the Model-View-Controller (MVC) architectural pattern. The backend layer is organized into controllers, services, repositories, and JPA entities covering venue information, media, availability, pricing, inquiries, payment proofs, feedback, notification templates, and page-visit analytics. Data is persisted in PostgreSQL 16, chosen for its relational integrity guarantees, which suit the structured nature of bookings, pricing, and payment-status data. The View layer is rendered server-side with Thymeleaf, HTML5, and CSS3, and enhanced with vanilla JavaScript and Chart.js for interactive calendar behavior and data visualization. Administrator authentication is secured with Spring Security using JWT tokens stored in HTTP-only cookies, with passwords hashed via BCrypt. Uploaded payment-proof images are handled by a dedicated file-upload utility and stored under a managed uploads directory. Client notifications are generated as pre-filled WhatsApp deep-links (`wa.me`) built from admin-configurable message templates, rather than through a paid messaging API, to match local communication habits at minimal cost. Optional AI-assisted insights for the administrator's reports, feedback overview, and traffic funnel are generated through the Google GenAI Java SDK against the Gemini model, loaded asynchronously so that the core interface remains fast and degrades gracefully if the AI service is unavailable.

### B. Methodology

The project followed the Waterfall model, selected because the venue owner's requirements were well understood and largely fixed at the outset, and because a linear, document-driven process suited both the academic milestone structure of the project and the priority placed on data reliability for a system handling booking and payment-status information. The lifecycle proceeded through Requirements Analysis and Definition, System and Software Design, Implementation and Unit Testing, Integration and System Testing, Deployment, and Maintenance and Operation, with the requirements and design phases producing the Software Requirements Specification (SRS) and System Design Document (SDD) that guided implementation.

_[Diagram: Waterfall model workflow, showing the six sequential phases from Requirements Analysis through Deployment to Maintenance & Operation]_
**Figure III-1.** Waterfall Model Workflow for the Al-Muneer Online Portal

### C. Software Requirements

**Table III-1. Software Requirements**

| Category | Development Environment | Deployment (Cloud VPS) |
|---|---|---|
| Operating System | Windows, macOS, or Linux | Linux (e.g., Ubuntu) |
| Runtime | JDK 11/17/21, Maven/Gradle | Java Runtime Environment (JRE) |
| Database | MySQL / PostgreSQL (local instance) | MySQL / PostgreSQL server |
| Other Tools | IDE (IntelliJ IDEA / Eclipse / VS Code), Git, modern web browser | Embedded Spring Boot server (Tomcat), SSL/TLS certificate, firewall/SSH |

### D. Hardware Requirements

**Table III-2. Hardware Requirements**

| Component | Development Machine | Deployment (Cloud VPS) |
|---|---|---|
| Processor | Intel i5 / AMD Ryzen 5 or better | 1–2 vCPUs |
| RAM | 8 GB minimum (16 GB recommended) | 2–4 GB |
| Storage | 256 GB SSD or larger | 20–50 GB SSD |

## IV. Requirement Analysis & Design

### A. Use Case Table

**Table IV-1. Al-Muneer Online Portal Use Cases**

| Use Case ID | Use Case Name | Description |
|---|---|---|
| UC001 | View Venue Information | Displays hall description, services, capacity, and location to visitors. |
| UC002 | View Media Gallery | Lets visitors browse image and video content of the hall. |
| UC003 | Check Availability | Provides an interactive calendar showing booked and available dates. |
| UC004 | View Pricing Panel | Displays pricing tiers and package details to visitors. |
| UC005 | Submit Booking Inquiry | Lets a visitor submit a booking request, generating a reference code. |
| UC006 | Manage Hall Information | Lets the admin create, update, and delete venue description and FAQ content. |
| UC007 | Manage Media Gallery | Lets the admin upload or delete gallery images and videos. |
| UC008 | Manage Pricing Panel | Lets the admin create and update pricing tiers. |
| UC009 | Manage Calendar & Inquiries | Lets the admin update availability slots and review submitted inquiries. |
| UC010 | View Traffic Analytics | Shows page-visit charts and an AI-generated traffic funnel advisory. |
| UC011 | Submit Payment Proof | Lets a visitor upload a payment-transfer screenshot against an inquiry. |
| UC012 | Submit Feedback | Lets a visitor submit a rating and written feedback. |
| UC013 | Manage Payment Status | Lets the admin review payment proofs and verify or reject them. |
| UC014 | View / Generate Reports | Shows inquiry, payment, and feedback report charts with an AI business advisory. |
| UC015 | Manage Feedback | Lets the admin review submitted feedback, supported by an AI feedback advisory. |
| UC016 | Configure / Manage Notifications | Lets the admin manage WhatsApp message templates and trigger pre-filled notifications. |

### B. Use Case Diagram

_[Diagram: Use case diagram with two actors, Visitor and Admin, connected to the sixteen use cases listed in Table IV-1; UC014 includes UC010]_
**Figure IV-1.** Use Case Diagram for the Al-Muneer Online Portal

### C. System Architecture

The portal follows a Model-View-Controller architecture within a single Spring Boot application. Controllers handle incoming HTTP requests and delegate to a service layer that contains the business logic (booking rules, the payment-verification status cascade, notification-template resolution, and AI-advisory orchestration). Services interact with a repository layer built on Spring Data JPA, which manages persistence for all domain entities in the PostgreSQL database. The View layer is rendered server-side through Thymeleaf templates returned directly by the controllers, giving the backend direct control over what is presented to visitors and the administrator. Supporting modules handle file storage for uploaded payment proofs and gallery media, and outbound WhatsApp deep-link generation.

_[Diagram: MVC/package-level architecture diagram — a rendered class/package view already exists in this folder as `package_diagram.png` and `design_view_diagram.png` and can be adapted, or replace with the preferred diagram from the Google Doc version]_
**Figure IV-2.** System Architecture Diagram (MVC)

## V. System Implementation and Testing

### A. Interface of System Main Functions

This section presents the interfaces demonstrating the completed system's core functionality for both visitor and administrator roles.

**a) Visitor Home Page**
The home page is a single-page, scrollable layout with anchor navigation across the hero section, venue information, media gallery, availability calendar, and pricing packages.

_[Screenshot: Visitor home page, single-page scroll layout]_
**Figure V-1.** Visitor Home Page

**b) Availability Calendar and Inquiry Prompt**
The interactive calendar distinguishes available, pending, and booked dates; selecting an available date surfaces a "Submit inquiry" call-to-action that carries the date into the inquiry form.

_[Screenshot: Availability calendar with an available date selected]_
**Figure V-2.** Availability Calendar and Inquiry Call-to-Action

**c) Booking Inquiry Form**
The inquiry page accepts new submissions with the date and/or pricing package pre-filled, and on submission returns a 9-digit reference code the visitor can use to look up or cancel their inquiry later.

_[Screenshot: Inquiry form with pre-filled date and package]_
**Figure V-3.** Booking Inquiry Form

**d) Administrator Dashboard**
The dashboard gives the owner a daily operational overview: new and active inquiries, pending payment proofs, unreviewed feedback, and recent site traffic.

_[Screenshot: Administrator dashboard overview]_
**Figure V-4.** Administrator Dashboard Overview

**e) Administrator Inquiry Management**
The inquiry management panel provides status filter chips with per-status counts, search, and a detail view where the admin updates status and sends a WhatsApp notification from a template.

_[Screenshot: Administrator inquiry management list with status filters]_
**Figure V-5.** Administrator Inquiry Management Panel

**f) Payment Proof Verification**
The payment verification screen shows the uploaded receipt image; verifying a proof triggers an automatic status cascade that confirms the inquiry and marks the corresponding availability slot as booked.

_[Screenshot: Payment proof verification screen]_
**Figure V-6.** Payment Proof Verification

**g) Reports and Analytics with AI Advisory**
The reports page shows inquiry-status, payment-status, and feedback-rating charts with date-range filtering, alongside an asynchronously loaded AI-generated business advisory panel.

_[Screenshot: Reports page with charts and AI advisor panel]_
**Figure V-7.** Reports Page with Visual Charts and AI Advisory

### B. Testing

The Al-Muneer Online Portal was tested to confirm that the implemented system satisfies the requirements defined in the SRS and behaves correctly across desktop and mobile browsers. Testing combined three approaches: white-box testing of internal logic and code paths, black-box testing of externally observable behavior across the REST/UI surface, and user testing conducted directly with the venue owner. Across the resulting Software Test Documentation (STD), **38 test cases spanning 17 functional modules were executed, with all 38 passing** on final verification.

**a) White Box Testing**
White-box testing examined internal logic for the modules most critical to correctness and security: unique 9-digit reference-code generation for each booking inquiry; WhatsApp number normalization, which strips non-digit characters and prepends the configured country code; the atomic status cascade triggered when an administrator verifies a payment proof, which updates the linked inquiry to confirmed and the associated availability slot to booked; JWT token creation, validation, and expiration handling; and the graceful fallback behavior of the AI advisory service when the external Gemini API is unavailable.

**b) Black Box Testing**
Black-box testing exercised the system end-to-end without reference to internal code. Scenarios covered the visitor flow (submitting an inquiry with valid and invalid data, arriving at the inquiry form with a pre-filled date or package, looking up an inquiry by reference code, and self-cancelling an inquiry); the payment-proof flow (uploading valid and invalid file types, and confirming the verification status cascade); the admin flow (login, managing venue content and gallery, updating calendar slots, filtering inquiries, sending WhatsApp notifications from templates, and generating reports); and error handling (invalid credentials, missing required fields, oversized uploads, and unauthorized access attempts), all of which produced clear, user-facing error messages.

**c) User Testing**
User testing was conducted directly with the project stakeholder, Mr. Ahmed Almunajid, who performed representative administrator tasks — updating venue information, uploading gallery images, reviewing new inquiries, verifying a payment proof, and viewing reports. Feedback on interface clarity, workflow efficiency, and the usefulness of the AI advisory panels informed minor adjustments to dashboard ordering and default notification templates.

## VI. Conclusion

The Al-Muneer Online Portal was designed, implemented, and deployed to replace a manual, phone-and-messaging-based booking process at a single-site event venue with a structured, self-service web platform. All core modules identified during requirements analysis were delivered: visitor-facing venue information, media gallery, availability calendar, and pricing display; booking-inquiry submission with reference-code tracking; payment-proof upload matched to local transfer practices; feedback collection; and, on the administrator side, secure content and calendar management, payment verification with an automatic status cascade, WhatsApp-based notification templates, and operational reporting augmented with Gemini-generated advisory insights. The completed system was verified through white-box, black-box, and stakeholder user testing, with all 38 documented test cases passing, and was deployed to a Cloud VPS for production use. Future work identified for the platform includes integrating a formal online payment gateway to remove reliance on manually verified transfer screenshots, adopting the official WhatsApp Business API for automated two-way notifications, adding full Arabic/English localization, and extending the analytics module with conversion-funnel and cohort analysis to give the owner deeper operational insight.

## References

1. Buhalis, D., & Law, R. (2008). Progress in information technology and tourism management: 20 years on and 10 years after the Internet — The state of eTourism research. *Tourism Management*, 29(4), 609–623.
2. Fowler, M. (2002). *Patterns of Enterprise Application Architecture*. Boston, MA: Addison-Wesley Professional.
3. Gazzaroli, D., Lee, G., & Morrison, A. M. (2019). The impact of third-party online platforms on the European event industry: A focus on Eventbrite. *International Journal of Event and Festival Management*, 10(3), 238–253.
4. Kshetri, N. (2013). Privacy and security issues in cloud computing: The role of institutions and institutional evolution. *Telecommunications Policy*, 37(4–5), 372–386.
5. Laudon, K. C., & Laudon, J. P. (2016). *Management Information Systems: Managing the Digital Firm*. Harlow, UK: Pearson.
6. Nielsen, J. (2000). *Designing Web Usability*. Indianapolis, IN: New Riders Publishing.
7. Odoom, R., Anning-Dorson, T., & Acheampong, G. (2017). Antecedents of social media usage and performance benefits in small and medium-sized enterprises (SMEs). *Journal of Enterprise Information Management*, 30(3), 383–399.
8. Royce, W. W. (1970). Managing the development of large software systems. *Proceedings of IEEE WESCON 26*, August, 1–9.
9. W3C. (2018). *Web Content Accessibility Guidelines (WCAG) 2.1*. Retrieved from https://www.w3.org/TR/WCAG21/
