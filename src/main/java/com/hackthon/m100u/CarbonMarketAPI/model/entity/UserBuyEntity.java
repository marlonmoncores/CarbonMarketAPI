package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


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
