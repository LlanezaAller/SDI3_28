package com.sdi.business.impl.classes.viajes;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.type.SeatStatus;

public class ViajeUpdate implements Command {

	Trip t;

	public ViajeUpdate(Trip t) {
		this.t = t;
	}

	@Override
	public Object execute() {
		Trip t2 = Factories.persistence.CreateTripFinder().findById(t.getId());
		t2.setArrivalDate(t.getArrivalDate());
		t2.setClosingDate(t.getClosingDate());
		t2.setComments(t.getComments());
		t2.setDeparture(t.getDeparture());
		t2.setDepartureDate(t.getDepartureDate());
		t2.setDestination(t.getDestination());
		t2.setEstimatedCost(t.getEstimatedCost());
		t2.setMaxPax(t.getMaxPax());
		int occupied = 0;
		for (Seat s : t.getSeats())
			if (s.getStatus() == SeatStatus.ACCEPTED)
				occupied++;
		t2.setAvailablePax(t.getMaxPax() - occupied);
		return t2;
	}

}
