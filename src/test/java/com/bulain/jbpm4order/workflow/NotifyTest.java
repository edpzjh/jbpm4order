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

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import com.bulain.common.JbpmTestCase;

public class NotifyTest extends JbpmTestCase {
	private String deploymentId;
	private String groupId;
	private Wiser wiser = new Wiser();

	public static void main(String[] args) {
		junit.textui.TestRunner.run(NotifyTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setUpJbpm();
		deploymentId = repositoryService.createDeployment()
				.addResourceFromClasspath("com/bulain/jbpm4order/workflow/notify.jpdl.xml")
				.deploy();
		
		groupId = identityService.createGroup("group1");
		identityService.createUser("user1", "user1", "user1", "user1@wiser.com");
		identityService.createUser("user2", "user2", "user2", "user2@wiser.com");
		identityService.createMembership("user1", groupId);
		identityService.createMembership("user2", groupId);
		
		wiser.setPort(2525);
	    wiser.start();
	}

	protected void tearDown() throws Exception {
		repositoryService.deleteDeploymentCascade(deploymentId);
		
		identityService.deleteGroup(groupId);
		identityService.deleteUser("user1");
		identityService.deleteUser("user2");
		
		tearDownJbpm();
		super.tearDown();
		
		wiser.stop();
	}

	public void testCandidate() throws MessagingException, IOException{
		Map<String, Object> variables = new HashMap<String, Object>(); 
		variables.put("groups", groupId);
		executionService.startProcessInstanceByKey("notify", variables);
	
		List<WiserMessage> wisMessages = wiser.getMessages();
        assertEquals(2, wisMessages.size());

        for (WiserMessage wisMessage : wisMessages) {
          Message message = wisMessage.getMimeMessage();
          
          Address[] from = message.getFrom();
          assertEquals(1, from.length);
          assertEquals("noreply@wiser.com", from[0].toString());

          Address[] expectedTo = InternetAddress.parse("user1@wiser.com,user2@wiser.com");
          Address[] to = message.getRecipients(RecipientType.TO);
          assertTrue(Arrays.equals(expectedTo, to));

          assertEquals("task1", message.getSubject());
          assertTrue(((String) message.getContent()).contains("task1"));
        }
	}
}
