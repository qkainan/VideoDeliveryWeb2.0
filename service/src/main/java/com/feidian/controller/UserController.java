package com.feidian.controller;

import com.feidian.dto.AddressDTO;
import com.feidian.dto.UserDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AddressService;
import com.feidian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    //Todo 更新用户电话号码 更新邮箱地址
    @PutMapping("/updateUserHead")
    public ResponseResult updateUserHead(@RequestPart(name = "headFile") MultipartFile headFile) {
        return userService.updateUserHead(headFile);
    }

    @PutMapping("/updateUserDescription")
    public ResponseResult updateUserDescription(@RequestBody UserDTO userDTO) {
        return userService.updateUserDescription(userDTO);
    }

    @PostMapping("/uploadUserAddress")
    public ResponseResult uploadUserAddress(@RequestBody AddressDTO addressDTO){
        return addressService.uploadUserAddress(addressDTO);
    }

}
