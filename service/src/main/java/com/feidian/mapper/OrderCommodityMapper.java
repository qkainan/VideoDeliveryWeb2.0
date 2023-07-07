package com.feidian.mapper;

import com.feidian.po.OrderCommodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderCommodityMapper {
    void insertOrderCommodity(Long id, Long id1, Long commodityNum);

    OrderCommodity findById(Long id);
}
