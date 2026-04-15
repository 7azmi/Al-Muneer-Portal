package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.repository.VenueInfoRepository;
import com.almuneer.portal.service.VenueInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueInfoServiceImpl implements VenueInfoService {

    private final VenueInfoRepository venueInfoRepository;

    @Override
    public VenueInfo getVenueInfo() {
        return venueInfoRepository.findAll().stream()
                .findFirst()
                .orElse(new VenueInfo());
    }

    @Override
    public VenueInfo updateVenueInfo(VenueInfo venueInfo) {
        VenueInfo existing = getVenueInfo();
        if (existing.getInfoId() != null) {
            venueInfo.setInfoId(existing.getInfoId());
        }
        return venueInfoRepository.save(venueInfo);
    }
}
