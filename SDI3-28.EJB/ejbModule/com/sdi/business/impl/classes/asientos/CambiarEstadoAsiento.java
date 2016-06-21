package com.sdi.business.impl.classes.asientos;

import alb.util.log.Log;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.type.SeatStatus;

public class CambiarEstadoAsiento implements Command {

	Seat seat;

	public CambiarEstadoAsiento(Seat seat, String userLogin) {
		this.seat = seat;
	}

	@Override
	public Object execute() {
		seat = Factories.persistence.CreateSeatFinder().findByUserAndTrip(
				seat.getUser().getId(), seat.getTrip().getId());
		Trip trip = Factories.persistence.CreateTripFinder().findById(
				seat.getTrip().getId());

		if (seat.getStatus() == SeatStatus.ACCEPTED) {
			seat.setStatus(SeatStatus.EXCLUDED);
			seat.getTrip()
					.setAvailablePax(seat.getTrip().getAvailablePax() + 1);
			Log.info("Asiento rechazado satisfactoriamente");
		} else if (seat.getStatus() == SeatStatus.EXCLUDED
				&& trip.getAvailablePax() > 0) {
			seat.setStatus(SeatStatus.ACCEPTED);
			seat.getTrip().setAvailablePax(trip.getAvailablePax() - 1);
			Log.info("Asiento confirmado satisfactoriamente");
		}

		else {
			Log.error(
					"No se puede rechazar el asiento: Asiento %s, Asientos libres %d",
					seat.getStatus(), trip.getAvailablePax());
		}
		return trip;
	}

}
