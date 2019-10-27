package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import lombok.Data;

@Data
public class BuyOutputTO {
    private long id;
    private Consumption consumption;
}
