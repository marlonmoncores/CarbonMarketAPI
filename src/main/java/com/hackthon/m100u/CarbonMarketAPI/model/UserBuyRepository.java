package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserBuyEntity;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBuyRepository extends JpaRepository<UserBuyEntity, Long> {

    Optional<UserBuyEntity> findById(Long id);
}
