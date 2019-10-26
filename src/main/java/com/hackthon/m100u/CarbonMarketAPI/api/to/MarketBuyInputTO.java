package com.hackthon.m100u.CarbonMarketAPI.api.to;

import java.util.List;

public class MarketBuyInputTO {

    private String cpf;

    private List<UserBuyItemTO> items;

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
}
