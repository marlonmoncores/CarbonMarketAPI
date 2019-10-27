package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;

import java.util.List;

public class UserMonthlyReportOutputTO {

    private List<UserPurchase> userPurchase;


    public List<UserPurchase> getUserPurchase() {
        return userPurchase;
    }

    public void setUserPurchase(List<UserPurchase> userPurchase) {
        this.userPurchase = userPurchase;
    }
}
