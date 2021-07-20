package com.wuzhangze.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuzhangze.cloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentMapper extends BaseMapper<Payment> {
}
