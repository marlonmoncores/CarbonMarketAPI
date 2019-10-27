package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.Data;

@Data
public class Consumption {
    private double totalghg;
    private char gradeghg;
    private double totalH2O;

    private double km_car;
    private int no_baths;

    public Consumption(double totalghg, double totalH2O, int portions) {
        this.totalghg = totalghg;
        this.km_car = totalghg * 1.6 / 392;

        this.gradeghg = (char) (65 + totalghg / 2 / portions);

        this.totalH2O = totalH2O;
        this.no_baths = (int) (totalH2O / 65);
    }
}
