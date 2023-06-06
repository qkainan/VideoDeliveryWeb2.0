package com.feidian.mapper;

import com.feidian.bo.OrderBO;
import com.feidian.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {
    void insertOrder(OrderBO orderBO);

    void updateOrderStatus(long orderId);

    OrderPO[] findByBuyerId(long userId);

    OrderPO[] findBySellerId(long userId);
}
