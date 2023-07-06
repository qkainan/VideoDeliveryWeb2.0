package com.feidian.service;

import com.feidian.domain.User;
import com.feidian.responseResult.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
