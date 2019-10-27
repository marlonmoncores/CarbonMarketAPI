package com.hackthon.m100u.CarbonMarketAPI.api.to;

import lombok.Data;

@Data
public class BuyOutputTO {
    private long id;
    private double totalghg;
    private String gradeghg;
    private double totalH2O;
}
