package com.kopylchak.limitsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Limit {
    private Long min;
    private Long max;
}
