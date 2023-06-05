package com.feidian.mapper;

import com.feidian.po.CommodityPO;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityMapper {

    void deleteCommodity(long commodityId);

    void updateCommodityDescription(String description);

    List<CommodityPO> findByUserId(long id);

    CommodityPO findByCommodityId(@Param("commodityId") long commodityId);


    void insertCommodity(CommodityPO commodityPO);

    void updateCommodity(CommodityDTO commodityDTO);
}
