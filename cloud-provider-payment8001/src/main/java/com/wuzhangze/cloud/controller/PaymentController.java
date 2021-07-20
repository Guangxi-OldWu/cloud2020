package com.wuzhangze.cloud.controller;


import com.wuzhangze.cloud.entity.CommonResult;
import com.wuzhangze.cloud.entity.Payment;
import com.wuzhangze.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;//从配置文件读取端口号

    @Autowired
    private DiscoveryClient discoveryClient;//暴露微服务的信息（注意是cloud包下的）

    @GetMapping("/payment/discovery")
    public Object discovery(){
        // 获取服务实例(服务id)
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("服务实例："+service);
        }

        // 通过服务id 获取微服务
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println("微服务信息："+
                    instance.getServiceId() +
                    "\t" + instance.getHost() +
                    "\t" + instance.getPort() +
                    "\t" + instance.getUri());
        }
        return discoveryClient;
    }

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

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    static int i = 0;
    @GetMapping("/test")
    public String test(){
        synchronized (PaymentController.class){
            i++;
        }
        log.info(Thread.currentThread().getName());
        return i + "";
    }
}
