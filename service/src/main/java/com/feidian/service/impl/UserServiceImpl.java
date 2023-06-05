package com.feidian.service.impl;

import com.feidian.bo.UserBO;
import com.feidian.dto.SignupDTO;
import com.feidian.mapper.UserMapper;
import com.feidian.po.UserPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.util.AESUtil;
import com.feidian.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.feidian.enums.HttpCodeEnum.REQUIRE_USERNAME;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public ResponseResult fastSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到25之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        String regexPwd = "\\w{8,25}";
        if (signupDTO.getUsername()==null){
            return ResponseResult.errorResult(REQUIRE_USERNAME);
        }

        if (signupDTO.getPassword().matches(regexPwd) == false) {
            return ResponseResult.errorResult(403, "密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.errorResult(403, "第二次输入密码与第一次不同");
        }

        //加密密码并创建用户
        //补全16位
        String encryptUserPwd = null;

        encryptUserPwd = getEncryptUserPwd(signupDTO.getPassword()).getData().toString();
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd,
                signupDTO.getNickname(), signupDTO.getEmailAddress());
        userMapper.insertUser(userBO);
        return ResponseResult.successResult(200, "快速注册成功");
    }

    @Transactional
    @Override
    public ResponseResult emailSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到16之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        // 5.邮箱验证
        String regexPwd = "\\w{8,16}";

        if (signupDTO.getUsername()==null){
            return ResponseResult.errorResult(REQUIRE_USERNAME);
        }

        if (false == signupDTO.getPassword().matches(regexPwd)) {
            return ResponseResult.errorResult(403,"密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.successResult(403, "第二次输入密码与第一次不同");
        }

        String regexEmailAddress = "\\w+@[\\w&&[^_]]{2,7}(\\.[a-zA-Z]{2,4}){1,3}";

        if (!signupDTO.getEmailAddress().matches(regexEmailAddress)) {
            return ResponseResult.errorResult(403,"邮箱格式不正确");
        }

        //验证邮箱验证码
        Boolean verifyResult = ((String)redisTemplate.opsForValue().get(signupDTO.getUsername()+ "verifyCode")).equals(signupDTO.getVerifyCode());

        if (false == verifyResult) {
            return  ResponseResult.errorResult(403,"验证码错误");
        }

        String encryptUserPwd = getEncryptUserPwd(signupDTO.getPassword()).getData().toString();
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd,
                signupDTO.getNickname(), signupDTO.getEmailAddress());
        userMapper.insertUser(userBO);
        return ResponseResult.successResult(200, "邮箱注册成功");
    }

    @Transactional
    @Override
    public UserPO findByName(String username) {
        return userMapper.findByName(username);
    }

    //加密用户密码
    public ResponseResult getEncryptUserPwd(String password) {
        String userPwd = "";
        StringBuilder stringBuilder = new StringBuilder(password);
        //补全
        if (16 > password.length()){
            for (int i = password.length() ; i < 16; i++) {
                stringBuilder = stringBuilder.append("=");
            }
        }

        userPwd = stringBuilder.toString();
        String encryptUserPwd = null;
        try {
            encryptUserPwd = AESUtil.encryptByAES(userPwd);
            return ResponseResult.successResult(encryptUserPwd);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
