package com.feidian.service.impl;

import com.feidian.bo.CartBO;
import com.feidian.dto.CartDTO;
import com.feidian.mapper.CartMapper;
import com.feidian.mapper.CommodityMapper;
import com.feidian.po.CommodityPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CartService;
import com.feidian.service.CommodityService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Mapper
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    public ResponseResult uploadCart(CartDTO cartDTO) {
        CommodityPO commodityPO = commodityMapper.findByCommodityId(cartDTO.getCommodityId());
        long orderStatus = 0;

        BigDecimal totalPrice = commodityPO.getPrice().multiply(cartDTO.getCommodityNum());

        CartBO cartBO = new CartBO(0,cartDTO.getUserId(), cartDTO.getCommodityId(),
                cartDTO.getAddressId(), commodityPO.getCommodityDescription(),
                commodityPO.getPrice(), cartDTO.getCommodityNum(), totalPrice,
                orderStatus);

        cartMapper.insertCart(cartBO);

        return new ResponseResult();
    }


}
