package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.asientos.AsientoAlta;
import com.sdi.business.impl.classes.asientos.AsientoFinderByUser;
import com.sdi.business.impl.classes.asientos.AsientoFinderByUserAndTrip;
import com.sdi.business.impl.classes.asientos.CambiarEstadoAsiento;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
@WebService(name="AsientosService")
@Stateless
public class EJBAsientosService implements LocalAsientosService, RemoteAsientosService {

	@Override
	public void saveSeat(Seat seat) throws EntityAlreadyExistsException {
		new AsientoAlta(seat).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seat> findByUser(Long user) {
		return (List<Seat>) new AsientoFinderByUser(user).execute();
	}

	@Override
	public Seat findByUserAndTrip(Long idUser, Long idTrip)
			throws EntityNotFoundException {
		return (Seat) new AsientoFinderByUserAndTrip(idUser, idTrip).execute();
	}

	@Override
	public Trip cambiarEstadoAsiento(Seat s, String login) {
		return (Trip) new CambiarEstadoAsiento(s,
				login).execute();
	}

}
