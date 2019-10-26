package com.hackthon.m100u.CarbonMarketAPI.api.to;

public class BuyOutputTO {
    private long id;
    private long totalghg;
    private String gradeghg;
    private double totalH2O;

    public long getTotalghg() {
        return totalghg;
    }

    public void setTotalghg(long totalghg) {
        this.totalghg = totalghg;
    }

    public String getGradeghg() {
        return gradeghg;
    }

    public void setGradeghg(String gradeghg) {
        this.gradeghg = gradeghg;
    }

    public double getTotalH2O() {
        return totalH2O;
    }

    public void setTotalH2O(double totalH2O) {
        this.totalH2O = totalH2O;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
