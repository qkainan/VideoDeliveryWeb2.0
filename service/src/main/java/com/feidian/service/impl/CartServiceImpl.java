package com.feidian.service.impl;

import com.feidian.bo.CartBO;
import com.feidian.dto.CartDTO;
import com.feidian.mapper.CartMapper;
import com.feidian.mapper.CommodityMapper;
import com.feidian.po.CartPO;
import com.feidian.po.CommodityPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CartService;
import com.feidian.service.CommodityService;
import com.feidian.util.JwtUtil;
import com.feidian.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ResponseResult displayCartVOList() {
        List<CartPO> list = cartMapper.findByUserId(JwtUtil.getUserId());
        List<CartVO> cartVOList = new ArrayList<>();


        for (CartPO cart : list) {

            CommodityPO commodityPO = commodityMapper.findByCommodityId(cart.getCommodityId());
            BigDecimal totalPrice = commodityPO.getPrice().multiply(cart.getCommodityNum());

            CartVO cartVO = new CartVO(cart.getId(), cart.getUserId(), cart.getCommodityId(),
                    cart.getAddressId(), commodityPO.getCommodityDescription(), commodityPO.getPrice(),
                    cart.getCommodityNum(), totalPrice, cart.getOrderStatus(),
                    cart.getUpdateTime());
            cartVOList.add(cartVO);
        }

        return ResponseResult.successResult(cartVOList);
    }


}
