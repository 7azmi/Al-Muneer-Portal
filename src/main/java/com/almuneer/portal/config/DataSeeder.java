package com.almuneer.portal.config;

import com.almuneer.portal.model.AdminUser;
import com.almuneer.portal.model.FaqItem;
import com.almuneer.portal.model.GalleryLabel;
import com.almuneer.portal.model.NotificationTemplate;
import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.repository.AdminUserRepository;
import com.almuneer.portal.repository.GalleryLabelRepository;
import com.almuneer.portal.repository.NotificationTemplateRepository;
import com.almuneer.portal.repository.VenueInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.almuneer.portal.util.FaqJsonUtil;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final VenueInfoRepository venueInfoRepository;
    private final NotificationTemplateRepository templateRepository;
    private final GalleryLabelRepository galleryLabelRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.maps.share-url:https://maps.google.com}")
    private String defaultMapsShareUrl;

    @Value("${app.maps.embed-url:https://www.google.com/maps?q=Ibb+Yemen&output=embed}")
    private String defaultMapsEmbedUrl;

    @Override
    public void run(String... args) {
        // Seed default admin user
        if (!adminUserRepository.existsByUsername("admin")) {
            AdminUser admin = AdminUser.builder()
                    .username("admin")
                    .passwordHash(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .build();
            adminUserRepository.save(admin);
            log.info("Default admin user created (username: admin, password: admin123)");
        }

        // Seed default venue info (singleton)
        if (venueInfoRepository.count() == 0) {
            VenueInfo venue = VenueInfo.builder()
                    .description("Al-Muneer Hall is the premier wedding and events venue in Ibb, Yemen. " +
                            "With elegant interiors and dedicated service, we make every occasion unforgettable.")
                    .services("Wedding Receptions, Engagement Parties, Corporate Events, Private Gatherings")
                    .capacity(500)
                    .location("Ibb, Yemen")
                    .contactInfo("+967-XXX-XXX-XXX")
                    .mapsShareUrl(defaultMapsShareUrl)
                    .mapsEmbedUrl(defaultMapsEmbedUrl)
                    .faqJson(FaqJsonUtil.serialize(List.of(
                            FaqItem.builder()
                                    .question("What is the maximum capacity of the hall?")
                                    .answer("Al-Muneer Hall can accommodate up to 500 guests. Contact us if your event size differs.")
                                    .build(),
                            FaqItem.builder()
                                    .question("How do I book a date?")
                                    .answer("Check availability on our home page calendar, then submit a booking inquiry. We will confirm via WhatsApp.")
                                    .build(),
                            FaqItem.builder()
                                    .question("How is payment handled?")
                                    .answer("We do not process online payments. After your inquiry is accepted, upload a bank transfer receipt on the payment proof page for manual verification.")
                                    .build(),
                            FaqItem.builder()
                                    .question("Can I cancel my inquiry?")
                                    .answer("Yes — you can cancel from your inquiry confirmation page using your 9-digit reference code, as long as no payment proof has been uploaded yet.")
                                    .build()
                    )))
                    .build();
            venueInfoRepository.save(venue);
            log.info("Default venue info record created");
        } else {
            venueInfoRepository.findAll().forEach(venue -> {
                boolean changed = false;
                if (venue.getMapsShareUrl() == null || venue.getMapsShareUrl().isBlank()) {
                    venue.setMapsShareUrl(defaultMapsShareUrl);
                    changed = true;
                }
                if (venue.getMapsEmbedUrl() == null || venue.getMapsEmbedUrl().isBlank()) {
                    venue.setMapsEmbedUrl(defaultMapsEmbedUrl);
                    changed = true;
                }
                if (venue.getFaqJson() == null || venue.getFaqJson().isBlank()
                        || "[]".equals(venue.getFaqJson().trim())) {
                    venue.setFaqJson(FaqJsonUtil.serialize(List.of(
                            FaqItem.builder()
                                    .question("What is the maximum capacity of the hall?")
                                    .answer("Al-Muneer Hall can accommodate up to 500 guests. Contact us if your event size differs.")
                                    .build(),
                            FaqItem.builder()
                                    .question("How do I book a date?")
                                    .answer("Check availability on our home page calendar, then submit a booking inquiry. We will confirm via WhatsApp.")
                                    .build(),
                            FaqItem.builder()
                                    .question("How is payment handled?")
                                    .answer("We do not process online payments. After your inquiry is accepted, upload a bank transfer receipt on the payment proof page for manual verification.")
                                    .build(),
                            FaqItem.builder()
                                    .question("Can I cancel my inquiry?")
                                    .answer("Yes — you can cancel from your inquiry confirmation page using your 9-digit reference code, as long as no payment proof has been uploaded yet.")
                                    .build()
                    )));
                    changed = true;
                }
                if (changed) {
                    venueInfoRepository.save(venue);
                    log.info("Venue map URLs backfilled from application defaults");
                }
            });
        }

        // Seed default notification templates
        if (templateRepository.count() == 0) {
            List<NotificationTemplate> templates = List.of(
                NotificationTemplate.builder()
                    .eventName("INQUIRY_RECEIVED")
                    .label("Inquiry Received")
                    .templateText("Hello {visitorName},\n\nThank you for your inquiry (ID: #{inquiryId}) for the date {eventDate}. " +
                            "We have received your request and will get back to you shortly.\n\nBest regards,\nAl-Muneer Hall")
                    .build(),
                NotificationTemplate.builder()
                    .eventName("PAYMENT_PENDING")
                    .label("Awaiting Payment")
                    .templateText("Hello {visitorName},\n\nGreat news! We have tentatively reserved the date {eventDate} for you (Inquiry #{inquiryId}). " +
                            "Please upload your payment proof at: [your site URL]/payment/upload?inquiryId={inquiryId}\n\nAl-Muneer Hall")
                    .build(),
                NotificationTemplate.builder()
                    .eventName("PAYMENT_VERIFIED")
                    .label("Payment Verified — Booking Confirmed")
                    .templateText("Hello {visitorName},\n\nYour payment for {eventDate} has been verified and your booking (#{inquiryId}) is now CONFIRMED. " +
                            "We look forward to hosting your event!\n\nAl-Muneer Hall")
                    .build(),
                NotificationTemplate.builder()
                    .eventName("PAYMENT_REJECTED")
                    .label("Payment Rejected")
                    .templateText("Hello {visitorName},\n\nUnfortunately, we were unable to verify the payment proof for your inquiry #{inquiryId}. " +
                            "Please re-upload a clear receipt at: [your site URL]/payment/upload?inquiryId={inquiryId}\n\nAl-Muneer Hall")
                    .build()
            );
            templateRepository.saveAll(templates);
            log.info("Default notification templates seeded (4 templates)");
        }

        // Seed default gallery labels
        if (galleryLabelRepository.count() == 0) {
            java.util.List<GalleryLabel> defaultLabels = java.util.List.of(
                GalleryLabel.builder().name("Weddings").icon("💍").sortOrder(0).build(),
                GalleryLabel.builder().name("Hall Setup").icon("🏛️").sortOrder(1).build(),
                GalleryLabel.builder().name("Decorations").icon("🌸").sortOrder(2).build(),
                GalleryLabel.builder().name("Exterior").icon("🏡").sortOrder(3).build(),
                GalleryLabel.builder().name("Events").icon("🎉").sortOrder(4).build()
            );
            galleryLabelRepository.saveAll(defaultLabels);
            log.info("Default gallery labels seeded (5 labels)");
        }
    }
}

