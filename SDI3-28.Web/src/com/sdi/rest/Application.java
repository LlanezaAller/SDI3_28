package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

public class Application extends javax.ws.rs.core.Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> res = new HashSet<>();
		res.add(UsuariosServicesRestImpl.class);
		res.add(AsientosServiceRestImpl.class);
		res.add(ViajesServiceRestImpl.class);
		res.add(ValoracionesServiceRestImpl.class);
		return res;
	}
}
