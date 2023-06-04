package com.feidian.mapper;

import com.feidian.bo.UserBO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    void insertUser(UserBO userBO);

}
