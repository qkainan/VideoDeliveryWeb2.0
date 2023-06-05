package com.feidian.controller;

import com.feidian.dto.UserDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Todo 更新用户电话号码 更新邮箱地址
    @PutMapping("/updateUserHead")
    public ResponseResult updateUserHead(@RequestPart(name = "headFile") MultipartFile headFile) {
        return userService.updateUserHead(headFile);
    }

    @PutMapping("/updateUserDescription")
    public ResponseResult updateUserDescription(@RequestBody UserDTO userDTO) {
        return userService.updateUserDescription(userDTO);
    }

}
