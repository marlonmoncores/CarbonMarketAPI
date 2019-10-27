package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Consumption {
    private double totalghg;
    private String gradeghg;
    private double totalH2O;

    private int km_car;
    private int no_baths;

    public Consumption(double totalghg, double totalH2O) {
        this.totalghg = totalghg;
        this.totalH2O = totalH2O;
    }
}
