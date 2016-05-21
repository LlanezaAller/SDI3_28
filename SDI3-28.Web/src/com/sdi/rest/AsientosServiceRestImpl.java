package com.sdi.rest;

import java.util.List;

import com.sdi.business.AsientosService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;

public class AsientosServiceRestImpl implements AsientosServiceRest {
	AsientosService service = Factories.business.getAsientosService();
	@Override
	public void saveSeat(Seat seat) throws EntityAlreadyExistsException {
		service.saveSeat(seat);
	}

	@Override
	public Seat findByUserAndTrip(Long idUser, Long idTrip)
			throws EntityNotFoundException {
		return service.findByUserAndTrip(idUser, idTrip);
	}

	@Override
	public List<Seat> findByUser(Long idUser) {
		return service.findByUser(idUser);
	}

	@Override
	public Trip cambiarEstadoAsiento(Seat s, String login) {
		return service.cambiarEstadoAsiento(s, login);
	}
	
}
