package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.AddressBO;
import com.feidian.po.AddressPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddressMapper extends BaseMapper<AddressPO> {
    void insertAddress(AddressBO addressBO);

    void deleteAddress(Long addressId);

    void updateAddressInfo(AddressBO addressBO);

    AddressPO findByAddressId(Long addressId);
}
