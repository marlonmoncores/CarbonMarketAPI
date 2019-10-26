package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.SaveUserBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBuyFacade {

    @Autowired
    private SaveUserBuy saveUserBuy;


    public UserOutputTO saveUserBuy(UserBuyInputTO userBuyInputTO){
        saveUserBuy.execute(userBuyInputTO.toUserPurchase());
        return null;
    }


}
