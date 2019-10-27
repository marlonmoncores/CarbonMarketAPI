package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserBuyEntity;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserBuyRepository extends JpaRepository<UserBuyEntity, Long> {

    Optional<UserBuyEntity> findById(Long id);

    List<UserBuyEntity> findByUserAndCreatedAtAfter(UserSystemEntity user, Date begin);
    List<UserBuyEntity> findByUserAndCreatedAtBefore(UserSystemEntity user, Date end);
    List<UserBuyEntity> findByUserAndCreatedAtBetween(UserSystemEntity user, Date begin, Date end);
}
