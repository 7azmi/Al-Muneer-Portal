package com.almuneer.portal.service;

import com.almuneer.portal.model.AvailabilitySlot;
import com.almuneer.portal.model.enums.SlotStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AvailabilitySlotService {
    List<AvailabilitySlot> getSlotsForMonth(int year, int month);
    Map<String, String> getSlotStatusMap(int year, int month);
    AvailabilitySlot getSlotByDate(LocalDate date);
    AvailabilitySlot saveSlot(AvailabilitySlot slot);
    void setSlotStatus(LocalDate date, SlotStatus status, String notes);
}
