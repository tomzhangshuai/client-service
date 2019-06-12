package com.wufanbao.api.clientservice.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 头部（header）
 * 头部一般有两部分信息：声明类型、声明加密的算法（通常使用HMAC SHA256）
 * 头部一般使用base64加密：eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
 * 解密后：
 * <p>
 * {
 * "typ":"JWT",
 * "alg":"HS256"
 * }
 * 载荷（payload）
 * 该部分一般存放一些有效的信息。jwt的标准定义包含五个字段：
 * <p>
 * iss：该JWT的签发者
 * sub: 该JWT所面向的用户
 * aud: 接收该JWT的一方
 * exp(expires): 什么时候过期，这里是一个Unix时间戳
 * iat(issued at): 在什么时候签发的
 * 这个只是JWT的定义标准，不强制使用。另外自己也可以添加一些公开的不涉及安全的方面的信息。
 * <p>
 * 签证（signature）
 * JWT最后一个部分。该部分是使用了HS256加密后的数据；包含三个部分：
 * <p>
 * header (base64后的)
 * payload (base64后的)
 * secret 私钥
 * secret是保存在服务器端的，jwt的签发生成也是在服务器端的，secret就是用来进行jwt的签发和jwt的验证，所以，它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
 */

public class JwtHelper {

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 构建jwt
     */
    public static String createJWT(long userId, String loginNo, String audience, String issuer, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("loginNo", loginNo)
                .claim("userId", userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }
}

