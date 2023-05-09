package com.ly.mall.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮箱工具类
 * @author LinYi
 */
@Component
public class EmailUtils {

    /**
     * @description 发送邮件的服务器
     * @date 2023/5/9 22:17
     */
    private static String HOSTNAME;

    /**
     * @description 发送邮件的邮箱号
     * @date 2023/5/9 22:17
     */
    private static String USERNAME;

    /**
     * @description 邮箱授权码
     * @date 2023/5/9 22:17
     */
    private static String PASSWORD;
    /**
     * @description 发件人
     * @date 2023/5/9 22:18
     */
    private static String NAME;
    @Value("${email.hostname}")
    public void setHostName(String HOSTNAME) {
        EmailUtils.HOSTNAME = HOSTNAME;
    }
    @Value("${email.username}")
    public void setUsername(String USERNAME) {
        EmailUtils.USERNAME = USERNAME;
    }
    @Value("${email.password}")
    public void SetPassword(String PASSWORD) {
        EmailUtils.PASSWORD = PASSWORD;
    }
    @Value("${email.name}")
    public void setName(String NAME) {
        EmailUtils.NAME = NAME;
    }

    /**
     * @param email: 接收邮件的邮箱地址
     * @param subject: 邮件主题
     * @param msg: 邮件信息
     * @author LinYi
     * @description 通过设定参数发送邮件
     * @date 2023/5/9 22:18
     */
    public static void sendEmail(String email,String subject,String msg) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
//        设置发送邮件的服务器
        mail.setHostName(HOSTNAME);
        mail.setCharset("UTF-8");
        /*IMAP/SMTP服务的密码 username为你开启发送验证码功能的邮箱号 password为你在qq邮箱获取到的一串字符串*/
        mail.setAuthentication(USERNAME, PASSWORD);
        /*发送邮件的邮箱和发件人*/
        mail.setFrom(USERNAME, NAME);
        /*使用安全链接*/
        mail.setSSLOnConnect(true);
        /*接收的邮箱*/
        mail.addTo(email);
        /*设置邮件的主题*/
        mail.setSubject(subject);
        /*设置邮件的内容*/
        mail.setMsg(msg);
        mail.send();//发送
    }

    /**
     * @param email: 接收邮件的邮箱地址
     * @param code: 一次性验证码
     * @author LinYi
     * @description 通过邮件发送验证码
     * @date 2023/5/9 22:23
     */
    public static void sendVerificationCode(String email,String code) throws EmailException {
        String subject = "你的一次性代码";
        String msg = "你的一次性代码为: " + code;
        sendEmail(email,subject,msg);
    }
}
