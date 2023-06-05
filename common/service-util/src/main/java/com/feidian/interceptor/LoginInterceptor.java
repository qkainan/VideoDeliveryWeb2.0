package com.feidian.interceptor;

import com.feidian.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }
            //获取请求头中的token
            String token = request.getHeader("Authorization");
            Enumeration<String> headerNames = request.getHeaderNames();
            //判断token是否为空，如果为空也代表未登录 提醒重新登录（401）
            if (!StringUtils.hasText(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            //解析token看看是否成功
            try {
                Claims claims = JwtUtil.parseJWT(token);
                String subject = claims.getSubject();
                System.out.println(subject);
            } catch (Exception e) {
                e.printStackTrace();
                //如果解析过程中没有出现异常说明是登录状态
                //如果出现了异常，说明未登录，提醒重新登录（401）
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

        return true;
    }

}
