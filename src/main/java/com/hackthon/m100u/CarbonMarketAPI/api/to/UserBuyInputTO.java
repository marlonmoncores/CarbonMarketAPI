package com.hackthon.m100u.CarbonMarketAPI.api.to;

import java.util.List;

public class UserBuyInputTO {

    private long idMarket;

    private List<UserBuyItemTO> items;

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
}
