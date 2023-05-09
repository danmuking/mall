package com.ly.mall.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
/**
 * 邮箱工具类
 */
public class EmailUtils {
    public static void sendEmail(String email,String subject,String msg) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
//        设置发送邮件的服务器
        mail.setHostName("smtp.126.com");
        mail.setCharset("UTF-8");
        /*IMAP/SMTP服务的密码 username为你开启发送验证码功能的邮箱号 password为你在qq邮箱获取到的一串字符串*/
        mail.setAuthentication("lingyi550210817@126.com", "BSEXPDPOGYHIKKZW");
        /*发送邮件的邮箱和发件人*/
        mail.setFrom("lingyi550210817@126.com", "邮件小助手");
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
}
