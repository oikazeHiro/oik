package com.oik.util.uncategorized;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {


    //wt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
    private final static String JWT_SEC_KEY = "OIK_JWT_SEC_SHIRO";

    private final static long JWT_EXPIRE = 2*60*60*1000;

    public static String createToken(String username,Long current) throws UnsupportedEncodingException {
        //token过期时间
        Date date=new Date(current+JWT_EXPIRE);

        //jwt的header部分
        Map<String ,Object>map=new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");

        //使用jwt的api生成token
        String token= null;//签名
        token = JWT.create()
                .withHeader(map)
                .withClaim("username", username)//私有声明
                .withClaim("current",current)//当前时间截点
                .withExpiresAt(date)//过期时间
                .withIssuedAt(new Date())//签发时间
                .sign(Algorithm.HMAC256(JWT_SEC_KEY));
        return token;
    }

    //校验token的有效性，1、token的header和payload是否没改过；2、没有过期
    public static boolean verify(String token){
        try {
            //解密
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(JWT_SEC_KEY)).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    //无需解密也可以获取token的信息
    public static String getUsername(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    //获取过期时间
    public static Long getExpire(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("current").asLong();
        } catch (Exception e) {
            return null;
        }
    }


//    public static void main(String[] args) {
//        boolean verify = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjdXJyZW50IjoxNjcwODI5OTUyODk0LCJleHAiOjE2NzA4MzE3NTIsImlhdCI6MTY3MDgyOTk1MiwidXNlcm5hbWUiOiJ0ZXN0MSJ9.kMonXszOmrliOQOdum733nBpYoMU8-dBiwZ-qq7HYGY");
//        System.out.println("verify = " + verify);
//
//    }
}
