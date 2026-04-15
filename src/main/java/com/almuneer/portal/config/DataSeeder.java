package com.almuneer.portal.config;

import com.almuneer.portal.model.AdminUser;
import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.repository.AdminUserRepository;
import com.almuneer.portal.repository.VenueInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final VenueInfoRepository venueInfoRepository;
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
    }
}
