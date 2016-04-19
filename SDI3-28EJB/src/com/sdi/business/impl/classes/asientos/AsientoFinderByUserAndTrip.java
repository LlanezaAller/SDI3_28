package com.sdi.business.impl.classes.asientos;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class AsientoFinderByUserAndTrip implements Command {

	Long idUser;
	Long idTrip;

	public AsientoFinderByUserAndTrip(Long idUser, Long idTrip) {
		this.idUser = idUser;
		this.idTrip = idTrip;
	}

	@Override
	public Object execute() {
		return Factories.persistence.CreateSeatFinder().findByUserAndTrip(
				idUser, idTrip);
	}
}
