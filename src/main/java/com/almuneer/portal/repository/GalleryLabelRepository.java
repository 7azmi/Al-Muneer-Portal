package com.almuneer.portal.repository;

import com.almuneer.portal.model.GalleryLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GalleryLabelRepository extends JpaRepository<GalleryLabel, Long> {
    java.util.List<GalleryLabel> findAllByOrderBySortOrderAscNameAsc();
    boolean existsByNameIgnoreCase(String name);
    Optional<GalleryLabel> findByNameIgnoreCase(String name);
}
