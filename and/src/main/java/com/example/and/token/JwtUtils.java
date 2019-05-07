package com.example.and.token;

import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtUtils {
//    private Logger logger = LoggerFactory.getLogger(getClass());

    private String secret;
    private long expire;

    /**
     * 生成jwt token
     */
    public String createJWT(String id, Map<String, Object> claims) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setId(id)
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 解析JWT字符串
     */
    public Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证JWT
     */
    public boolean validateJWT(String jwtStr) {
        try {
            parseJWT(jwtStr);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

}