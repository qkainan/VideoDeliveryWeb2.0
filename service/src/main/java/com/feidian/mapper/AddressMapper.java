package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.AddressBO;
import com.feidian.po.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    void insertAddress(AddressBO addressBO);

    void deleteAddress(Long addressId);

    void updateAddressInfo(AddressBO addressBO);

    Address findByAddressId(Long addressId);
}
