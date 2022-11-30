package com.novax.ex.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Description:JWT Util
 *
 * @author shaw
 * @date 6/28/22 2:03 PM
 */
public class JWTUtil {
    /**
     * token过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * 加密密钥
     */
    private static final String SECRET = "8J$hdHs/d98&%2jsl10";
    /**
     * JWT redis key
     */
    public static final String REDIS_KEY_JWT = "jwt:";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String userName, Long userId, String loginType, String salt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userName", userName)
                    .withClaim("userId", userId)
                    .withClaim("loginType", loginType)
                    .withClaim("salt", salt)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户id
     */
    public static Long getUid(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中登录用户类型
     */
    public static String getLoginType(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginType").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中唯一标识
     */
    public static String getSalt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("salt").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 用于管理端多用户登录
     */
    public static String getSecret(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("secret").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 生成签名,30min后过期
     *
     * @param userName 用户名
     * @return 加密的token
     */
    public static String sign(String userName, String type, String salt, Long userId) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim("userName", userName)
                .withClaim("loginType", type)
                .withClaim("userId", userId)
                .withClaim("salt", salt)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * D
     * 生成签名,30min后过期
     *
     * @param userName 用户名
     * @return 加密的token
     */
    public static String adminSign(String userName, String loginType, String salt, Long userId, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim("userName", userName)
                .withClaim("loginType", loginType)
                .withClaim("userId", userId)
                .withClaim("secret", secret)
                .withClaim("salt", salt)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 用户端token key
     *
     * @param token token
     * @return string
     */
    public static String getTokenKey(String token) {
        return REDIS_KEY_JWT + getLoginType(token) + ":" + getUid(token);
    }

    /**
     * 管理端 token key
     *
     * @param token token
     * @return string
     */
    public static String getAdminTokenKey(String token) {
        return getLoginType(token) + ":" + REDIS_KEY_JWT + getUid(token) + ":" + getSecret(token);
    }

//
//    public static String delay(String token, String salt) {
//        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
//        Algorithm algorithm = Algorithm.HMAC256(SECRET);
//        // 附带username信息
//        return JWT.create()
//                .withClaim("username", getUsername(token))
//                .withClaim("loginType", getLoginType(token))
//                .withClaim("userId",getUid(token))
//                .withClaim("salt",salt)
//                .withExpiresAt(date)
//                .sign(algorithm);
//    }
}
