package com.hackthon.m100u.CarbonMarketAPI.api.to;

import java.util.List;

public class MarketBuyInputTO {

    private long idUser;

    private List<UserBuyItemTO> items;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public List<UserBuyItemTO> getItems() {
        return items;
    }

    public void setItems(List<UserBuyItemTO> items) {
        this.items = items;
    }
}
