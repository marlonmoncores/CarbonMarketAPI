package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Market {
    private long id;
    private String name;
    private Date createdAt;

    public Market(long id) {
        this.id = id;
    }
}
