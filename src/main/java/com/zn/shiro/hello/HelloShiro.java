package com.zn.shiro.hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhoun on 2018/7/29.
 **/
public class HelloShiro {

    private static final Logger logger = LoggerFactory.getLogger(HelloShiro.class);

    public static void main(String[] args) {
        //初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //登录
        UsernamePasswordToken token = new UsernamePasswordToken("shiro", "123456");

        try {
            subject.login(token);
        } catch (Exception e) {
            logger.info("登录失败");
            return;
        }
        logger.info("登录成功！Hello" + subject.getPrincipal());
        //注销
        subject.logout();

    }
}
