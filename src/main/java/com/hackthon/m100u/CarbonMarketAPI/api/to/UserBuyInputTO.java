package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;

import java.util.List;
import java.util.stream.Collectors;

public class UserBuyInputTO {

    private long idMarket;

    @JsonIgnore
    private long idUser;


    private List<UserBuyItemTO> items;

    public UserPurchase toUserPurchase(){
        UserPurchase userPurchase = new UserPurchase();
        userPurchase.setMarket(new Market(idMarket));
        userPurchase.setUser(new User(idUser));
        userPurchase.setItems(toItensPurchaseList());
        return userPurchase;
    }

    private List<ItemPurchase> toItensPurchaseList(){
        return items.stream().map(UserBuyItemTO::toItemPurchase).collect(Collectors.toList());
    }



    public long getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(long idMarket) {
        this.idMarket = idMarket;
    }

    public List<UserBuyItemTO> getItems() {
        return items;
    }

    public void setItems(List<UserBuyItemTO> items) {
        this.items = items;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
