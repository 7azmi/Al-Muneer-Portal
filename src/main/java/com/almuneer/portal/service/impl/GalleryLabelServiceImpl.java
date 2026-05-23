package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.GalleryLabel;
import com.almuneer.portal.repository.GalleryLabelRepository;
import com.almuneer.portal.service.GalleryLabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryLabelServiceImpl implements GalleryLabelService {

    private final GalleryLabelRepository labelRepository;

    @Override
    public List<GalleryLabel> getAll() {
        return labelRepository.findAllByOrderBySortOrderAscNameAsc();
    }

    @Override
    public GalleryLabel getById(Long id) {
        return labelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gallery label not found: " + id));
    }

    @Override
    @Transactional
    public GalleryLabel create(String name, String icon, Integer sortOrder) {
        if (labelRepository.existsByNameIgnoreCase(name.trim())) {
            throw new IllegalArgumentException("A label named '" + name + "' already exists.");
        }
        return labelRepository.save(GalleryLabel.builder()
                .name(name.trim())
                .icon(icon != null ? icon.trim() : null)
                .sortOrder(sortOrder != null ? sortOrder : 0)
                .build());
    }

    @Override
    @Transactional
    public GalleryLabel update(Long id, String name, String icon, Integer sortOrder) {
        GalleryLabel label = getById(id);
        label.setName(name.trim());
        label.setIcon(icon != null ? icon.trim() : null);
        label.setSortOrder(sortOrder != null ? sortOrder : label.getSortOrder());
        return labelRepository.save(label);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        labelRepository.deleteById(id);
    }
}
