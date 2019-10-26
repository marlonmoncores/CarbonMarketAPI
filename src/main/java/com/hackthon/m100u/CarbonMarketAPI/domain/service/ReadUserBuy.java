package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.model.UserBuyItemRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserBuyRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserBuyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadUserBuy {

    @Autowired
    UserBuyRepository userBuyRepository;

    @Autowired
    UserBuyItemRepository userBuyItemRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<UserPurchase> execute(long userId, Long buyId) {
        Optional<UserBuyEntity> userBuyEntity = userBuyRepository.findById(buyId);

        if (userBuyEntity.isPresent()) {
            if (userBuyEntity.get().getUser().getId() == userId) {
                return Optional.of(userBuyEntity.get().toUserPurchase());
            }
        }
        return Optional.empty();
    }
}
