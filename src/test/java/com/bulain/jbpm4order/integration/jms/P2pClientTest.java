package com.bulain.jbpm4order.integration.jms;

import javax.jms.JMSException;

import org.springframework.jms.JmsException;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.jms.P2pClient;

public class P2pClientTest extends ServiceTestCase {
	private P2pClient p2pClient;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(P2pClientTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		p2pClient = (P2pClient) applicationContext.getBean("p2pClient");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSendMessage() throws JmsException, JMSException {
		for (int i = 0; i < 20; i++) {
			String body = "This is a test message." + i;
			String messageId = p2pClient.sendMessage(body);
			String message = p2pClient.receiveMessage(messageId);
			assertEquals("Server: " + body, message);
		}
	}

}
