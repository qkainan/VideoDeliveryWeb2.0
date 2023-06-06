package com.feidian.mapper;

import com.feidian.bo.OrderBO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {
    void insertOrder(OrderBO orderBO);
}
