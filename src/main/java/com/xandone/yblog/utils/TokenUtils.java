package com.xandone.yblog.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author ：xandone
 * created on  ：2020/10/16 9:51
 * description：
 */
public class TokenUtils {

    /**
     * The constant logger
     */
    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * 秘钥
     */
    private static final String signkey = "@#$FD7H8@";

    /**
     * 过期时间,一天
     */
//    private static final int EXPIRES_DATE = 60;
    private static final int EXPIRES_DATE = 60 * 60 * 24;

    /**
     * The constant algorithm .
     */
    private static Algorithm algorithm = Algorithm.HMAC256(signkey);


    /**
     * Get token string .
     * 生成token
     *
     * @param adminId 管理员id
     * @return the string
     */

    public static String getToken(String adminId) {

        Calendar calendar = Calendar.getInstance();
        //签发时间
        Date issueDate = calendar.getTime();
        //过期时间
        calendar.add(Calendar.SECOND, EXPIRES_DATE);
        Date expiresDate = calendar.getTime();

        //header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(header)  //设置header头信息
                .withClaim("id", adminId)   //设置payload
                .withIssuedAt(issueDate)
                .withExpiresAt(expiresDate)
                .sign(algorithm);
        return token;
    }


    /**
     * Verify token map.对token进行解密
     *
     * @param token the token
     * @return true:通过
     */
    public static boolean verifyToken(String token) {

        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT verify = null;
        try {
            verify = jwtVerifier.verify(token);
            verify.getClaims();
            return true;
        } catch (TokenExpiredException ex) {
            logger.error("token已经过期", ex);
            System.out.println("token已经过期");
        } catch (Exception e) {
            logger.error("登录异常", e);
            System.out.println("登录异常");
        }
        return false;
    }


//    /**
//     * The entry point ofapplication .
//     *
//     * @param args the input arguments
//     */

//    public static void main(String[] args) {
//        String adminId = "250";
//        String token = getToken(adminId);
//        System.out.println(token);
//        //验证token是否正确
//        Map<String, Claim> claims = verifyToken(token);
//        if (claims != null) {
//            Claim idClaim = claims.get("id");
//            //此处获得的信息可以到数据库中进行查询，验证是否存在该用户信息
//            System.out.println(adminId.equals(idClaim.asString()) ? "token验证成功" : "token验证失败");
//        }

//        用一个过期的token来验证结果
//        String token2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjI1MCIsImV4cCI6MTYwMjgxNjk3MSwiaWF0IjoxNjAyODE2OTY2fQ.0EVHamk-MSJ1OoikSm8QRo-25qYJFlgAf1xb8lprSzg";
//        verifyToken(token2);

//    }
}

