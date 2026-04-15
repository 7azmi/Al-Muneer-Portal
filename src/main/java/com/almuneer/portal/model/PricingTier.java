package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pricing_tiers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PricingTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pricingId;

    @Column(nullable = false)
    private String tierName;

    @Column(nullable = false)
    private java.math.BigDecimal basePrice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isActive = true;
}
