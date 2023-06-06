package com.feidian.service.impl;

import com.feidian.dto.AddressDTO;
import com.feidian.mapper.AddressMapper;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AddressService;
import com.feidian.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ResponseResult uploadUserAddress(AddressDTO addressDTO) {
        addressDTO.setUserId(JwtUtil.getUserId());
        addressMapper.insertAddress(addressDTO);
        return ResponseResult.successResult();
    }

    @Override
    public ResponseResult deleteAddress(long addressId) {
        addressMapper.deleteAddress(addressId);
        return ResponseResult.successResult();
    }

    @Override
    public ResponseResult updateAddressInfo(AddressDTO addressDTO) {
        addressMapper.updateAddressInfo(addressDTO);
        return ResponseResult.successResult();
    }


}
