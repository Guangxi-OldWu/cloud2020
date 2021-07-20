package com.wuzhangze.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzhangze.cloud.entity.Payment;
import com.wuzhangze.cloud.mapper.PaymentMapper;
import com.wuzhangze.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
