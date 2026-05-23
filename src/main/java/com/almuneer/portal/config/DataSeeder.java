package com.almuneer.portal.config;

import com.almuneer.portal.model.AdminUser;
import com.almuneer.portal.model.NotificationTemplate;
import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.repository.AdminUserRepository;
import com.almuneer.portal.repository.NotificationTemplateRepository;
import com.almuneer.portal.repository.VenueInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final VenueInfoRepository venueInfoRepository;
    private final NotificationTemplateRepository templateRepository;
    private final PasswordEncoder passwordEncoder;

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
                    .faqJson("[]")
                    .build();
            venueInfoRepository.save(venue);
            log.info("Default venue info record created");
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
    }
}

