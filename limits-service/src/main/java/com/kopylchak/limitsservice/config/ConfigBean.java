package com.kopylchak.limitsservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
@Data
// ConfigurationProperties має містити назву класу проперті, а сама пропертя відображається назвою поля
// Тому в цей клас потрапляють проперті: limits-service.min, limits-service.max
public class ConfigBean {
    private Long min;
    private Long max;
}
