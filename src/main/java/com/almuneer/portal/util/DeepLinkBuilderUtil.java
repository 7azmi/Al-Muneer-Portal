package com.almuneer.portal.util;

import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Generates wa.me WhatsApp deep links by resolving placeholders in a template.
 * Supported placeholders: {visitorName}, {inquiryId}, {eventDate}, {status}, {packageName}
 */
@Component
public class DeepLinkBuilderUtil {

    /**
     * Build a wa.me URL from a raw WhatsApp number and a template string with placeholders.
     *
     * @param whatsAppNumber raw number (digits only, with country code, e.g. "9671234567890")
     * @param templateText   text with {placeholder} tokens
     * @param values         map of placeholder key → resolved value
     * @return fully-formed wa.me deep-link URL
     */
    public String buildLink(String whatsAppNumber, String templateText, Map<String, String> values) {
        String resolved = templateText;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            resolved = resolved.replace("{" + entry.getKey() + "}", entry.getValue() != null ? entry.getValue() : "");
        }
        String clean = whatsAppNumber.replaceAll("[^\\d]", "");
        String encoded = URLEncoder.encode(resolved, StandardCharsets.UTF_8);
        return "https://wa.me/" + clean + "?text=" + encoded;
    }
}
