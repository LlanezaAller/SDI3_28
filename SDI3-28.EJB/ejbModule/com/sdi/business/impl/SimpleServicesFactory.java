package com.sdi.business.impl;

import com.sdi.business.AsientosService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.SystemService;
import com.sdi.business.UsuariosService;
import com.sdi.business.ViajesService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public UsuariosService getUsuariosService() {
		return new EJBUsuariosService();
	}

	@Override
	public ViajesService getViajesService() {
		return new EJBViajesService();
	}

	@Override
	public AsientosService getAsientosService() {
		return new EJBAsientosService();
	}

	@Override
	public SystemService getSystemService() {
		return new EJBSystemService();
	}

}
