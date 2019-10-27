package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String externalId;

    private String name;

    private Double carbonServing;

    private Double waterServing;

    private Integer servings;

    private Integer servingDay;

    private Date createdAt;

    private ItemCategory category;

    public Item(long id) {
        this.id = id;
    }
}
