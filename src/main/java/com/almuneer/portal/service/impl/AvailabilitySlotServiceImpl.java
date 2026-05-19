package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.AvailabilitySlot;
import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.repository.AvailabilitySlotRepository;
import com.almuneer.portal.service.AvailabilitySlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AvailabilitySlotServiceImpl implements AvailabilitySlotService {

    private final AvailabilitySlotRepository slotRepository;

    @Override
    public List<AvailabilitySlot> getSlotsForMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        return slotRepository.findBySlotDateBetween(start, end);
    }

    @Override
    public Map<String, String> getSlotStatusMap(int year, int month) {
        List<AvailabilitySlot> slots = getSlotsForMonth(year, month);
        Map<String, String> statusMap = new LinkedHashMap<>();
        for (AvailabilitySlot slot : slots) {
            statusMap.put(slot.getSlotDate().toString(), slot.getStatus().name());
        }
        return statusMap;
    }

    @Override
    public AvailabilitySlot getSlotByDate(LocalDate date) {
        return slotRepository.findBySlotDate(date).orElse(null);
    }

    @Override
    public AvailabilitySlot getOrCreateSlot(LocalDate date) {
        return slotRepository.findBySlotDate(date)
                .orElseGet(() -> slotRepository.save(
                        AvailabilitySlot.builder()
                                .slotDate(date)
                                .status(SlotStatus.AVAILABLE)
                                .build()));
    }

    @Override
    public AvailabilitySlot saveSlot(AvailabilitySlot slot) {
        return slotRepository.save(slot);
    }


    @Override
    public void setSlotStatus(LocalDate date, SlotStatus status, String notes) {
        AvailabilitySlot slot = slotRepository.findBySlotDate(date)
                .orElse(AvailabilitySlot.builder()
                        .slotDate(date)
                        .build());
        slot.setStatus(status);
        slot.setNotes(notes);
        slotRepository.save(slot);
    }
}
