package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.model.UserBuyItemRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserBuyRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserBuyEntity;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserSystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReadUserBuy {

    @Autowired
    UserBuyRepository userBuyRepository;

    @Autowired
    UserBuyItemRepository userBuyItemRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<UserPurchase> findByBuyId(long userId, Long buyId) {
        Optional<UserBuyEntity> userBuyEntity = userBuyRepository.findById(buyId);

        if (userBuyEntity.isPresent()) {
            if (userBuyEntity.get().getUser().getId() == userId) {
                return Optional.of(userBuyEntity.get().toUserPurchase());
            }
        }
        return Optional.empty();
    }

    public List<UserPurchase> findByInterval(long userId, Date beginDate , Date endDate) {
        List<UserBuyEntity> userBuyEntity;
        if(beginDate != null && endDate != null){
            userBuyEntity = userBuyRepository.findByUserAndCreatedAtBetween(new UserSystemEntity(userId), beginDate, endDate);
        }else if(beginDate == null && endDate != null){
            userBuyEntity = userBuyRepository.findByUserAndCreatedAtBefore(new UserSystemEntity(userId), endDate);
        }else if(beginDate != null && endDate == null) {
            userBuyEntity = userBuyRepository.findByUserAndCreatedAtAfter(new UserSystemEntity(userId), beginDate);
        }else{
            throw new IllegalArgumentException("One limit shoud be provided");
        }

        return userBuyEntity.stream().map(item -> item.toUserPurchase()).collect(Collectors.toList());
    }
}
