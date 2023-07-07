package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.po.OrderCommodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderCommodityMapper extends BaseMapper<OrderCommodity> {
    void insertOrderCommodity(Long id, Long id1, Long commodityNum);

    OrderCommodity findById(Long id);
}
