package com.feidian.service;

import com.feidian.dto.SignupDTO;
import com.feidian.responseResult.ResponseResult;

public interface UserService {
    ResponseResult fastSignup(SignupDTO signupDTO);

    ResponseResult emailSignup(SignupDTO signupDTO);
}
