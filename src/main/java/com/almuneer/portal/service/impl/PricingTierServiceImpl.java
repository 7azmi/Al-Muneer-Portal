package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.PricingTier;
import com.almuneer.portal.repository.PricingTierRepository;
import com.almuneer.portal.service.PricingTierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingTierServiceImpl implements PricingTierService {

    private final PricingTierRepository pricingTierRepository;

    @Override
    public List<PricingTier> getActiveTiers() {
        return pricingTierRepository.findByIsActiveTrue();
    }

    @Override
    public List<PricingTier> getAllTiers() {
        return pricingTierRepository.findAll();
    }

    @Override
    public PricingTier getTierById(Long id) {
        return pricingTierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pricing tier not found: " + id));
    }

    @Override
    public PricingTier saveTier(PricingTier tier) {
        return pricingTierRepository.save(tier);
    }

    @Override
    public void deleteTier(Long id) {
        pricingTierRepository.deleteById(id);
    }
}
