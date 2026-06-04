package com.almuneer.portal.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Thin wrapper around the Google GenAI Java SDK.
 * Sends a plain-text prompt to Gemini and returns the generated text.
 */
@Slf4j
@Service
public class GeminiService {

    private static final String MODEL = "gemini-3.1-flash-lite";

    private final Client client;

    public GeminiService(@Value("${app.gemini.api-key}") String apiKey) {
        this.client = Client.builder().apiKey(apiKey).build();
    }

    /**
     * Sends {@code prompt} to Gemini and returns the generated text.
     * Returns a human-readable fallback if the call fails.
     */
    public String generate(String prompt) {
        try {
            List<Content> contents = List.of(
                    Content.builder()
                            .role("user")
                            .parts(List.of(Part.fromText(prompt)))
                            .build()
            );

            GenerateContentConfig config = GenerateContentConfig.builder().build();

            GenerateContentResponse response =
                    client.models.generateContent(MODEL, contents, config);

            if (response.candidates().isEmpty()
                    || response.candidates().get().isEmpty()
                    || response.candidates().get().get(0).content().isEmpty()
                    || response.candidates().get().get(0).content().get().parts().isEmpty()) {
                return "AI summary unavailable — no response from model.";
            }

            List<Part> parts = response.candidates().get().get(0).content().get().parts().get();
            StringBuilder sb = new StringBuilder();
            for (Part part : parts) {
                if (part.text().isPresent()) {
                    sb.append(part.text().get());
                }
            }
            return sb.toString().trim();

        } catch (Exception e) {
            log.error("Gemini API call failed: {}", e.getMessage());
            return "AI summary unavailable — API call failed.";
        }
    }
}

