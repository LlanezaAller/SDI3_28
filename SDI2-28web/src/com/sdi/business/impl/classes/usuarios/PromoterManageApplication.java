package com.sdi.business.impl.classes.usuarios;

import alb.util.log.Log;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;

public class PromoterManageApplication implements Command {

	String login;
	Long tripId;
	SeatStatus status;

	public PromoterManageApplication(SeatStatus status, String login,
			Long tripId) {
		this.login = login;
		this.tripId = tripId;
		this.status = status;
	}

	@Override
	public Object execute() {
		Trip trip = Factories.persistence.CreateTripFinder().findById(tripId);
		User user = Factories.persistence.CreateUserFinder().findByLogin(login);

		user.finAplicacion(trip);

		Seat s = new Seat(user, trip);
		s.setStatus(status);
		if (status == SeatStatus.ACCEPTED)
			trip.setAvailablePax(trip.getAvailablePax() - 1);

		Factories.persistence.CreateSeatFinder().newSeat(s);

		Log.info("Petición %s", status);

		return trip;
	}

}
