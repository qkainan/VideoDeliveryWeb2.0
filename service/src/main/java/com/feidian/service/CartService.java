package com.feidian.service;

import com.feidian.dto.CartDTO;
import com.feidian.responseResult.ResponseResult;

public interface CartService {
    ResponseResult displayCartVOList();

    ResponseResult uploadCart(CartDTO cartDTO);
}
