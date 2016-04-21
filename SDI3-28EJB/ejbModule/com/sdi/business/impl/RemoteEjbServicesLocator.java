package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import alb.util.log.Log;

import com.sdi.business.AsientosService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.SystemService;
import com.sdi.business.UsuariosService;
import com.sdi.business.ViajesService;

public class RemoteEjbServicesLocator implements ServicesFactory {

	private static final String USUARIOS_SERVICE_JNDI_KEY =  "SDI3-28/" 
			+ "SDI3-28EJB/"
			+ "EjbUsuariosService!"
			+ "com.sdi.business.impl.LocalUsuariosService";
	private static final String VIAJES_SERVICE_JNDI_KEY = "SDI3-28/" 
			+ "SDI3-28EJB/"
			+ "EjbViajesService!"
			+ "com.sdi.business.impl.LocalViajesService";
	private static final String ASIENTOS_SERVICE_JNDI_KEY = "SDI3-28/" 
			+ "SDI3-28EJB/"
			+ "EjbAsientosService!"
			+ "com.sdi.business.impl.LocalAsientosService";
	private static final String SYSTEM_SERVICE_JNDI_KEY = "SDI3-28/" 
			+ "SDI3-28EJB/"
			+ "EjbSystemService!"
			+ "com.sdi.business.impl.LocalSystemService";
	
	@Override
	public UsuariosService getUsuariosService() {
		Log.debug("Remote Usuarios Services Locator");
		try {
			Context ctx = new InitialContext();
			return (UsuariosService) ctx.lookup(USUARIOS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public ViajesService getViajesService() {
		Log.debug("Remote Viajes Services Locator");
		try {
			Context ctx = new InitialContext();
			return (ViajesService) ctx.lookup(VIAJES_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public AsientosService getAsientosService() {
		Log.debug("Remote Viajes Services Locator");
		try {
			Context ctx = new InitialContext();
			return (AsientosService) ctx.lookup(ASIENTOS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public SystemService getSystemService() {
		Log.debug("Remote System Services Locator");
		try {
			Context ctx = new InitialContext();
			return (SystemService) ctx.lookup(SYSTEM_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
