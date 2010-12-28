package com.bulain.jbpm4order.jms;

import org.springframework.jms.core.JmsTemplate;

public class QueueMessageConsumer {
	private JmsTemplate jmsTemplate;

	public String receiveMessage() {
		return (String) jmsTemplate.receiveAndConvert();
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
