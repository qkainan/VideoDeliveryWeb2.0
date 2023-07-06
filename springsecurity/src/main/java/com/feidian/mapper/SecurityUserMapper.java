package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.UserBO;
import com.feidian.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SecurityUserMapper extends BaseMapper<User> {

    void insertUser(UserBO userBO);

}
