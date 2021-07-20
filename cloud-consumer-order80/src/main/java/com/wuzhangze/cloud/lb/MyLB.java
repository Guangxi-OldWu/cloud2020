package com.wuzhangze.cloud.lb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Autowired
    /**
     * 请求次数++，返回请求次数+1
     */
    public final int getAndIncrement(){
        for(;;){
            int current = atomicInteger.get();
            int next = current > Integer.MAX_VALUE ? 0 : current + 1;
            if(atomicInteger.compareAndSet(current,next)){
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        return serviceInstances.get(getAndIncrement() % serviceInstances.size());
    }
}
