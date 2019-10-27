package com.hackthon.m100u.CarbonMarketAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceRecord {
    Item item;
    int quantity;
}
