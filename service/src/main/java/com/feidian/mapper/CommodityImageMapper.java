package com.feidian.mapper;

import com.feidian.po.CommodityImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityImageMapper {
    void insertCommodityImage(Long commodityId, String imageUrl, Long imageStatus);

    List<CommodityImage> findByCommodityId(Long commodityId);

}
