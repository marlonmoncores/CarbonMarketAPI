package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.utils.PasswordUtils;
import com.hackthon.m100u.CarbonMarketAPI.model.UserBuyItemRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserBuyRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveUserBuy {

    @Autowired
    UserBuyRepository userBuyRepository;

    @Autowired
    UserBuyItemRepository userBuyItemRepository;

    @Autowired
    UserRepository userRepository;

    public void execute(UserPurchase userPurchase){
        UserBuyEntity userBuyEntity = toEntity(userPurchase);
        userBuyRepository.save(userBuyEntity);
        userPurchase.getItems().stream().forEach( item -> userBuyItemRepository.save(toEntity(item,userBuyEntity)));
        return;

    }

    private UserBuyEntity toEntity(UserPurchase userPurchage){
        UserBuyEntity userBuyEntity = new UserBuyEntity();
        Date now = new Date();
        userBuyEntity.setMarket(new MarketEntity(userPurchage.getMarket().getId()));
        if(userPurchage.getUser().getId() != null) {
            userBuyEntity.setUser(new UserSystemEntity(userPurchage.getUser().getId()));
        }else{
            UserSystemEntity userSystemEntity = userRepository.findByCpf(userPurchage.getUser().getCpf()).get();
            userBuyEntity.setUser(userSystemEntity);
        }
        userBuyEntity.setCreatedAt(now);
        return userBuyEntity;
    }

    private UserBuyItemEntity toEntity(ItemPurchase itemPurchase, UserBuyEntity userBuyEntity){
        UserBuyItemEntity userBuyItemEntity = new UserBuyItemEntity();
        userBuyItemEntity.setItem(new ItemEntity(itemPurchase.getItem().getId()));
        userBuyItemEntity.setQuantity(itemPurchase.getQuantity());
        userBuyItemEntity.setUserBuy(userBuyEntity);
        return userBuyItemEntity;
    }


}
