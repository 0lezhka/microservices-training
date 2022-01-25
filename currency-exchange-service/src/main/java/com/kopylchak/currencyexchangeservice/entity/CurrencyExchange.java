package com.kopylchak.currencyexchangeservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyExchange {
    @EmbeddedId
    private CurrencyExchangeId id;
    private BigDecimal conversionMultiple;
    private String env;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrencyExchangeId implements Serializable {
        @Column(name = "exchange_from")
        private String from;
        @Column(name = "exchange_to")
        private String to;
    }
}
