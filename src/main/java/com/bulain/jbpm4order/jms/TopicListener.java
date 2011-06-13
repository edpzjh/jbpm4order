package com.bulain.jbpm4order.jms;

public class TopicListener {
    public void onMessage(String body) {
        System.out.println("Topic: " + body);
    }
}
