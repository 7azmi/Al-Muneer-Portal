package com.almuneer.portal.repository;

import com.almuneer.portal.model.AvailabilitySlot;
import com.almuneer.portal.model.enums.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {
    Optional<AvailabilitySlot> findBySlotDate(LocalDate slotDate);
    List<AvailabilitySlot> findBySlotDateBetween(LocalDate start, LocalDate end);
    List<AvailabilitySlot> findByStatus(SlotStatus status);
}
