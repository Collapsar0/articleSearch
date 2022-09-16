package top.erhuoduoduo;

import org.junit.Test;
import top.erhuoduoduo.utils.MyPasswordEncoder;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: ’À∫≈√‹¬Îº”√‹
 * @author: collapsar
 * @create: 2022/03/09 01:49
 */
public class getPassword {

    String usersPwd = "testuser";
//    @Test
    public void getPassword(){
        System.out.println(new MyPasswordEncoder().encode(usersPwd));
    }
}
