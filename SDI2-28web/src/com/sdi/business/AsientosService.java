package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Seat;
import com.sdi.model.Trip;

public interface AsientosService {

	void saveSeat(Seat seat) throws EntityAlreadyExistsException;

	Seat findByUserAndTrip(Long idUser, Long idTrip)
			throws EntityNotFoundException;

	List<Seat> findByUser(Long idUser);

	Trip cambiarEstadoAsiento(Seat s, String login);

}
