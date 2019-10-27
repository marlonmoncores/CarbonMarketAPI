package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findAll();
}
