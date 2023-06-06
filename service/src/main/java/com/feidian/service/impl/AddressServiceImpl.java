package com.feidian.service.impl;

import com.feidian.dto.AddressDTO;
import com.feidian.mapper.AddressMapper;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ResponseResult uploadUserAddress(AddressDTO addressDTO) {
        return null;
    }
}
