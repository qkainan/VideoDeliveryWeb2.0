package com.feidian.mapper;

import com.feidian.bo.UserBO;
import com.feidian.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    User findByName(String username);

    void updateUserInfo(UserBO userBO);

    User findById(Long id);
}
