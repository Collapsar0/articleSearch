package top.erhuoduoduo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: SpringBoot项目启动项
 * @author: collapsar
 * @create: 2021/11/29 21:00
 */

@MapperScan("top.erhuoduoduo.mapper")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
