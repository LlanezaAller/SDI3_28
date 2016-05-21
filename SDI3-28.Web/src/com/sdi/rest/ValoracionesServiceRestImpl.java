package com.sdi.rest;

import java.util.List;

import com.sdi.business.ValoracionesService;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Rating;

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
