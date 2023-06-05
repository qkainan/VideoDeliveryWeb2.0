package com.feidian.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "maibao";

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //判断是否使用默认超时时间
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        // 设置过期时间
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        //创建秘钥
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)              //唯一的ID
                .setSubject(subject)   // 主体  可以是JSON数据
                .setIssuer(JWT_KEY)     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        // 本地的密码解码，这里自定义
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 从token中获取subject
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static long getSubject(String token) {
        try {
            if (!StringUtils.hasText(token)) return 0;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId = (String) claims.get("sub");
            return Integer.parseInt(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getUserId(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest req = sra.getRequest();

        String token = req.getHeader("Authorization");
        long userId = JwtUtil.getSubject(token);
        return userId;
    }

}