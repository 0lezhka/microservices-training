package com.kopylchak.currencyexchangeservice.repository;

import com.kopylchak.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, CurrencyExchange.CurrencyExchangeId> {
}
