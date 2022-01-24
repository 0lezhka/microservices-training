package com.kopylchak.currencyexchangeservice.controller;

import com.kopylchak.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;

    @GetMapping
    public CurrencyExchange currencyExchange(@RequestParam("from") String from, @RequestParam("to") String to) {
        return new CurrencyExchange("UAH", "USD", new BigDecimal(100),
                environment.getProperty("local.server.port"));
    }
}
