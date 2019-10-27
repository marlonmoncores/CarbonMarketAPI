package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
    Optional<MarketEntity> findByName(String name);
}
