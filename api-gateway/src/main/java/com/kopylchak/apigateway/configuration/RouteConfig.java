package com.kopylchak.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // Для заданого URI
                .route(r -> r.path("/currency-conversion/feign")
                        // Додаю новий хедер
                        .filters(f -> f.addRequestHeader("JWT", "Some JWT Token"))
                        // І редірекчу на потрібний URI, при цьому роблячи load balancing
                        .uri("lb://currency-conversion-service/feign")
                ).route(r -> r.path("/currency-conversion")
                        .uri("lb://currency-conversion-service"))
                .route(r -> r.path("/currency-exchange/property")
                        .uri("lb://currency-exchange-service/property"))
                .route(r -> r.path("/currency-exchange")
                        .uri("lb://currency-exchange-service"))
                .build();
    }
}
