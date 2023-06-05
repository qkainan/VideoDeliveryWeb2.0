package com.feidian.service;

import com.feidian.dto.CommodityDTO;
import com.feidian.po.CommodityPO;
import com.feidian.responseResult.ResponseResult;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CommodityService {


//    void updateCommodityDescription(String description);
//
//    List<CommodityPO> findByUserId(long id);
//
//    CommodityPO findByCommodityId(@Param("commodityId") long commodityId);
//
//    void insertCommodity( CommodityPO commodityPO);
//
//    void updateCommodity(CommodityDTO commodityDTO);

    ResponseResult uploadCommodity(CommodityDTO commodityDTO, MultipartFile coverFile, MultipartFile[] imageFile);

    ResponseResult displayCommodity(long commodityId) throws URISyntaxException, IOException;

    ResponseResult deleteCommodity(long commodityId);

    ResponseResult updateCommodity(CommodityDTO commodityDTO);
}
