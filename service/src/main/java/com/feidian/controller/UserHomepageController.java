package com.feidian.controller;

import com.feidian.po.*;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.*;
import com.feidian.util.JwtUtil;
import com.feidian.vo.PurchaseOrderVO;
import com.feidian.vo.SaleOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserHomepageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CartService cartService;



//    @Transactional
//    @GetMapping("/getPerinfo")
//    public ResponseResult getHomePage(){
//        long userId = JwtUtil.getUserId();
//
//        UserPO userPO = userService.findById(userId);
//
//        List<VideoPO> videoPOList = videoService.findByUserId(userId);
//        List<CommodityPO> commodityPOList = commodityService.findByUserId(userId);
//        List<PurchaseOrderVO> buyerOrderVoList = getPurchaseOrderVo(userId);
//        List<SaleOrderVO> sellerOrderVoList = getSaleOrderVo(userId);
//        List<CartPO> cartList = cartService.findByUserId(userId);
//
//        UserHomepageVO userHomepageVo = new UserHomepageVO(userId, userPO.getUsername(),
//                userPO.getUserDescription(), userPO.getPhone(), userPO.getHeadUrl(),
//                userPO.getEmailAddress(), videoPOList, commodityPOList, buyerOrderVoList,sellerOrderVoList, cartList);
//        return  new ResponseResult(200,"操作成功",userHomepageVo);
//    }



}
