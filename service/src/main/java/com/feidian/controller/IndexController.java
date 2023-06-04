package com.feidian.controller;

import com.feidian.dto.SignupDTO;
import com.feidian.po.UserPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.service.UtilService;
import com.feidian.util.AESUtil;
import com.feidian.util.EmailUtil;
import com.feidian.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @Transactional
    @PostMapping("/sendVerifyCode")
    public ResponseResult sendVerifyCode(@RequestBody SignupDTO signupDTO) {
        return utilService.sendVerifyCode(signupDTO.getEmailAddress(), signupDTO.getUsername());
    }


}
