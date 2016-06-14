package com.sdi.business.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import alb.util.log.Log;

import com.sdi.business.AsientosService;
import com.sdi.business.ViajesService;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/SDI3-28-ShareMytrip-Sender") })
public class ChatListener implements MessageListener {
	
	private static final String JMS_CONNECTION_FACTORY = "java:/ConnectionFactory";
	private static final String SHAREMYTRIP_TOPIC_RECEIVER = "java://topic/SDI3-28-ShareMytrip-Receiver";
	private static final String LOG_QUEUE = "java:/queue/LogQueue";
	
	private Session session;
	private Connection connection;
	
	private MessageProducer log;
	private MessageProducer receiver;
	
	private ViajesService tripService = Factories.business.getViajesService();
	private AsientosService seatService = Factories.business
			.getAsientosService();

	@Override
	public void onMessage(Message arg0) {
		try {
			if(arg0 instanceof MapMessage){
				MapMessage msg = (MapMessage) arg0;
				String userLogin = msg.getString("userLogin");
				User sender = Factories.business.getUsuariosService().findUser(userLogin);
				long tripID = msg.getLong("tripID");
				String text = msg.getString("text");
				if (seatService.findByUserAndTrip(sender.getId(), tripID).getStatus() == SeatStatus.ACCEPTED) {
					Trip t = tripService.findTrip(tripID);
					ConnectionFactory factory = Jndi
							.getConnectionFactory(JMS_CONNECTION_FACTORY);
					Destination queue_log = Jndi.getDestination(LOG_QUEUE); 
					Destination topic_receiver = Jndi.getDestination(SHAREMYTRIP_TOPIC_RECEIVER);
					connection = factory.createConnection("sdi", "password");
					session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					log = session.createProducer(queue_log);
					receiver = session.createProducer(topic_receiver);
					for (Seat s : t.getSeats()) {
						if (s.getStatus() == SeatStatus.ACCEPTED){
							MapMessage forward = session.createMapMessage();
							forward.setString("senderLogin", userLogin);
							forward.setString("destinyLogin", s.getUser().getLogin());
							forward.setString("text", text);
							forward.setLong("tripID", tripID);
							Log.debug("Redirigiendo mensaje del usuario %s al usuario %s", userLogin,  s.getUser().getLogin());
							receiver.send(forward);
						}
					}
				} else {
					MapMessage error = session.createMapMessage();
					error.setString("user", userLogin);
					error.setLong("trip", tripID);
					error.setString("msg", text);
					log.send(error);
				}
			}
		} catch (EntityNotFoundException | JMSException e) {
			Log.error(e);
		}	
	}

}
