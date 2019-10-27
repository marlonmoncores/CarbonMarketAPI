package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Market {
    private Long id;
    private String name;
    private Date createdAt;

    public Market(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Market(long id) {
        this.id = id;
    }
}
