package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String payment_ok(Integer id) {
        return "PaymentFallbackService  OK";
    }

    @Override
    public String payment_timeout(Integer id) {
        return "PaymentFallbackService Timeout";
    }
}
