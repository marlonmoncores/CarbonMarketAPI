package com.hackthon.m100u.CarbonMarketAPI.domain;

import java.util.Date;

public class Market {
    private long id;
    private String name;
    private Date createdAt;

    public Market() {

    }

    public Market(long id) {
        this.id = id;
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
