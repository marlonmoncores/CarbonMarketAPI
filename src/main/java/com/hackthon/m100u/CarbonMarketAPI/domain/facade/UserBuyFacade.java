package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.*;
import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.CalculateCarbonConsumptionService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.ReadUserBuy;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.SaveUserBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserBuyFacade {

    @Autowired
    private SaveUserBuy saveUserBuy;

    @Autowired
    private ReadUserBuy readUserBuy;

    @Autowired
    private CalculateCarbonConsumptionService calculateCarbonConsumptionService;

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

    @Transactional
    public BuyOutputTO saveUserCodeBuy( UserBuyCodeInputTO userBuyCodeInputTO){
        UserPurchase userPurchase = null;//TODO - consultar receita
        long purchaseId = saveUserBuy.execute(userPurchase);
        BuyOutputTO buyOutputTO = calculateBuyCost(userPurchase);
        buyOutputTO.setId(purchaseId);
        return buyOutputTO;
    }

    private BuyOutputTO calculateBuyCost(UserPurchase userPurchase){//TODO - calculate buy carbon cost
        BuyOutputTO buyOutputTO = new BuyOutputTO();
        Consumption consumption = calculateCarbonConsumptionService.calculateTotalConsumption(userPurchase);
        buyOutputTO.setTotalghg(consumption.getTotalghg());
        buyOutputTO.setTotalH2O(consumption.getTotalH2O());
        return buyOutputTO;
    }


    public Optional<UserPurchase> getUserBuy(long userId, Long buyId) {
        Optional<UserPurchase> userPurchase = readUserBuy.findByBuyId(userId, buyId);
        return userPurchase;
    }

    public List<UserPurchase> getUserBuyFiltered(UserBuyFilterTO userBuyFilterTO) {
        List<UserPurchase> userPurchases = readUserBuy.findByInterval(userBuyFilterTO.getUserId(), userBuyFilterTO.getBeginDate(), userBuyFilterTO.getEndDate());
        return userPurchases;
    }
}
