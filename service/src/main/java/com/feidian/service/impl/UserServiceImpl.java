package com.feidian.service.impl;

import com.feidian.bo.UserBO;
import com.feidian.dto.SignupDTO;
import com.feidian.mapper.UserMapper;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.feidian.enums.HttpCodeEnum.SYSTEM_ERROR;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Transactional
    @Override
    public ResponseResult fastSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到25之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        String regexPwd = "\\w{8,25}";

        if (signupDTO.getPassword().matches(regexPwd) == false) {
            return ResponseResult.errorResult(403,"密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.errorResult(403,"第二次输入密码与第一次不同");
        }

        //加密密码并创建用户
        //补全16位
        String encryptUserPwd = null;
        try {
            encryptUserPwd = getEncryptUserPwd(signupDTO.getPassword());
            UserBO userBO = new UserBO( signupDTO.getUsername(), encryptUserPwd,
                    signupDTO.getNickname(),signupDTO.getEmailAddress());
            userMapper.insertUser(userBO);
            return ResponseResult.successResult(200,"快速注册成功");
        } catch (Exception e) {
            return ResponseResult.errorResult(SYSTEM_ERROR,e.getMessage());
        }

    }

    //加密用户密码
    public String getEncryptUserPwd(String password) throws Exception {
        String userPwd = "";
        StringBuilder stringBuilder = new StringBuilder(password);
        //补全
        if (16 > password.length()){
            for (int i = password.length() ; i < 16; i++) {
                stringBuilder = stringBuilder.append("=");
            }
        }

        userPwd = stringBuilder.toString();
        String encryptUserPwd = AESUtil.encryptByAES(userPwd);
        return encryptUserPwd;
    }

}
