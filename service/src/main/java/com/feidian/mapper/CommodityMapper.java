package com.feidian.mapper;

import com.feidian.bo.CommodityBO;
import com.feidian.dto.CommodityDTO;
import com.feidian.po.CommodityPO;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityMapper {

    CommodityPO findByCommodityId( long commodityId);

    void insertCommodity(CommodityBO commodityBO);

    void deleteCommodity(long commodityId);

    void updateCommodity(CommodityBO commodityBO);

    List<CommodityPO> findByUserId(long userId);

//    void updateCommodityDescription(String description);
//
//    List<CommodityPO> findByUserId(long id);
//
}
