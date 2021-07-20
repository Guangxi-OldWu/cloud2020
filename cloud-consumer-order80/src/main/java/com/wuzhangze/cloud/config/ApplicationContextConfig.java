package com.wuzhangze.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //开启RestTemplate负载均衡，在微服务集群中必须要加上，因为同一个服务名有多个微服务，不知道要选哪个，开启负载均衡才能按规则选取
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
