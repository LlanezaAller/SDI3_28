package com.sdi.business.integration;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Jndi {

	public static Object find(String jndiKey) {
		Context ctx;
		try {
			ctx = new InitialContext();
			return ctx.lookup(jndiKey);

		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	public static ConnectionFactory getConnectionFactory(
			String jmsConnectionFactory) {
		return (ConnectionFactory) find(jmsConnectionFactory);
	}

	public static Destination getDestination(String notaneitorQueue) {
		return (Destination) find(notaneitorQueue);
	}

}
