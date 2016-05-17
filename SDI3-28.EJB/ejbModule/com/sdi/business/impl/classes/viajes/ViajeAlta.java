package com.sdi.business.impl.classes.viajes;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;

public class ViajeAlta implements Command {

	Trip viaje;

	public ViajeAlta(Trip viaje) {
		this.viaje = viaje;
	}

	@Override
	public Object execute() {
		User user = Factories.persistence.CreateUserFinder().findByLogin(
				viaje.getPromoter().getLogin());

		viaje = new Trip(viaje.getDeparture(), viaje.getDestination(),
				viaje.getArrivalDate(), viaje.getDepartureDate(),
				viaje.getClosingDate(), viaje.getAvailablePax(),
				viaje.getAvailablePax() + 1, viaje.getEstimatedCost(),
				viaje.getComments(), TripStatus.OPEN, user);
		Factories.persistence.CreateTripFinder().newTrip(viaje);

		Seat promoSeat = new Seat(user, viaje);
		promoSeat.setStatus(SeatStatus.ACCEPTED);

		Factories.persistence.CreateSeatFinder().newSeat(promoSeat);

		return null;
	}

}
