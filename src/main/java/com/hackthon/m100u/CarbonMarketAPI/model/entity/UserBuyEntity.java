package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="user_buy")
public class UserBuyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private UserSystemEntity user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="id_market")
    private MarketEntity market;

    @OneToMany(mappedBy = "userBuy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserBuyItemEntity> itens;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public UserPurchase toUserPurchase(){
        UserPurchase userPurchase = new UserPurchase();
        userPurchase.setMarket(market.toMarket());
        userPurchase.setUser(user.toUser());
        userPurchase.setCreatedAt(createdAt);
        userPurchase.setItems(toItens());

        return userPurchase;
    }

    private List<ItemPurchase> toItens(){
        return itens.stream().map(item -> item.toItemPurchase()).collect(Collectors.toList());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserSystemEntity getUser() {
        return user;
    }

    public void setUser(UserSystemEntity user) {
        this.user = user;
    }

    public MarketEntity getMarket() {
        return market;
    }

    public void setMarket(MarketEntity market) {
        this.market = market;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
