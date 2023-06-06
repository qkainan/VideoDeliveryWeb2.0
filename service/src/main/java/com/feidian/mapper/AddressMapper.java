package com.feidian.mapper;

import com.feidian.dto.AddressDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddressMapper {
    void insertAddress(AddressDTO addressDTO);

    void deleteAddress(long addressId);

    void updateAddressInfo(AddressDTO addressDTO);
}
