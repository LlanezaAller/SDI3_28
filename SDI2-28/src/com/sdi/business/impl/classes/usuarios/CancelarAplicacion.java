package com.sdi.business.impl.classes.usuarios;

import alb.util.log.Log;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class CancelarAplicacion implements Command {

	String loginUser;
	Long tripId;

	public CancelarAplicacion(String loginUser, long tripId) {
		this.loginUser = loginUser;
		this.tripId = tripId;
	}

	@Override
	public Object execute() {
		Trip trip = Factories.persistence.CreateTripFinder().findById(tripId);
		User user = Factories.persistence.CreateUserFinder().findByLogin(
				loginUser);
		Seat s = Factories.persistence.CreateSeatFinder().findByUserAndTrip(
				user.getId(), trip.getId());
		// boolean hasApplication = SdiUtil.assertHasApplication(user, trip);

		// boolean hasSeat = SdiUtil.assertHasSeat(user, trip);
		user.finAplicacion(trip);
		Log.info("Se ha cancelado una solicitud");
		if (s != null) {
			s.unlink();
			Factories.persistence.CreateSeatFinder().removeSeat(s);
			Log.info("Se ha cancelado un asiento");
		}

		return trip;
	}

}
