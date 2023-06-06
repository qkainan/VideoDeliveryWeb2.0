package com.feidian.controller;



import com.feidian.dto.CartDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.*;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderCommodityService orderCommodityService;

    //上传到购物车
    @PostMapping("/uploadCart")
    @ResponseBody
    public ResponseResult uploadCart(@RequestBody CartDTO cartDTO){
        return cartService.uploadCart(cartDTO);
    }

    //展示购物车列表
    @GetMapping("/displayCartVOList")
    public ResponseResult displayCartVOList() {
        return cartService.displayCartVOList();
    }

    //删除购物车
    @PostMapping("/deleteCart")
    public ResponseResult deleteCart(long cartId){
        return cartService.deleteCart(cartId);
    }


//
//    @Transactional
//    @PutMapping("/putCartStatus")
//    public ResponseResult putCartStatus(@RequestBody CartDTO cartDTO){
//        cartService.updateOrderStatus(cartDTO.getId());
//        return new ResponseResult(200,"更新成功");
//    }
//    @Transactional
//    @PostMapping("/postCartPurchase")
//    public ResponseResult postCartPurchase(@RequestBody PurchaseDTO purchaseDTO) {
//        long userId = JwtUtil.getUserId();
//
//        if (purchaseDTO.getId() != 0) {
//            CartPO cartPO = cartService.findByCartId(purchaseDTO.getId());
//            cartService.updateOrderStatus(cartPO.getId());
//            cartService.deleteCart(cartPO.getId());
//        }
//
//        //状态（5：已收货 4：代发货 3：已发货 2：代发货 0：已退款 ）
//        long orderStatus = 2;
//        CommodityPO commodityPO = commodityService.findByCommodityId(purchaseDTO.getCommodityId());
//        AddressPO address = userService.findByAddressId(purchaseDTO.getAddressId());
//
//        //Todo order orderCommodity同步更新
//        OrderBO orderBO = new OrderBO( userId, commodityPO.getUserId(), address.getAddressName(), orderStatus);
//
//        orderService.insertOrder(orderBO);
//
//        orderCommodityService.insertOrderCommodity(orderBO.getId(),commodityPO.getId(),purchaseDTO.getCommodityNum());
//
//        return new ResponseResult(200, "购买成功");
//
//    }
//
//    @Transactional
//    @PostMapping("/postTakeCommodity")
//    public ResponseResult postTakeCommodity(long orderId){
//        orderService.updateStatus(orderId);
//        return new ResponseResult(200,"操作成功");
//    }
//

}
