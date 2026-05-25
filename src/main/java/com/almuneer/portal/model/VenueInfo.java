package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venue_info")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VenueInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infoId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String services;

    private Integer capacity;

    private String location;

    private String contactInfo;

    /** Google Maps share / open link for “Open in Google Maps” buttons */
    @Column(columnDefinition = "TEXT")
    private String mapsShareUrl;

    /** Google Maps embed iframe URL (from Share → Embed a map) */
    @Column(columnDefinition = "TEXT")
    private String mapsEmbedUrl;

    @Column(columnDefinition = "TEXT")
    private String faqJson;
}
