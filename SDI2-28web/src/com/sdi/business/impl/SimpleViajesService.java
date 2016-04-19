package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.ViajesService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.viajes.CancelarViajes;
import com.sdi.business.impl.classes.viajes.ListarViajes;
import com.sdi.business.impl.classes.viajes.ListarViajesAplicados;
import com.sdi.business.impl.classes.viajes.ListarViajesEstado;
import com.sdi.business.impl.classes.viajes.ListarViajesPromotor;
import com.sdi.business.impl.classes.viajes.ViajeAlta;
import com.sdi.business.impl.classes.viajes.ViajeUpdate;
import com.sdi.business.impl.classes.viajes.ViajesFinder;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

public class SimpleViajesService implements ViajesService {

	@Override
	public Trip findTrip(Long id) throws EntityNotFoundException {
		return (Trip) new CommandExecutor().execute(new ViajesFinder(id));
	}

	@Override
	public void saveTrip(Trip viaje) throws EntityAlreadyExistsException {
		new CommandExecutor().execute(new ViajeAlta(viaje));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllTrips() {
		return (List<Trip>) new CommandExecutor().execute(new ListarViajes());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllAplicantsByUserId(Long id) {
		return (List<Trip>) new CommandExecutor()
				.execute(new ListarViajesAplicados(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllByStatus(TripStatus status) {
		return (List<Trip>) new CommandExecutor()
				.execute(new ListarViajesEstado(status));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllTripsByPromoterId(Long id) {
		return (List<Trip>) new CommandExecutor()
				.execute(new ListarViajesPromotor(id));
	}

	@Override
	public void update(Trip t) {
		new CommandExecutor().execute(new ViajeUpdate(t));
	}

	@Override
	public int cancelTrips(List<TripUser> selectedTripsRelation, User user) {
		return (int) new CommandExecutor().execute(new CancelarViajes(
				selectedTripsRelation, user));
	}

}
