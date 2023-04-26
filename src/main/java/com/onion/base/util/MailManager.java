package com.onion.base.util;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailManager {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	public void send(String to, String sub, String text) throws Exception {
		
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		mailMessage.setFrom(sender);
		mailMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
		mailMessage.setSubject(sub);
		mailMessage.setText(text);
		javaMailSender.send(mailMessage);
		
		
		//HTML 태그를 무시하고 TEXT로만 전송
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setFrom(sender);
//		mailMessage.setTo(to);
//		mailMessage.setSubject(sub);
//		mailMessage.setText(text);
//		javaMailSender.send(mailMessage);
		
	}
}
