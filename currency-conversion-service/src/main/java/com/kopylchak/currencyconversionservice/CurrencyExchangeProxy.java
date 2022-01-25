package com.kopylchak.currencyconversionservice;

import com.kopylchak.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Якщо не вказувати url серверу, то буде використовуватись client side load balancer
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange")
    CurrencyConversion currencyExchange(@RequestParam("from") String from, @RequestParam("to") String to);
}
