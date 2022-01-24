package com.kopylchak.limitsservice.controller;

import com.kopylchak.limitsservice.config.ConfigBean;
import com.kopylchak.limitsservice.entity.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {
    // Цей конфіг береться з конфіг сервера.
    @Autowired
    private ConfigBean configBean;

    @GetMapping("limits")
    public Limit getLimit() {
        return new Limit(configBean.getMin(), configBean.getMax());
    }
}
