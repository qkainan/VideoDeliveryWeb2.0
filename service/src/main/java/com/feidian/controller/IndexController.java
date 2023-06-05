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
    public ResponseResult login(@RequestBody LoginDTO loginDTO) throws Exception {

        if (loginDTO.getPassword().length() >16 && loginDTO.getPassword().length() <8 ) {
            return new ResponseResult(403, "密码不符合要求");
        }

        //密码符合要求则开始验证
        UserPO userPO = userService.findByName(loginDTO.getUsername());
        Long id01 = userPO.getId();
        String username01 = userPO.getUsername();

        if (!StringUtils.hasText(username01)) {
            return new ResponseResult(403,"用户名不存在");
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
        String decryptUserPwd = AESUtil.decryptByAES(userPO.getPassword());

        if (!decryptUserPwd.equals(userPwd)) {
            userPO.setUserStatus(1);
            return new ResponseResult(403,"密码不正确");
        }

        //如果正确 生成token返回,并记录日志
        Map<String, Object> map;
        map = new HashMap<>();
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(id01), null);
        map.put("Authorization", token);
        userPO.setUserStatus(0);

        return new ResponseResult(200, "登录成功", map);
    }



}
