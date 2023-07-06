package com.feidian.mapper;

import com.feidian.bo.UserBO;
import com.feidian.po.UserPO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    void insertUser(UserBO userBO);

    UserPO findByName(String username);

    void updateUserInfo(UserBO userBO);

    UserPO findById(Long id);
}
