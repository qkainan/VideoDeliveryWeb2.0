package com.feidian.mapper;

import com.feidian.bo.CommodityBO;
import com.feidian.po.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityMapper {

    Commodity findByCommodityId(Long commodityId);

    void insertCommodity(CommodityBO commodityBO);

    void deleteCommodity(Long commodityId);

    void updateCommodity(CommodityBO commodityBO);

    List<Commodity> findByUserId(Long userId);

}
