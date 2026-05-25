package com.almuneer.portal.config;

import com.almuneer.portal.model.GalleryLabel;
import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.service.GalleryLabelService;
import com.almuneer.portal.service.VenueInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Injects global model attributes into every Thymeleaf template.
 *
 * <p>Templates can reference {@code ${countryCode}}, {@code ${countryCodeDigits}},
 * and {@code ${galleryLabels}} directly without each controller needing to set them.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final GalleryLabelService galleryLabelService;
    private final VenueInfoService venueInfoService;

    @Value("${app.country.code:+967}")
    private String countryCode;

    @Value("${app.country.code:+967}")
    private String rawCode;

    @Value("${app.maps.share-url:https://maps.google.com}")
    private String defaultMapsShareUrl;

    @Value("${app.maps.embed-url:https://www.google.com/maps?q=Ibb+Yemen&output=embed}")
    private String defaultMapsEmbedUrl;

    @ModelAttribute("countryCode")
    public String countryCode() {
        return countryCode;
    }

    /** Digits extracted from the code — useful in JS/backend normalisation. */
    @ModelAttribute("countryCodeDigits")
    public String countryCodeDigits() {
        return rawCode.replaceAll("[^\\d]", "");
    }

    @ModelAttribute("mapsShareUrl")
    public String mapsShareUrl() {
        return resolveMapsShareUrl();
    }

    @ModelAttribute("mapsEmbedUrl")
    public String mapsEmbedUrl() {
        return resolveMapsEmbedUrl();
    }

    private VenueInfo venueOrNull() {
        try {
            return venueInfoService.getVenueInfo();
        } catch (Exception e) {
            return null;
        }
    }

    private String resolveMapsShareUrl() {
        VenueInfo venue = venueOrNull();
        if (venue != null && venue.getMapsShareUrl() != null && !venue.getMapsShareUrl().isBlank()) {
            return venue.getMapsShareUrl().trim();
        }
        return defaultMapsShareUrl;
    }

    private String resolveMapsEmbedUrl() {
        VenueInfo venue = venueOrNull();
        if (venue != null && venue.getMapsEmbedUrl() != null && !venue.getMapsEmbedUrl().isBlank()) {
            return venue.getMapsEmbedUrl().trim();
        }
        return defaultMapsEmbedUrl;
    }

    /** Gallery labels ordered by sortOrder — used in the visitor gallery filter bar. */
    @ModelAttribute("galleryLabels")
    public List<GalleryLabel> galleryLabels() {
        try {
            return galleryLabelService.getAll();
        } catch (Exception e) {
            return List.of();
        }
    }
}
