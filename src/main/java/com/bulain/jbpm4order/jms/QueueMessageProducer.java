package com.bulain.jbpm4order.jms;

import org.springframework.jms.core.JmsTemplate;

public class QueueMessageProducer {
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(final String body) {
		jmsTemplate.convertAndSend(body);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
