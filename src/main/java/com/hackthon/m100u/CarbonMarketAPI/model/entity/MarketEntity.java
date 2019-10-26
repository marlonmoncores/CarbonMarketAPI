package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import com.hackthon.m100u.CarbonMarketAPI.domain.Market;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="market")
public class MarketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public MarketEntity() {
    }

    public MarketEntity(long id) {
        this.id = id;
    }

    public Market toMarket(){
        Market market = new Market();
        market.setCreatedAt(createdAt);
        market.setName(name);
        market.setId(id);
        return market;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
