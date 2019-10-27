package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Invoice {
    Date emission_date;
    Market market;
    int km_car;
    int no_baths;
    char points;

    List<InvoiceRecord> records;
}
