package com.feidian.controller;

import com.feidian.po.CommodityPO;
import com.feidian.po.OrderCommodityPO;
import com.feidian.po.OrderPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.OrderService;
import com.feidian.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //收货后，改变订单状态
    @PostMapping("/receivingCommodity")
    public ResponseResult receivingCommodity(long orderId){
        return orderService.receivingCommodity(orderId);
    }

    @GetMapping("/viewPurchaseOrder")
    public ResponseResult viewPurchaseOrder(){
        return orderService.viewPurchaseOrder();
    }

    @GetMapping("/viewSaleOrder")
    public ResponseResult viewSaleOrder(){
        return orderService.viewSaleOrder();
    }


}
