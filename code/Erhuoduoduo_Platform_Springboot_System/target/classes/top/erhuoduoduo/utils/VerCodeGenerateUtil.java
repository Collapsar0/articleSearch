package top.erhuoduoduo.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 生成6位随机验证码
 * @author: collapsar
 * @create: 2021/12/02 20:32
 */
public class VerCodeGenerateUtil {
    private static final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();

    /**
     * 生成6位随机数字
     * @return 返回6位数字验证码
     */
    public static String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}