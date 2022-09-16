package top.erhuoduoduo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.erhuoduoduo.utils.JwtUtils;

import java.util.Date;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 测试主类
 * @author: collapsar
 * @create: 2021/12/01 01:43
 */
@SpringBootTest
public class getToken {

    private long expire = 604800;
    private String secret = "aHR0cHM6Ly9tLm5ldC91LzM2ODE4Njg=";
    private String header = "Authorization";

    //生成JWT
    public String generateTokenTest(String username){
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

//    @Test
    public void contextLoads(){
        System.out.println(generateTokenTest("testuser"));
    }

}
