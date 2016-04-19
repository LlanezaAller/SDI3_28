package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.AsientosService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.asientos.AsientoAlta;
import com.sdi.business.impl.classes.asientos.AsientoFinderByUser;
import com.sdi.business.impl.classes.asientos.AsientoFinderByUserAndTrip;
import com.sdi.business.impl.classes.asientos.CambiarEstadoAsiento;
import com.sdi.model.Seat;
import com.sdi.model.Trip;

public class SimpleAsientosService implements AsientosService {

	@Override
	public void saveSeat(Seat seat) throws EntityAlreadyExistsException {
		new CommandExecutor().execute(new AsientoAlta(seat));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seat> findByUser(Long user) {
		return (List<Seat>) new CommandExecutor()
				.execute(new AsientoFinderByUser(user));
	}

	@Override
	public Seat findByUserAndTrip(Long idUser, Long idTrip)
			throws EntityNotFoundException {
		return (Seat) new CommandExecutor()
				.execute(new AsientoFinderByUserAndTrip(idUser, idTrip));
	}

	@Override
	public Trip cambiarEstadoAsiento(Seat s, String login) {
		return (Trip) new CommandExecutor().execute(new CambiarEstadoAsiento(s,
				login));
	}

}
