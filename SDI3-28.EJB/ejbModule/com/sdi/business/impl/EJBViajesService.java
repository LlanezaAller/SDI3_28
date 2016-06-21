package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.viajes.CancelarViajes;
import com.sdi.business.impl.classes.viajes.FindTripsByUserAndStatus;
import com.sdi.business.impl.classes.viajes.ListarViajes;
import com.sdi.business.impl.classes.viajes.ListarViajesAplicados;
import com.sdi.business.impl.classes.viajes.ListarViajesEstado;
import com.sdi.business.impl.classes.viajes.ListarViajesPromotor;
import com.sdi.business.impl.classes.viajes.ViajeAlta;
import com.sdi.business.impl.classes.viajes.ViajeUpdate;
import com.sdi.business.impl.classes.viajes.ViajesFinder;
import com.sdi.business.util.SdiUtil;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;

@WebService(name = "ViajesService")
@Stateless
public class EJBViajesService implements RemoteViajesService,
		LocalViajesService {

	@Override
	public Trip findTripByID(Long id) throws EntityNotFoundException {
		return (Trip) new ViajesFinder(id).execute();
	}

	@Override
	public void saveTrip(Trip viaje) throws EntityAlreadyExistsException {
		new ViajeAlta(viaje).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllTrips() {
		return (List<Trip>) new ListarViajes().execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllUserApplications(Long id) {
		return (List<Trip>) new ListarViajesAplicados(id).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllTripsByStatus(TripStatus status) {
		return (List<Trip>) new ListarViajesEstado(status).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findAllTripsByPromoterId(Long id) {
		return (List<Trip>) new ListarViajesPromotor(id).execute();
	}

	@Override
	public void update(Trip t) {
		new ViajeUpdate(t).execute();
	}

	@Override
	public int cancelTrips(List<TripUser> selectedTripsRelation, User user) {
		return (int) new CancelarViajes(selectedTripsRelation, user).execute();
	}

	@Override
	public Seat[] findSeatsFromTrip(Long id) throws EntityNotFoundException {
		return findTripByID(id).getSeats().toArray(new Seat[] {});
	}

	@Override
	public boolean hasUserApplication(User user, Trip trip) {
		return SdiUtil.assertHasApplication(user, trip);

	}

	@Override
	public boolean hasUserSeat(User user, Trip trip)
			throws EntityNotFoundException {
		return SdiUtil.assertHasSeat(user, trip);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> findTripsByUserAndStatus(Long userID,
			TripStatus tripStatus, SeatStatus seatStatus) {
		return (List<Trip>) new FindTripsByUserAndStatus(userID, tripStatus, seatStatus).execute();
	}

}
