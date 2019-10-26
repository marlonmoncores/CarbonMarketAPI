package com.hackthon.m100u.CarbonMarketAPI.domain;

import java.util.Date;

public class Item {
    private long id;
    private String externalId;
    private String name;
    private Double carbonServing;
    private Integer servings;
    private Integer servingDay;
    private Date createdAt;
    private ItemCategory category;

    public Item() {

    }

    public Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCarbonServing() {
        return carbonServing;
    }

    public void setCarbonServing(Double carbonServing) {
        this.carbonServing = carbonServing;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Integer getServingDay() {
        return servingDay;
    }

    public void setServingDay(Integer servingDay) {
        this.servingDay = servingDay;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}
