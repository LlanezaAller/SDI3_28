package com.sdi.business.impl.classes.asientos;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;

public class AsientoAlta implements Command {

	Seat asiento;

	public AsientoAlta(Seat asiento) {
		this.asiento = asiento;
	}

	@Override
	public Object execute() {
		Factories.persistence.CreateSeatFinder().newSeat(asiento);
		return null;
	}

}
