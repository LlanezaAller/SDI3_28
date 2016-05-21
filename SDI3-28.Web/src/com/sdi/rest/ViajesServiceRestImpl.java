package com.sdi.rest;

import java.util.List;

import com.sdi.business.ViajesService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

public class ViajesServiceRestImpl implements ViajesServiceRest{
	ViajesService service = Factories.business.getViajesService();
	@Override
	public Trip findTrip(Long id) throws EntityNotFoundException {
		return service.findTrip(id);
	}

	@Override
	public void saveTrip(Trip viaje) throws EntityAlreadyExistsException {
		service.saveTrip(viaje);
	}

	@Override
	public List<Trip> findAllTrips() {
		return service.findAllTrips();
	}

	@Override
	public List<Trip> findAllAplicantsByUserId(Long id) {
		return service.findAllAplicantsByUserId(id);
	}

	@Override
	public List<Trip> findAllByStatus(TripStatus status) {
		return service.findAllByStatus(status);
	}

	@Override
	public List<Trip> findAllTripsByPromoterId(Long id) {
		return service.findAllTripsByPromoterId(id);
	}

	@Override
	public void update(Trip t) {
		service.update(t);
		
	}

	@Override
	public int cancelTrips(List<TripUser> selectedTripsRelation, User user) {
		return service.cancelTrips(selectedTripsRelation, user);
	}

}
