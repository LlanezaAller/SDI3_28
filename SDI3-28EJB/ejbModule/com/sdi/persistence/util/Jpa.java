package com.sdi.persistence.util;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Jpa {
	//TODO comprobar que la clave sea la misma
	private static final String EJB_JPA_KEY = "java:/ForumJpaFactory";

	@PersistenceContext private EntityManager mapper;
	
 	public static EntityManager getManager() {
		return Jpa.getInstance().mapper;
	}
	
	private static Jpa getInstance() {
		return (Jpa) jndiFind( EJB_JPA_KEY );
	}

	private static Object jndiFind(String name) {
		Context ctx;
		try {
			ctx = new InitialContext();
			return ctx.lookup(name);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
