package com.learnhall.logic;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class sendPswdByMail {
	public static void main(String[] args) {
		sendMail("83573858@qq.com", "12345678", "111111");
	}
	
	/*** 发送邮件 **/
	public static void sendMail(String toEmail, String lgid, String lgpwd) {
		String host = "smtp.aliyun.com";
		int port = 25;
		String username = "code_1010xue@aliyun.com";
		String password = "sxzx1010xue";
		
		Properties javaMailProperties = new Properties();  
        javaMailProperties.put("mail.smtp.auth", "true");  
        javaMailProperties.put("mail.smtp.starttls.enable", "true");  
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
        mailSender.setHost(host);  
        mailSender.setPort(port);  
        mailSender.setUsername(username);  
        mailSender.setPassword(password);  
        mailSender.setJavaMailProperties(javaMailProperties);  
        
//        String email = "as23355@163.com";  
//        String nickname = "测试数据邮件";  
  
        MimeMessage message = mailSender.createMimeMessage();  
        MimeMessageHelper help;
		try {
			help = new MimeMessageHelper(message, true, "UTF-8");
	        help.setFrom("code_1010xue@aliyun.com");  
	        help.setTo(new String[] { toEmail, toEmail });  
	        help.setSubject("尚学堂 -- 账号密码找回");  
	        String content = "您好,您的登录帐号 : " + lgid + ", 登录密码 : " + lgpwd;  
	        help.setText(content, true);  
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        mailSender.send(message); 
	}
}
