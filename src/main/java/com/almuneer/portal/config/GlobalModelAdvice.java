package com.almuneer.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Injects global model attributes into every Thymeleaf template.
 *
 * <p>Templates can reference {@code ${countryCode}} directly without
 * each controller needing to set it manually. The value comes from
 * {@code app.country.code} in {@code application.properties}.
 */
@ControllerAdvice
public class GlobalModelAdvice {

    @Value("${app.country.code:+967}")
    private String countryCode;

    /** Digits-only version of the country code (e.g. "967") for normalisation logic. */
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
}
