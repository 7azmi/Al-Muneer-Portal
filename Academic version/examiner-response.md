# Response to Examiners' Comments

**Project:** Al-Muneer Hall Online Portal
**Student:** Ahmed Ghaleb
**Date:** 16 July 2026

---

## Examiner 1 — AP Dr. Radziah bt. Mohamad

| # | Comment | Response / Action Taken |
|---|---------|------------------------|
| i | Bad formatting of Table of Contents. | Accepted. The Table of Contents has been regenerated and reformatted in the revised manuscript according to the UTM thesis template. |
| ii | Bad formatting of the report. Follow the UTM Thesis format. | Accepted. The entire manuscript has been reformatted to comply with the UTM Thesis Manual (margins, headings, captions, pagination, and front matter). |
| iii | Rewrite the "Why Chosen" column in Table 2.2; remove informal AI-style filler and non-academic quotes (e.g., Terry Davis quote). | Accepted. The entire justification column of Table 2.2 has been rewritten with formal, data-backed engineering justifications supported by literature (Fowler, 2002; Garcia-Molina et al., 2008; Royce, 1970; W3C, 2018; Armbrust et al., 2010; Walls, 2016). All informal remarks and the non-academic quote have been removed. |
| iv | Alternative and Exception flows in the use case specifications are not explicitly triggered from conditional steps within the Normal Flow. Use Visual Paradigm to model the sequence diagram and all requirements diagrams. | Accepted. All 17 use case specifications (UC001–UC017) have been revised: every conditional step in each Normal Flow now carries an explicit branch marker (e.g., "[If validation fails → AF1]"), and every Alternative/Exception flow states its triggering Normal Flow step and its termination (resume step or use case end). All requirements diagrams (use case, activity, and sequence) have been re-modeled in Visual Paradigm, with branch labels traceable to the numbered flow steps. |
| v | Add GeminiAI as one of the actors in the use case diagram and the AI function as one of the use cases. | Accepted. The use case diagram (Figure 4.1 in the report and Figure 2.1 in the SRS) now includes "Gemini AI (External AI Service)" as a secondary actor and "UC017: Generate AI Insights" as a use case. A full use case specification for UC017 (with activity and sequence diagrams) has been added as Section 2.3.17 of the SRS, and a corresponding functional requirement (FR2.11) has been added to Chapter 4. |

## Examiner 2 — Dr. Mohd Razak bin Samingan

| # | Comment | Response / Action Taken |
|---|---------|------------------------|
| 1 | Project objectives are too long, too verbose, and include implementation details; rewrite them (objective 1 as suggested). | Accepted. Section 1.4 has been rewritten into four concise objectives free of implementation detail. Objective 1 adopts the examiner's suggested wording verbatim. The achievement-of-objectives discussion in Chapter 6 has been aligned with the new structure. |
| 2 | Set paragraph line spacing to 1.5. | Accepted. Line spacing has been set to 1.5 throughout the revised manuscript. |
| 3 | Booking page shows a button labelled "Submit Inquiry". | Accepted. The action has been relabelled "Submit Booking" throughout the visitor interface (booking form, calendar call-to-action, and landing page), and all corresponding references in the report and SRS have been updated. |
| 4 | Not user-friendly to check a booking: users must remember and enter the given reference number. | Accepted. The system has been enhanced so that visitors retrieve their bookings by simply entering their own WhatsApp number — the same number used at submission — with no code to memorise. The 9-digit reference code remains available as a secondary lookup and for payment-proof deep links, and returning visitors on the same device continue to see their saved booking automatically via a persistent cookie. This enhancement is documented in Section 5.2.2. |
| 5 | Many issues with UI/UX and task flow. | *(To be answered by the student in their own words.)* |
