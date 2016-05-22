package com.academic.simulator;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;


public class Main {
	private static final String JMS_CONNECTION_FACTORY =
			 "jms/RemoteConnectionFactory";
	private static final String SHAREMYTRIP_TOPIC = "jms/topic/SDI3-28-ShareMytrip";
	
	public static void main(String[] args) throws JMSException {
		new Main().run();
	}

	private Session session;
	private Connection con;
	private MessageProducer sender;

	private void run() throws JMSException {
		initialize();
		for (int i = 0; i < 5; i++) {
			MapMessage msg = createMessage();
			//showMessage(msg);
			sender.send(msg);
		}
		sender.close();
		con.close();
		session.close();
	}
	
	private void initialize() throws JMSException {
		ConnectionFactory factory =
		Jndi.getConnectionFactory(JMS_CONNECTION_FACTORY);
		Destination queue = Jndi.getDestination(SHAREMYTRIP_TOPIC);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		con.start();
	}
	
	private MapMessage createMessage() throws JMSException {
		MapMessage msg = session.createMapMessage();
		msg.setString("command", "new");
		msg.setString("iduser", "123456");
		msg.setString("email", "jms@email.es");
		msg.setString("name", "JMS Name "
		+ Id.next() + " "
		+ System.currentTimeMillis());
		msg.setString("surname", "JMS Surname");
		return msg;
		}


}
