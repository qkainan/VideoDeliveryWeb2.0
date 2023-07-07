package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.OrderBO;
import com.feidian.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    void insertOrder(OrderBO orderBO);

    void updateOrderStatus(Long orderId);

    List<Order> findByBuyerId(Long userId);

    List<Order> findBySellerId(Long userId);
}
