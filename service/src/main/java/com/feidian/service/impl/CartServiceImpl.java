package com.feidian.service.impl;

import com.feidian.bo.CartBO;
import com.feidian.bo.OrderBO;
import com.feidian.dto.CartDTO;
import com.feidian.dto.PurchaseDTO;
import com.feidian.mapper.*;
import com.feidian.po.Address;
import com.feidian.po.Cart;
import com.feidian.po.Commodity;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CartService;
import com.feidian.util.JwtUtil;
import com.feidian.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderCommodityMapper orderCommodityMapper;


    @Transactional
    @Override
    public ResponseResult uploadCart(CartDTO cartDTO) {
        Commodity commodityPO = commodityMapper.findByCommodityId(cartDTO.getCommodityId());
        Long orderStatus = 0L;

        BigDecimal totalPrice = commodityPO.getPrice().multiply(cartDTO.getCommodityNum());

        CartBO cartBO = new CartBO(0L,cartDTO.getUserId(), cartDTO.getCommodityId(),
                cartDTO.getAddressId(), commodityPO.getCommodityDescription(),
                commodityPO.getPrice(), cartDTO.getCommodityNum(), totalPrice,
                orderStatus);

        cartMapper.insertCart(cartBO);

        return new ResponseResult();
    }

    @Transactional
    @Override
    public ResponseResult displayCartVOList() {
        List<Cart> list = cartMapper.findByUserId(JwtUtil.getUserId());
        List<CartVO> cartVOList = new ArrayList<>();


        for (Cart cart : list) {

            Commodity commodityPO = commodityMapper.findByCommodityId(cart.getCommodityId());
            BigDecimal totalPrice = commodityPO.getPrice().multiply(cart.getCommodityNum());

            CartVO cartVO = new CartVO(cart.getId(), cart.getUserId(), cart.getCommodityId(),
                    cart.getAddressId(), commodityPO.getCommodityDescription(), commodityPO.getPrice(),
                    cart.getCommodityNum(), totalPrice, cart.getOrderStatus(),
                    cart.getUpdateTime());
            cartVOList.add(cartVO);
        }

        return ResponseResult.successResult(cartVOList);
    }

    @Override
    public ResponseResult deleteCart(Long cartId) {
        cartMapper.deleteCart(cartId);
        return ResponseResult.successResult();
    }

    @Override
    public ResponseResult cartPurchase(PurchaseDTO purchaseDTO) {
        Long userId = JwtUtil.getUserId();

        if (purchaseDTO.getId() != 0) {
            Cart cart = cartMapper.findByCartId(purchaseDTO.getId());
            //orderStatus（1：已购买 0：未购买）
            cartMapper.updateOrderStatus(cart.getId());
            cartMapper.deleteCart(cart.getId());
        }

        //状态（5：已收货 4：代发货 3：已发货 1：待发货 0：已退款 ）
        Long orderStatus = 1L;
        Commodity commodityPO = commodityMapper.findByCommodityId(purchaseDTO.getCommodityId());
        Address address = addressMapper.findByAddressId(purchaseDTO.getAddressId());

        //Todo order orderCommodity同步更新
        OrderBO orderBO = new OrderBO( userId, commodityPO.getUserId(), address.getAddressName(), orderStatus);

        orderMapper.insertOrder(orderBO);

        orderCommodityMapper.insertOrderCommodity(orderBO.getId(),commodityPO.getId(),purchaseDTO.getCommodityNum());

        return new ResponseResult(200, "购买成功");
    }

}
