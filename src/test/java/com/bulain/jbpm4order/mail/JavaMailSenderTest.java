package com.bulain.jbpm4order.mail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import com.bulain.common.ServiceTestCase;

public class JavaMailSenderTest extends ServiceTestCase {
	private JavaMailSender mailSender;
	private Wiser wiser = new Wiser();
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(JavaMailSenderTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
	    wiser.setPort(2525);
	    wiser.start();
	    
		mailSender = (JavaMailSender) applicationContext.getBean("mailSender");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		wiser.stop();
	}

	public void testSendMail() throws MessagingException, IOException{
		SimpleMailMessage mail = new SimpleMailMessage();  
        mail.setFrom("noreply@wiser.com");  
        mail.setTo("test-to@wiser.com");  
        mail.setSubject("Test email from wiser.com");  
        mail.setText("send from bulain to test-to");  
        mailSender.send(mail);  
        
        List<WiserMessage> wisMessages = wiser.getMessages();
        assertEquals(1, wisMessages.size());

        for (WiserMessage wisMessage : wisMessages) {
          Message message = wisMessage.getMimeMessage();
          
          Address[] from = message.getFrom();
          assertEquals(1, from.length);
          assertEquals("noreply@wiser.com", from[0].toString());

          Address[] expectedTo = InternetAddress.parse("test-to@wiser.com");
          Address[] to = message.getRecipients(RecipientType.TO);
          assertTrue(Arrays.equals(expectedTo, to));

          assertEquals("Test email from wiser.com", message.getSubject());

          assertEquals("send from bulain to test-to", (String) message.getContent());
        }
	}
}
