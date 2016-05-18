package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.ValoracionesService;
import com.sdi.business.impl.classes.valoraciones.ValoracionFinder;
import com.sdi.business.impl.classes.valoraciones.removeValoracion;
import com.sdi.model.Rating;

public class EJBValoracionesService implements ValoracionesService {

	@Override
	public List<Rating> findAllOrdered() {
		return (List<Rating>) new ValoracionFinder().execute();
	}

	@Override
	public void remove(long id_valoracion) {
		new removeValoracion(id_valoracion).execute();
	}

}
