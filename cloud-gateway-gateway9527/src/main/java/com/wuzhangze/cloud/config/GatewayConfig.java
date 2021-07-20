package com.wuzhangze.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
//        // 相当于yaml文件中的routes
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        routes.route("path_route_1",p ->
//                p.path("/payment/lb") //访问localhost:9527/payment/lb 将会访问8001，注意我测试时需要地址一致
//                        .uri("http://localhost:8001/payment/lb")).build();
//        return routes.build();
//    }
}
