package top.erhuoduoduo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 自定义的密码加密方法，实现了PasswordEncoder接口
 * @author: collapsar
 * @create: 2021/12/06 00:03
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public String encode(CharSequence charSequence) {
        //加密方法可以根据自己的需要修改
        // 设置默认的加密方式（强hash方式加密）
        //return charSequence.toString();
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
    //return encode(charSequence).equals(s);
      return passwordEncoder.matches(charSequence, s);
    }
}
