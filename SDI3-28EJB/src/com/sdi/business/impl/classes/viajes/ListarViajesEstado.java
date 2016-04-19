package com.sdi.business.impl.classes.viajes;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.type.TripStatus;

public class ListarViajesEstado implements Command {

	TripStatus status;

	public ListarViajesEstado(TripStatus status) {
		this.status = status;
	}

	@Override
	public Object execute() {
		return Factories.persistence.CreateTripFinder().findAllStatus(status);
	}

}
