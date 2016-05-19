package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.valoraciones.ValoracionFinder;
import com.sdi.business.impl.classes.valoraciones.removeValoracion;
import com.sdi.model.Rating;
@WebService(name="ValoracionesService")
@Stateless
public class EJBValoracionesService implements RemoteValoracionesService, LocalValoracionesService {

	@Override
	public List<Rating> findAllOrdered() {
		return (List<Rating>) new ValoracionFinder().execute();
	}

	@Override
	public void remove(long id_valoracion) {
		new removeValoracion(id_valoracion).execute();
	}

}
