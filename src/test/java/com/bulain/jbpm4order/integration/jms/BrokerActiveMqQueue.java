package com.bulain.jbpm4order.integration.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

public final class BrokerActiveMqQueue {
	private static final Logger logger = Logger.getLogger(BrokerActiveMqQueue.class);

    private static ActiveMQConnectionFactory connectionFactory;
    private static Connection connection;
    private static Session session;
    private static Destination destination;
    private static boolean transacted = false;

    private BrokerActiveMqQueue(){}
    
    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.setUseJmx(true);
        broker.addConnector("tcp://localhost:61616");
        broker.start();

        setUp();
        createProducerAndSendAMessage();
		logger.debug("main(String[]) - Simulating a huge network delay :)");
        Thread.sleep(4000);
        createConsumerAndReceiveAMessage();

        broker.stop();
    }

    private static void setUp() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("mmy first active mq queue");
    }

    private static void createProducerAndSendAMessage() throws JMSException {
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        TextMessage message = session.createTextMessage("Hello World!");
		logger.debug("createProducerAndSendAMessage() - Sending message: " + message.getText());
        producer.send(message);
    }

    private static void createConsumerAndReceiveAMessage() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
        MessageConsumer consumer = session.createConsumer(destination);
        MyConsumer myConsumer = new MyConsumer();
        connection.setExceptionListener(myConsumer);
        consumer.setMessageListener(myConsumer);
    }

    private static class MyConsumer implements MessageListener, ExceptionListener {
		private static final Logger logger = Logger.getLogger(MyConsumer.class);

		public synchronized void onException(JMSException ex) {
			logger.debug("onException(JMSException) - JMS Exception occured.  Shutting down client.");
        }

        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
					logger.debug("onMessage(Message) - Received message: " + textMessage.getText());
                } catch (JMSException ex) {
					logger.debug("onMessage(Message) - Error reading message: "+ ex);
                }
            } else  {
				logger.debug("onMessage(Message) - Received: " + message);
            }
        }
    }
}

