package com.sdi.business.impl.classes.usuarios;

import alb.util.log.Log;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

public class Aplicar implements Command {

	String login;
	Long tripId;

	public Aplicar(String login, Long tripId) {
		this.login = login;
		this.tripId = tripId;
	}

	@Override
	public Object execute() {
		Trip trip = Factories.persistence.CreateTripFinder().findById(tripId);
		User user = Factories.persistence.CreateUserFinder().findByLogin(login);

		if (trip.getStatus() == TripStatus.OPEN) {
			user.aplicar(trip);
			Log.info("Aplicación de usuario creada con éxito");
		} else {
			Log.error("El viaje no se encuentra abierto");
		}
		return trip;
	}

}
