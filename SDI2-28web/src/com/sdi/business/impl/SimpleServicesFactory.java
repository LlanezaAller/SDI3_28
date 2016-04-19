package com.sdi.business.impl;

import com.sdi.business.AsientosService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.SystemService;
import com.sdi.business.UsuariosService;
import com.sdi.business.ViajesService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public UsuariosService createUsuariosService() {
		return new SimpleUsuariosService();
	}

	@Override
	public ViajesService createViajesService() {
		return new SimpleViajesService();
	}

	@Override
	public AsientosService createAsientosService() {
		return new SimpleAsientosService();
	}

	@Override
	public SystemService createSystemService() {
		return new SimpleSystemService();
	}

}
