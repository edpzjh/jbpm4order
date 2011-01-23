package com.bulain.jbpm4order.integration.jms;

import org.springframework.jms.core.JmsTemplate;

import com.bulain.common.test.ServiceTestCase;

public class TopicPublicerTest extends ServiceTestCase {
	private JmsTemplate jmsTemplateC;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TopicPublicerTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		jmsTemplateC = (JmsTemplate) applicationContext.getBean("jmsTemplateC");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPublic(){
		jmsTemplateC.convertAndSend("This is a public Message!");
	}
}
