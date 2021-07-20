package com.wuzhangze.cloud.controller;

import com.wuzhangze.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id){
        log.info("****result："+paymentService.paymentInfo_OK(id));
        return serverPort;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable Integer id){
//        log.info("****result："+paymentService.paymentInfo_Timeout(id));
        return paymentService.paymentInfo_Timeout(id) + "," + serverPort;
    }

    public static int i = 0;
    @GetMapping("/test")
    public String test(){
        i++;
        //用 AtomicInteger 实现可以吗
//        AtomicInteger atomicInteger = new AtomicInteger(i);
//        atomicInteger.incrementAndGet();
        return i + "";
    }

    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
