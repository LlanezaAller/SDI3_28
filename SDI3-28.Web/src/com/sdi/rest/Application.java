package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

import com.sdi.rest.impl.AsientosServiceRestImpl;
import com.sdi.rest.impl.UsuariosServicesRestImpl;
import com.sdi.rest.impl.ValoracionesServiceRestImpl;
import com.sdi.rest.impl.ViajesServiceRestImpl;

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
