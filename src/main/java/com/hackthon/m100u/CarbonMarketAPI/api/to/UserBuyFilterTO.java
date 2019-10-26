package com.hackthon.m100u.CarbonMarketAPI.api.to;

import java.util.Date;

public class UserBuyFilterTO {

    private Date beginDate;
    private Date endDate;
    private Long userId;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
