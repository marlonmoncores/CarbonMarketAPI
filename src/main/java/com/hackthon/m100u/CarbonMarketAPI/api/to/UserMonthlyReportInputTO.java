package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserMonthlyReportInputTO {

    @JsonIgnore
    private Long idUser;

    private Integer year;
    private Integer month;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
