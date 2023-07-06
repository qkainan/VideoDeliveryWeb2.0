package com.feidian.service.impl;

import com.feidian.bo.UserBO;
import com.feidian.domain.AuthenticatedUser;
import com.feidian.domain.User;
import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.enums.HttpCodeEnum;
import com.feidian.po.UserPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AuthenticationService;
import com.feidian.service.SecurityUserService;
import com.feidian.util.AESUtil;
import com.feidian.utils.JwtUtil;
import com.feidian.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.feidian.enums.HttpCodeEnum.REQUIRE_USERNAME;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SecurityUserService securityUserService;


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
        securityUserService.insertUser(userBO);
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
        Boolean verifyResult = ((String)redisCache.getCacheObject(signupDTO.getUsername()+ "verifyCode")).equals(signupDTO.getVerifyCode());

        if (false == verifyResult) {
            return  ResponseResult.errorResult(403,"验证码错误");
        }

        String encryptUserPwd = getEncryptUserPwd(signupDTO.getPassword()).getData().toString();
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd,
                signupDTO.getNickname(), signupDTO.getEmailAddress());
        securityUserService.insertUser(userBO);
        return ResponseResult.successResult(200, "邮箱注册成功");
    }

    @Override
    public ResponseResult login(LoginDTO loginDTO) {
        if (loginDTO.getPassword().length() >16 && loginDTO.getPassword().length() <8 ) {
            return ResponseResult.errorResult(403, "密码不符合要求");
        }

        //密码符合要求则开始验证
        UserPO userPO = securityUserService.findByName(loginDTO.getUsername());
        Long id01 = userPO.getId();
        String username01 = userPO.getUsername();

        if (!StringUtils.hasText(username01)) {
            return ResponseResult.errorResult(403,"用户名不存在");
        }

        //验证密码是否正确
        //补全用户输入的密码
        String userPwd = "";
        StringBuilder stringBuilder = new StringBuilder(loginDTO.getPassword());
        if (16 > loginDTO.getPassword().length()){
            for (int i = loginDTO.getPassword().length() ; i < 16; i++) {
                stringBuilder = stringBuilder.append("=");
            }
        }
        userPwd = stringBuilder.toString();

        //获取解密后的密码
        String decryptUserPwd = null;
        try {
            decryptUserPwd = AESUtil.decryptByAES(userPO.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!decryptUserPwd.equals(userPwd)) {
            userPO.setUserStatus(1L);
            return ResponseResult.errorResult(403,"密码不正确");
        }

        //如果正确 生成token返回,并记录日志
        Map<String, Object> map;
        map = new HashMap<>();
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(id01), null);
        map.put("Authorization", token);
        userPO.setUserStatus(0L);

        return ResponseResult.successResult(map);
    }

    @Override
    public ResponseResult login(User user) {

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authentication)){
            return ResponseResult.errorResult(HttpCodeEnum.LOGIN_ERROR);
        }

        //如果认证通过，使用userid生成一个jwt 存入ResponseResult
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
        String userId = String.valueOf(authenticatedUser.getUser().getId());
        String jwt = JwtUtil.createJWT(userId);

        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的与用户信息存入redis，userid作为key
        redisCache.setCacheObject("login:" + userId , authenticatedUser);

        return ResponseResult.successResult(200,"登陆成功");
    }

    @Override
    public ResponseResult logout() {
        //从SecurityContextHolder中获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticatedUser loginUser = (AuthenticatedUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.successResult(200,"注销成功");
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
