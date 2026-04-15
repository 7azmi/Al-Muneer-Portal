package com.almuneer.portal.model;

import com.almuneer.portal.model.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "media_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MediaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private MediaType mediaType;

    private String fileName;

    private String filePath;

    private String youtubeUrl;

    private String caption;

    @Column(nullable = false)
    private LocalDateTime uploadDate;

    @Column(length = 30)
    private String category;

    @PrePersist
    protected void onCreate() {
        this.uploadDate = LocalDateTime.now();
    }
}
