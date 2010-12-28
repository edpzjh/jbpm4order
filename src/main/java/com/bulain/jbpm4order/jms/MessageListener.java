package com.bulain.jbpm4order.jms;

import org.springframework.jms.core.JmsTemplate;

public class MessageListener{
	private JmsTemplate jmsTemplate;
	
	public void onMessage(String body) {
		sendMessage("Server: "+body);
	}
	
	public void sendMessage(final String body){
		jmsTemplate.convertAndSend(body);
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
