package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.ValoracionesService;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Rating;
import com.sdi.rest.ValoracionesServiceRest;

public class ValoracionesServiceRestImpl implements ValoracionesServiceRest{
	ValoracionesService service = Factories.business.getValoracionesService();
	
	@Override
	public List<Rating> findAllOrdered() {
		return service.findAllOrdered();
	}

	@Override
	public void remove(long id_valoracion) {
		service.remove(id_valoracion);
	}

}
