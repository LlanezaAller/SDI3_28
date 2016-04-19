package com.sdi.business.impl.classes.usuarios;

import java.util.Set;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.UserFinder;

public class AplicarUsuarios implements Command {

	Set<User> usuarios;
	Trip trip;

	public AplicarUsuarios(Set<User> apps, Trip trip) {
		this.usuarios = apps;
		this.trip = trip;
	}

	@Override
	public Object execute() {
		UserFinder userFinder = Factories.persistence.CreateUserFinder();
		Trip t = Factories.persistence.CreateTripFinder()
				.findById(trip.getId());
		for (User u : usuarios) {
			userFinder.findByLogin(u.getLogin()).aplicar(t);
		}
		return null;
	}

}
