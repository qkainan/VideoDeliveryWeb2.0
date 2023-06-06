package com.feidian.controller;



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

//    @Transactional
//    @PostMapping("/postCart")
//    @ResponseBody
//    public ResponseResult postCart(@RequestBody CartDTO cartDTO){
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
//
//        return new ResponseResult(200,"操作成功");
//    }
//    @Transactional
//    @GetMapping("/getCartVOList")
//    public ResponseResult getCartVOList() {
//
//        List<CartPO> list = getCartList(JwtUtil.getUserId());
//        List<CartVO> cartVOList = new ArrayList<>();
//
//
//        for (CartPO cart : list) {
//
//            CommodityPO commodityPO = commodityService.findByCommodityId(cart.getCommodityId());
//            BigDecimal totalPrice = commodityPO.getPrice().multiply(cart.getCommodityNum());
//
//            CartVO cartVO = new CartVO(cart.getId(), cart.getUserId(), cart.getCommodityId(),
//                    cart.getAddressId(), commodityPO.getCommodityDescription(), commodityPO.getPrice(),
//                    cart.getCommodityNum(), totalPrice, cart.getOrderStatus(),
//                    cart.getUpdateTime());
//            cartVOList.add(cartVO);
//        }
//
//        return new ResponseResult(200, "操作成功", cartVOList);
//    }
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
//    @Transactional
//    @PostMapping("/postDeleteCart")
//    public ResponseResult postDeleteCart(long cartId){
//        cartService.deleteCart(cartId);
//        return new ResponseResult(200,"操作成功");
//    }
//
//
//    public List<CartPO> getCartList(long userId){
//        return cartService.findByUserId(userId);
//    }

}
