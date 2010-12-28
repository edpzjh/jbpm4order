package com.bulain.jbpm4order.workflow;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;

import org.jbpm.api.ProcessInstance;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import com.bulain.common.JbpmTestCase;

public class MailTest extends JbpmTestCase {
	private String deploymentId;
	private Wiser wiser = new Wiser();
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(MailTest.class);
	}	  
	
	protected void setUp() throws Exception {
	    super.setUp();
	    setUpJbpm();
	    deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("com/bulain/jbpm4order/workflow/mail.jpdl.xml")
	        .deploy();
	    wiser.setPort(2525);
	    wiser.start();
	}
	
	protected void tearDown() throws Exception {
	    repositoryService.deleteDeploymentCascade(deploymentId);
	    tearDownJbpm();
	    super.tearDown();
	    wiser.stop();
	}
	
	public void testMail() throws MessagingException, IOException{
		Map<String, Object> variables = new HashMap<String, Object>(); 
		variables.put("to", "test-to@wiser.com");
		variables.put("subject", "Test email from wiser.com");
		variables.put("text", "send from bulain to test-to");
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("mail", variables);
		
	    assertEquals(true, processInstance.isEnded());
	    
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
