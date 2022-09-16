package top.erhuoduoduo.security;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import top.erhuoduoduo.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: JwtToken过滤器
 * @author: collapsar
 * @create: 2021/12/06 13:31
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MyCustomUserService userDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt = request.getHeader(jwtUtils.getHeader());
        if(StrUtil.isBlankOrUndefined(jwt)){
            chain.doFilter(request,response);
            return;
        }

        Claims claims = jwtUtils.getClaimsByToken(jwt);
        if(claims == null){
            throw new JwtException("token 异常");
        }
        if(jwtUtils.isTokenExpired(claims)){
            throw new JwtException("token已过期");
        }

        String username = claims.getSubject();
        //获取用户权限等信息

        //用户鉴权部分得在这里实现
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username,null,userDetailService.getUserAuthority(username));

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }
}
