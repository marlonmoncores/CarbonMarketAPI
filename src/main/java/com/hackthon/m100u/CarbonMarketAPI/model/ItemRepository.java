package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.ItemEntity;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserBuyItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
