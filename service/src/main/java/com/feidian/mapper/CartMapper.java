package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.CartBO;
import com.feidian.po.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    void insertCart(CartBO cartBO);

    List<Cart> findByUserId(Long userId);

    void deleteCart(Long cartId);

    Cart findByCartId(Long id);

    void updateOrderStatus(Long id);
}
