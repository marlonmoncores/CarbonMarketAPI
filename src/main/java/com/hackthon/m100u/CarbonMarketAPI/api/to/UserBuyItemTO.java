package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;

public class UserBuyItemTO {
    private long itemId;
    private int quantity;

    public ItemPurchase toItemPurchase() {
        ItemPurchase itemPurchase = new ItemPurchase();
        itemPurchase.setItem(new Item(itemId));
        itemPurchase.setQuantity(quantity);
        return itemPurchase;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
