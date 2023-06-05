package com.feidian.service.impl;

import com.feidian.dto.CommodityDTO;
import com.feidian.mapper.CommodityMapper;
import com.feidian.po.CommodityPO;
import com.feidian.service.CommodityService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public void deleteCommodity(long commodityId) {
        commodityMapper.deleteCommodity(commodityId);
    }

    public void updateCommodityDescription(String description){
        commodityMapper.updateCommodityDescription(description);
    };

    @Override
    public List<CommodityPO> findByUserId(long id) {
        return commodityMapper.findByUserId(id);
    }

    @Override
    public CommodityPO findByCommodityId(@Param("commodityId") long commodityId) {
        return commodityMapper.findByCommodityId(commodityId);
    }

//    @Override
//    public void insertCommodity( @Param("userId") long userId,@Param("commodityName") String commodityName,
//                                 @Param("commodityType")String commodityType,@Param("price") BigDecimal price,
//                                 @Param("commodityDescription") String commodityDescription,
//                                 @Param("commodityAddress")String commodityAddress, @Param("coverUrl")String coverUrl) {
//        commodityMapper.insertCommodity( userId, commodityName, commodityType, price,
//                commodityDescription, commodityAddress, coverUrl);
//    }

    @Override
    public void insertCommodity(CommodityPO commodityPO) {
        commodityMapper.insertCommodity(commodityPO);
    }

    @Override
    public void updateCommodity(CommodityDTO commodityDTO) {
        commodityMapper.updateCommodity(commodityDTO);
    }

}
