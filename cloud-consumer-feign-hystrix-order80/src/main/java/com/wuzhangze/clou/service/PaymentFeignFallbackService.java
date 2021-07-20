package com.wuzhangze.clou.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFeignFallbackService implements PaymentFeignService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fallback--paymentInfo_ok";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "fallback--paymentInfo_Timeout";
    }
}
