package com.almuneer.portal.repository;

import com.almuneer.portal.model.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {
    List<MediaItem> findByCategory(String category);
    List<MediaItem> findAllByOrderByUploadDateDesc();
}
