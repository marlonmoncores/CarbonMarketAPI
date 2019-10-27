package com.hackthon.m100u.CarbonMarketAPI.api.to;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import lombok.Data;

@Data
public class UserMonthlyReportOutputTO {

    @JsonUnwrapped
    private Consumption consumption;

}
