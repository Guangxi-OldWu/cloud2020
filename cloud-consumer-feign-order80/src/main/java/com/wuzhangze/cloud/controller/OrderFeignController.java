package com.wuzhangze.cloud.controller;

import com.netflix.discovery.converters.Auto;
import com.wuzhangze.cloud.entity.CommonResult;
import com.wuzhangze.cloud.entity.Payment;
import com.wuzhangze.cloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        // 客户端m默认等待1秒，服务端设置了暂停3秒，报错feign.RetryableException: Read timed out
        return paymentFeignService.paymentFeignTimeout();
    }
}
