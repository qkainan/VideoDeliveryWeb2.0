package com.feidian.service.impl;


import com.feidian.mapper.OrderMapper;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.OrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    //收货
    @Override
    public ResponseResult receivingCommodity(long orderId) {
        orderMapper.updateOrderStatus(orderId);
        return ResponseResult.successResult();
    }
}
