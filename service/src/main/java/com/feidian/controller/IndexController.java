package com.feidian.controller;

import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.po.UserPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.service.UtilService;
import com.feidian.util.AESUtil;
import com.feidian.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private UtilService utilService;

    //注册登录
    //快速注册
    @PostMapping("/fastSignup")
    public ResponseResult fastSignup(@RequestBody SignupDTO signupDTO){
        //Todo 规范性验证放在Controller层，业务性验证放在Service层
        return userService.fastSignup(signupDTO);
    }

    //发送验证码
    @PostMapping("/sendVerifyCode")
    public ResponseResult sendVerifyCode(@RequestBody SignupDTO signupDTO) {
        return utilService.sendVerifyCode(signupDTO.getEmailAddress(), signupDTO.getUsername());
    }

    //邮箱注册
    @PostMapping("/emailSignup")
    public ResponseResult emailSignup(@RequestBody SignupDTO signupDTO) throws Exception {
        //Todo 规范性验证放在Controller层，业务性验证放在Service层
        return userService.emailSignup(signupDTO);
    }

    //登录
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }



}
