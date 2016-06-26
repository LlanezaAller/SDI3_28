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
				seat.getSeatUser().getId(), seat.getSeatTrip().getId());
		Trip trip = Factories.persistence.CreateTripFinder().findById(
				seat.getSeatTrip().getId());

		if (seat.getStatus() == SeatStatus.ACCEPTED) {
			seat.setStatus(SeatStatus.EXCLUDED);
			seat.getSeatTrip()
					.setAvailablePax(seat.getSeatTrip().getAvailablePax() + 1);
			Log.info("Asiento rechazado satisfactoriamente");
		} else if (seat.getStatus() == SeatStatus.EXCLUDED
				&& trip.getAvailablePax() > 0) {
			seat.setStatus(SeatStatus.ACCEPTED);
			seat.getSeatTrip().setAvailablePax(trip.getAvailablePax() - 1);
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
