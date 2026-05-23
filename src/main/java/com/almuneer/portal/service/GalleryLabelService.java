package com.almuneer.portal.service;

import com.almuneer.portal.model.GalleryLabel;
import java.util.List;

public interface GalleryLabelService {
    List<GalleryLabel> getAll();
    GalleryLabel getById(Long id);
    GalleryLabel create(String name, String icon, Integer sortOrder);
    GalleryLabel update(Long id, String name, String icon, Integer sortOrder);
    void delete(Long id);
}
