package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;

import java.util.List;
import java.util.stream.Collectors;

public class MarketBuyInputTO {

    private String cpf;

    private List<UserBuyItemTO> items;

    private Long idMarket;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<UserBuyItemTO> getItems() {
        return items;
    }

    public void setItems(List<UserBuyItemTO> items) {
        this.items = items;
    }

    public Long getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(Long idMarket) {
        this.idMarket = idMarket;
    }

    public UserPurchase toUserPurchase() {
        UserPurchase userPurchase = new UserPurchase();
        userPurchase.setMarket(new Market(idMarket));
        User user = new User();
        user.setCpf(cpf);
        userPurchase.setUser(user);
        userPurchase.setItems(toItensPurchaseList());
        return userPurchase;
    }



    private List<ItemPurchase> toItensPurchaseList(){
        return items.stream().map(item -> item.toItemPurchase()).collect(Collectors.toList());
    }
}
