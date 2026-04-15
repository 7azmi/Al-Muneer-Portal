package com.almuneer.portal.service;

import com.almuneer.portal.model.PricingTier;
import java.util.List;

public interface PricingTierService {
    List<PricingTier> getActiveTiers();
    List<PricingTier> getAllTiers();
    PricingTier getTierById(Long id);
    PricingTier saveTier(PricingTier tier);
    void deleteTier(Long id);
}
