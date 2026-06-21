package com.almuneer.portal.util;

import com.almuneer.portal.model.FaqItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class FaqJsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private FaqJsonUtil() {}

    public static List<FaqItem> parse(String faqJson) {
        if (faqJson == null || faqJson.isBlank()) {
            return List.of();
        }
        try {
            List<FaqItem> items = MAPPER.readValue(faqJson, new TypeReference<>() {});
            return items.stream()
                    .filter(item -> item.getQuestion() != null && !item.getQuestion().isBlank())
                    .toList();
        } catch (Exception e) {
            log.warn("Failed to parse faqJson: {}", e.getMessage());
            return List.of();
        }
    }

    public static String serialize(List<FaqItem> items) {
        if (items == null || items.isEmpty()) {
            return "[]";
        }
        try {
            List<FaqItem> cleaned = items.stream()
                    .filter(item -> item.getQuestion() != null && !item.getQuestion().isBlank())
                    .map(item -> FaqItem.builder()
                            .question(item.getQuestion().trim())
                            .answer(item.getAnswer() != null ? item.getAnswer().trim() : "")
                            .build())
                    .toList();
            return MAPPER.writeValueAsString(cleaned);
        } catch (Exception e) {
            log.warn("Failed to serialize FAQs: {}", e.getMessage());
            return "[]";
        }
    }

    public static String buildFromPairs(List<String> questions, List<String> answers) {
        if (questions == null || questions.isEmpty()) {
            return "[]";
        }
        List<FaqItem> items = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            String q = questions.get(i);
            if (q == null || q.isBlank()) {
                continue;
            }
            String a = (answers != null && i < answers.size() && answers.get(i) != null)
                    ? answers.get(i) : "";
            items.add(FaqItem.builder().question(q).answer(a).build());
        }
        return serialize(items);
    }
}
