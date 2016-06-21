package com.sdi.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.valoraciones.ValoracionFinder;
import com.sdi.business.impl.classes.valoraciones.ValoracionOrderedAfterDateFinder;
import com.sdi.business.impl.classes.valoraciones.removeValoracion;
import com.sdi.model.Rating;
import com.sdi.model.SimpleRating;
@WebService(name="ValoracionesService")
@Stateless
public class EJBValoracionesService implements RemoteValoracionesService, LocalValoracionesService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Rating> findAll() {
		return (List<Rating>) new ValoracionFinder().execute();
	}

	@Override
	public void remove(long id_valoracion) {
		new removeValoracion(id_valoracion).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleRating> findLatestRatings(Date limitDate) {
		List<SimpleRating> result = new ArrayList<SimpleRating>();
		for(Rating r:(List<Rating>) new ValoracionOrderedAfterDateFinder(limitDate).execute()){
			result.add(new SimpleRating(r));
		}
		return result;
	}

}
