package com.almuneer.portal.repository;

import com.almuneer.portal.model.PricingTier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PricingTierRepository extends JpaRepository<PricingTier, Long> {
    List<PricingTier> findByIsActiveTrue();
}
