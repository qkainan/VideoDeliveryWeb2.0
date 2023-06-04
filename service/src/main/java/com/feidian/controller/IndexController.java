package com.feidian.controller;

import com.feidian.dto.SignupDTO;
import com.feidian.po.UserPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    //注册登录
    //快速注册
    @PostMapping("/fastSignup")
    public ResponseResult fastSignup(@RequestBody SignupDTO signupDTO){
        //Todo 规范性验证放在Controller层，业务性验证放在Service层
        return userService.fastSignup(signupDTO);
    }

}
