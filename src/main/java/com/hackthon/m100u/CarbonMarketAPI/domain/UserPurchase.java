package com.hackthon.m100u.CarbonMarketAPI.domain;

import java.util.Date;
import java.util.List;

public class UserPurchase {
    private User user;
    private Market market;
    private List<ItemPurchase> items;
    private Date createdAt;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public List<ItemPurchase> getItems() {
        return items;
    }

    public void setItems(List<ItemPurchase> items) {
        this.items = items;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
