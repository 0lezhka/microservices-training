package com.kopylchak.currencyconversionservice;

import com.kopylchak.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange")
    CurrencyConversion currencyExchange(@RequestParam("from") String from, @RequestParam("to") String to);
}
