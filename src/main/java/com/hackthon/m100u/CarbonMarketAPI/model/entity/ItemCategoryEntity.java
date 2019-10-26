package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="item_category")
public class ItemCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Boolean optional;
    private Integer dailyPortions;

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

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Integer getDailyPortions() {
        return dailyPortions;
    }

    public void setDailyPortions(Integer dailyPortions) {
        this.dailyPortions = dailyPortions;
    }
}
