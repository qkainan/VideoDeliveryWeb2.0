package com.feidian.controller;

import com.feidian.responseResult.ResponseResult;
import com.feidian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //收货后，改变订单状态
    @PostMapping("/receivingCommodity")
    public ResponseResult receivingCommodity(long orderId){
        return orderService.receivingCommodity(orderId);
    }

}
