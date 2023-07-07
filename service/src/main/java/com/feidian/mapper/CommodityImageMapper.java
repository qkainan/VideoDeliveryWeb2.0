package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.po.CommodityImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityImageMapper extends BaseMapper<CommodityImage> {
    void insertCommodityImage(Long commodityId, String imageUrl, Long imageStatus);

    List<CommodityImage> findByCommodityId(Long commodityId);

}
