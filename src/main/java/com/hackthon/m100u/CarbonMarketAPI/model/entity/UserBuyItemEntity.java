package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import com.hackthon.m100u.CarbonMarketAPI.domain.Item;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_buy_item")
public class UserBuyItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="id_user_buy")
    private UserBuyEntity userBuy;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="id_item")
    private ItemEntity item;

    private Integer quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserBuyEntity getUserBuy() {
        return userBuy;
    }

    public void setUserBuy(UserBuyEntity userBuy) {
        this.userBuy = userBuy;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
