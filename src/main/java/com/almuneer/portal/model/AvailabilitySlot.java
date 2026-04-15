package com.almuneer.portal.model;

import com.almuneer.portal.model.enums.SlotStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "availability_slots")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AvailabilitySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    @Column(nullable = false, unique = true)
    private LocalDate slotDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SlotStatus status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = SlotStatus.AVAILABLE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
