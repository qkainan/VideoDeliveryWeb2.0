package com.feidian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderCommodityMapper {
    void insertOrderCommodity(long id, long id1, long commodityNum);
}
