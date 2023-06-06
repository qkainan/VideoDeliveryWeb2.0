package com.feidian.service.impl;

import com.feidian.dto.CartDTO;
import com.feidian.mapper.CartMapper;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CartService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Override
    public ResponseResult uploadCart(CartDTO cartDTO) {
//        CommodityPO commodityPO = commodityService.findByCommodityId(cartDTO.getCommodityId());
//        long orderStatus = 0;
//
//        BigDecimal totalPrice = commodityPO.getPrice().multiply(cartDTO.getCommodityNum());
//
//        CartBO cartBO = new CartBO(0,cartDTO.getUserId(), cartDTO.getCommodityId(),
//                cartDTO.getAddressId(), commodityPO.getCommodityDescription(),
//                commodityPO.getPrice(), cartDTO.getCommodityNum(), totalPrice,
//                orderStatus);
//
//        cartService.insertCart(cartBO.getUserId(), cartBO.getCommodityId(),
//                cartBO.getAddressId(), cartBO.getCommodityDescription(),
//                cartBO.getPrice(), cartBO.getCommodityNum(), cartBO.getTotalPrice(),
//                cartBO.getOrderStatus());

        return new ResponseResult(200,"操作成功");
    }
}
