package com.kopylchak.currencyconversionservice.controller;

import com.kopylchak.currencyconversionservice.CurrencyExchangeProxy;
import com.kopylchak.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {
    @Autowired
    private Environment environment;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping
    public CurrencyConversion convertCurrency(@RequestParam("from") String from, @RequestParam("to") String to,
                                              @RequestParam("quantity") BigDecimal quantity) {
        CurrencyConversion currencyConversion = restTemplate.getForEntity(
                "http://localhost:8000/currency-exchange?from={from}&to={to}", CurrencyConversion.class, from, to)
                .getBody();

        return new CurrencyConversion(from ,to, quantity, currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple().multiply(quantity), currencyConversion.getEnv());
    }

    @GetMapping("/feign")
    public CurrencyConversion convertCurrencyFeign(@RequestParam("from") String from, @RequestParam("to") String to,
                                              @RequestParam("quantity") BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.currencyExchange(from, to);

        return new CurrencyConversion(from ,to, quantity, currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple().multiply(quantity), currencyConversion.getEnv());
    }
}
