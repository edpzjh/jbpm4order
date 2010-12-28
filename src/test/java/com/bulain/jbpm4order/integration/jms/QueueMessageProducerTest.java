package com.bulain.jbpm4order.integration.jms;

import com.bulain.common.ServiceTestCase;
import com.bulain.jbpm4order.jms.QueueMessageProducer;

public class QueueMessageProducerTest extends ServiceTestCase {
	private QueueMessageProducer queueMessageProducer;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(QueueMessageProducerTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		queueMessageProducer = (QueueMessageProducer)applicationContext.getBean("queueMessageProducer");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSendMessage(){
		String body = "This is a test message.";
		queueMessageProducer.sendMessage(body);
	}

}
