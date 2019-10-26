package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserBuyItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBuyItemRepository extends JpaRepository<UserBuyItemEntity, Long> {

}
