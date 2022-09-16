package top.erhuoduoduo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: JWT工具类
 * @author: collapsar
 * @create: 2021/12/06 10:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtUtils {

    private long expire;
    private String secret;
    private String header;

    //生成JWT
    public String generateToken(String username){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    //解析JWT
    public Claims getClaimsByToken(String jwt){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    //JWT是否过期
    public  boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }
}
