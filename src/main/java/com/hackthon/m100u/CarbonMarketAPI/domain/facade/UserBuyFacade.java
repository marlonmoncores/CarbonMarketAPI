package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.BuyOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.MarketBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.SaveUserBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserBuyFacade {

    @Autowired
    private SaveUserBuy saveUserBuy;

    @Transactional
    public BuyOutputTO saveUserBuy(UserBuyInputTO userBuyInputTO){
        UserPurchase userPurchase =userBuyInputTO.toUserPurchase();
        saveUserBuy.execute(userPurchase);
        return calculateBuyCost(userPurchase);
    }

    @Transactional
    public BuyOutputTO saveUserBuy(MarketBuyInputTO marketBuyInputTO){
        UserPurchase userPurchase = marketBuyInputTO.toUserPurchase();
        saveUserBuy.execute(marketBuyInputTO.toUserPurchase());
        return calculateBuyCost(userPurchase);
    }

    private BuyOutputTO calculateBuyCost(UserPurchase userPurchase){//TODO - calculate buy carbon cost
        BuyOutputTO buyOutputTO = new BuyOutputTO();
        buyOutputTO.setGradeghg("A");
        buyOutputTO.setTotalghg(12345);
        buyOutputTO.setTotalH2O(0.2);
        return buyOutputTO;
    }


    public void getUserBuy(long user, Integer buyId) {
    }
}
