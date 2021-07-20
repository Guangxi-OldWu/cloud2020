package com.wuzhangze.cloud.controller;


import com.wuzhangze.cloud.entity.CommonResult;
import com.wuzhangze.cloud.entity.Payment;
import com.wuzhangze.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;//从配置文件读取端口号

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        boolean save = paymentService.save(payment);
        log.info("****插入结果：" + save);
        if(save){
            return new CommonResult(200,"插入数据库成功，serverPort："+serverPort,save);
        }else {
            return new CommonResult(444,"插入数据库失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getById(id);
        log.info("****插入结果：" + payment);
        if(payment != null){
            return new CommonResult(200,"查询成功，serverPort："+serverPort, payment);
        }else {
            return new CommonResult(444,"没有对应记录"+id);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
}
