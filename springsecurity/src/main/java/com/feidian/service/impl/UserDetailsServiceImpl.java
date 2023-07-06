package com.feidian.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.feidian.domain.LoginUser;
import com.feidian.domain.User;
import com.feidian.mapper.UserMapper;
import com.feidian.responseResult.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, "qkainan");
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查到就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        //TODO 查询对应的权限信息
        //测试写法
        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        //把数据封装成UserDetails返回
        LoginUser loginUser = new LoginUser(user, list);
        return null;
    }
}
