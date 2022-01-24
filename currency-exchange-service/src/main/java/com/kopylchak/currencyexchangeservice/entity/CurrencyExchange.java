package com.kopylchak.currencyexchangeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyExchange {
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private String env;
}
