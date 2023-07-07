package com.feidian.service;

import com.feidian.po.CommodityImage;

import java.util.List;

public interface CommodityImageService {

    void insertCommodityImage(Long commodityId, String imageUrl, Long imageStatus);

    List<CommodityImage> findByCommodityId(Long CommodityId);
}
