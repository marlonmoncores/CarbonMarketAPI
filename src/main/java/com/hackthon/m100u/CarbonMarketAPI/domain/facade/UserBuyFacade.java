package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.BuyOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.MarketBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.ReadUserBuy;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.SaveUserBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserBuyFacade {

    @Autowired
    private SaveUserBuy saveUserBuy;

    @Autowired
    private ReadUserBuy readUserBuy;

    @Transactional
    public BuyOutputTO saveUserBuy(UserBuyInputTO userBuyInputTO){
        UserPurchase userPurchase =userBuyInputTO.toUserPurchase();
        long purchaseId = saveUserBuy.execute(userPurchase);
        BuyOutputTO buyOutputTO = calculateBuyCost(userPurchase);
        buyOutputTO.setId(purchaseId);
        return buyOutputTO;
    }

    @Transactional
    public BuyOutputTO saveUserBuy(MarketBuyInputTO marketBuyInputTO){
        UserPurchase userPurchase = marketBuyInputTO.toUserPurchase();
        long purchaseId = saveUserBuy.execute(marketBuyInputTO.toUserPurchase());
        BuyOutputTO buyOutputTO = calculateBuyCost(userPurchase);
        buyOutputTO.setId(purchaseId);
        return buyOutputTO;
    }

    private BuyOutputTO calculateBuyCost(UserPurchase userPurchase){//TODO - calculate buy carbon cost
        BuyOutputTO buyOutputTO = new BuyOutputTO();
        buyOutputTO.setGradeghg("A");
        buyOutputTO.setTotalghg(12345);
        buyOutputTO.setTotalH2O(0.2);
        return buyOutputTO;
    }


    public Optional<UserPurchase> getUserBuy(long userId, Long buyId) {
        Optional<UserPurchase> userPurchase = readUserBuy.execute(userId, buyId);
        return userPurchase;
    }
}
