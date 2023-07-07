package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.UserBO;
import com.feidian.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void insertUser(UserBO userBO);

    User findByName(String username);

    void updateUserInfo(UserBO userBO);

    User findById(Long id);
}
