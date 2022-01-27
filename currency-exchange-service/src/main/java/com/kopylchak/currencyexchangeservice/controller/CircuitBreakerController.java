package com.kopylchak.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CircuitBreakerController {
    @GetMapping("/sample-api")
    // Якщо метод падає, то дана анотація дозволяє перевиконати його певну кількість разів (задається в конфігах)
    // Якшо жоден раз не був успішний, то повертається результат fallbackMethod
    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String sampleApi() {
        log.info("/sample-api");

        double result = 2 / 0;

        return "Sample API";
    }

    @GetMapping("/circuit-breaker")
    //
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    public String circuitBreaker() {
        log.info("/sample-api");

        double result = 2 / 0;

        return "circuitBreaker";
    }

        @GetMapping("/rate-limit")
    // Дозволяє обмежити кількість ріквестів на заданий проміжок часу. Конфігуриться в пропертях
    @RateLimiter(name = "default", fallbackMethod = "fallbackMethod")
    public String rateLimiter() {
        log.info("/sample-api");

        return "circuitBreaker";
    }

    public String fallbackMethod(Exception e) {
        return "Fallback method result";
    }
}
