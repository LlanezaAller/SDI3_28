package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AsientosService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.SystemService;
import com.sdi.business.UsuariosService;
import com.sdi.business.ValoracionesService;
import com.sdi.business.ViajesService;

public class LocalEJBServicesLocator implements ServicesFactory {
	private static final String USUARIOS_SERVICE_JNDI_KEY = "java:global/SDI3-28/SDI3-28.EJB/EJBUsuariosService!com.sdi.business.impl.LocalUsuariosService";
	private static final String VIAJES_SERVICE_JNDI_KEY = "java:global/SDI3-28/SDI3-28.EJB/EJBViajesService!com.sdi.business.impl.LocalViajesService";
	private static final String ASIENTOS_SERVICE_JNDI_KEY = "java:global/SDI3-28/SDI3-28.EJB/EJBAsientosService!com.sdi.business.impl.LocalAsientosService";
	private static final String SYSTEM_SERVICE_JNDI_KEY = "java:global/SDI3-28/SDI3-28.EJB/EJBSystemService!com.sdi.business.impl.LocalSystemService";
	private static final String VALORACIONES_SERVICE_JNDI_KEY = "java:global/SDI3-28/SDI3-28.EJB/EJBValoracionesService!com.sdi.business.impl.LocalValoracionesService";
	@Override
	public UsuariosService getUsuariosService() {
		try {
			Context ctx = new InitialContext();
			return (UsuariosService) ctx.lookup(USUARIOS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public ViajesService getViajesService() {
		try {
			Context ctx = new InitialContext();
			return (ViajesService) ctx.lookup(VIAJES_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public AsientosService getAsientosService() {
		try {
			Context ctx = new InitialContext();
			return (AsientosService) ctx.lookup(ASIENTOS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public SystemService getSystemService() {
		try {
			Context ctx = new InitialContext();
			return (SystemService) ctx.lookup(SYSTEM_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public ValoracionesService getValoracionesService() {
		try {
			Context ctx = new InitialContext();
			return (ValoracionesService) ctx.lookup(VALORACIONES_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
