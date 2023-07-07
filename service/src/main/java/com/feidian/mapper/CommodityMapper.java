package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.CommodityBO;
import com.feidian.po.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    Commodity findByCommodityId(Long commodityId);

    void insertCommodity(CommodityBO commodityBO);

    void deleteCommodity(Long commodityId);

    void updateCommodity(CommodityBO commodityBO);

    List<Commodity> findByUserId(Long userId);

}
