package com.almuneer.portal.config;

import com.almuneer.portal.model.GalleryLabel;
import com.almuneer.portal.service.GalleryLabelService;
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

    @Value("${app.country.code:+967}")
    private String countryCode;

    @Value("${app.country.code:+967}")
    private String rawCode;

    @ModelAttribute("countryCode")
    public String countryCode() {
        return countryCode;
    }

    /** Digits extracted from the code — useful in JS/backend normalisation. */
    @ModelAttribute("countryCodeDigits")
    public String countryCodeDigits() {
        return rawCode.replaceAll("[^\\d]", "");
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
