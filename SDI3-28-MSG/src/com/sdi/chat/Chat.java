package com.sdi.chat;

import java.io.BufferedReader;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

public class Chat implements MessageListener {
	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String SHAREMYTRIP_TOPIC_RECEIVER = "jms/topic/SDI3-28-ShareMytrip-Receiver";
	private static final String SHAREMYTRIP_TOPIC_SENDER= "jms/queue/SDI3-28-ShareMytrip-Sender";
	
	private BufferedReader console;

	private long idTrip;
	private String userLogin;

	private Session session;
	private Connection connection;
	
	private MessageProducer sender;
	private MessageConsumer receiver;

	public Chat(BufferedReader console, long idTrip, String userLogin) {
		this.console = console;
		this.idTrip = idTrip;
		this.userLogin = userLogin;

		ConnectionFactory factory = Jndi
				.getConnectionFactory(JMS_CONNECTION_FACTORY);
		Destination topic_sender = Jndi.getDestination(SHAREMYTRIP_TOPIC_SENDER); 
		Destination topic_receiver = Jndi.getDestination(SHAREMYTRIP_TOPIC_RECEIVER);
		
		try {
			connection = factory.createConnection("sdi", "password");
			connection.setClientID(userLogin);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createProducer(topic_sender);
			receiver = session.createConsumer(topic_receiver);
			//Si quisiesemos hacer la suscripción durable usaríamos este método. Necesitamos el permiso <permission type="createDurableQueue" roles="guest"/>
			//receiver = session.createDurableSubscriber((Topic) topic_receiver, userLogin);
			receiver.setMessageListener(this);
			connection.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

	public void start() {
		System.out.println("[CHAT DEL VIAJE " + idTrip + "]");
		String input;
		try {
			do {
				input = console.readLine();
				if (input != null && !input.isEmpty()) {
					if (input.equals("!exit")){
						connection.stop();
						session.close();
						connection.close();
						return;
					}else {
						sendMessage(input);
					}
				}
			} while (true);
		} catch (IOException | JMSException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(String text) {
		MapMessage msg;
		try {
			msg = session.createMapMessage();
			
			msg.setString("userLogin", userLogin);
			msg.setString("text", text);
			msg.setLong("tripID", idTrip);

			sender.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onMessage(Message arg0) {
		if (arg0 instanceof MapMessage) {
			MapMessage msg = (MapMessage) arg0;
			try {
				if(userLogin.equals(msg.getString("destinyLogin")))
				System.out.println("[" + msg.getString("senderLogin") + "]: "
						+ msg.getString("text"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
