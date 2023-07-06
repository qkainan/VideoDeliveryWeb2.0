package com.feidian.controller;

import com.feidian.domain.User;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
