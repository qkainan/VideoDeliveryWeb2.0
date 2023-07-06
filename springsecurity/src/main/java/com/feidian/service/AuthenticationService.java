package com.feidian.service;

import com.feidian.domain.User;
import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.responseResult.ResponseResult;

public interface AuthenticationService {

    ResponseResult fastSignup(SignupDTO signupDTO);

    ResponseResult emailSignup(SignupDTO signupDTO);

    ResponseResult login(LoginDTO loginDTO);

    ResponseResult login(User user);

    ResponseResult logout();
}
