package com.feidian.vo;

import com.feidian.po.Cart;
import com.feidian.po.Commodity;
import com.feidian.po.Order;
import com.feidian.po.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHomepageVO {
    private Long id;
    private String username;
    private String userDescription;
    private String phone;
    private String headUrl;
    private String emailAddress;

    private List<Video> videoPOList;

    private List<Commodity> commodityPOList;

    private List<Order> buyerOrderPOList;

    private List<Order> sellerOrderPOList;

    private List<Cart> cartList;

}
