package cn.xilio.turtle;

import cn.xilio.turtle.core.security.SecureManager;
/**
 * TIP：密码生成工具类 当前改密码需要通过该工具类修改
 */
public abstract class PasswordGeneratorUtils {

    public static void main(String[] args) {

        SecureManager secureManager = new SecureManager();
        //将生成的密码保存到user表中的password字段
        secureManager.encrypt("123456").doOnNext(System.out::println).block();
    }
}
