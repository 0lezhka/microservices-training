package com.kopylchak.currencyexchangeservice.controller;

import com.kopylchak.currencyexchangeservice.entity.CurrencyExchange;
import com.kopylchak.currencyexchangeservice.repository.CurrencyExchangeRepository;
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
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    // Тягнемо проперті з config серверу з динамчним апдейтом

    @GetMapping("/property")
    public CurrencyExchange currencyExchangeProp(@RequestParam("from") String from, @RequestParam("to") String to) {
        return new CurrencyExchange(new CurrencyExchange.CurrencyExchangeId("UAH", "USD"), new BigDecimal(100),
                environment.getProperty("local.server.port"));
    }

    @GetMapping
    public CurrencyExchange currencyExchange(@RequestParam("from") String from, @RequestParam("to") String to) {
        return currencyExchangeRepository.findById(new CurrencyExchange.CurrencyExchangeId(from, to)).map(c -> {
            c.setEnv(environment.getProperty("local.server.port"));

            return c;
        }).orElse(null);
    }
}
