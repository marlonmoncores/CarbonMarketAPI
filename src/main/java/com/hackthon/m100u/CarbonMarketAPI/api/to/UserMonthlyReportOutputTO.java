package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class UserMonthlyReportOutputTO {


    private UserMonthlyReportOutputInternalTO original;
    private UserMonthlyReportOutputInternalTO suggestion;


    @Data
    @AllArgsConstructor
    public static class UserMonthlyReportOutputInternalTO {
        private List<ItemPurchase> items;
        private Consumption consumption;
    }

}
