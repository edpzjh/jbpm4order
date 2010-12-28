package com.bulain.jbpm4order.integration.jms;

import com.bulain.common.ServiceTestCase;
import com.bulain.jbpm4order.jms.QueueMessageConsumer;

public class QueueMessageConsumerTest extends ServiceTestCase {
	private QueueMessageConsumer queueMessageConsumer;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(QueueMessageConsumerTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		queueMessageConsumer = (QueueMessageConsumer)applicationContext.getBean("queueMessageConsumer");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testReceiveMessage() {
		String receiveMessage = queueMessageConsumer.receiveMessage();
		System.out.println("Consumer: "+receiveMessage);
		assertEquals("Server: This is a test message.", receiveMessage);
	}

}
