package com.feidian.mapper;

import com.feidian.bo.CartBO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface CartMapper {

    void insertCart(CartBO cartBO);
}
