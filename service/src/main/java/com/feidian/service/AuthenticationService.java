package com.feidian.service;

import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.po.User;
import com.feidian.responseResult.ResponseResult;

////认证、授权用Service
public interface AuthenticationService {

    ResponseResult fastSignup(SignupDTO signupDTO);

    ResponseResult emailSignup(SignupDTO signupDTO);

    ResponseResult login(LoginDTO loginDTO);

    ResponseResult login(User user);

    ResponseResult logout();
}
