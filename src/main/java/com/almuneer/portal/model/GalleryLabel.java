package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * A visitor-facing label/category for gallery items (e.g. "Hall Setup", "Exterior").
 * Admin can create, rename and delete these; the gallery filter bar is built from them.
 */
@Entity
@Table(name = "gallery_labels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GalleryLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelId;

    /** Display name shown in the gallery filter bar and admin UI. */
    @Column(nullable = false, unique = true, length = 60)
    private String name;

    /** Optional emoji/icon prefix shown next to the label name. */
    @Column(length = 8)
    private String icon;

    /** Display order — lower numbers appear first in the filter bar. */
    @Column(nullable = false)
    @Builder.Default
    private Integer sortOrder = 0;
}
