package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.business.AsientosService;
import com.sdi.business.UsuariosService;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.RemoteEJBServicesLocator;
import com.sdi.model.Seat;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

public class ListarUsuarios implements Action {

	@Override
	public void execute() {
		System.out.println("Listando usuarios...");
		System.out.println("");
		System.out
				.println("Nick\t\tNombre\t\tApellidos\t\tNº Viajes creados \t\t Nº Viajes Participados");
		UsuariosService userService = new RemoteEJBServicesLocator()
				.getUsuariosService();
		List<User> users = userService.findAll();
		AsientosService seatService = new RemoteEJBServicesLocator()
				.getAsientosService();

		int participatedTrips, promotedTrips;
		for (User u : users) {
			participatedTrips = promotedTrips = 0;
			for (Seat s : seatService.findByUser(u.getId())) {
				if (s.getTrip().getStatus() == TripStatus.DONE)
					participatedTrips++;
				if (s.getTrip().getPromoter().getId().equals(u.getId()))
					promotedTrips++;
			}
			System.out.println(u.getLogin() + "\t" + u.getName() + "\t"
					+ u.getSurname() + "\t" + promotedTrips + "\t\t\t"
					+ participatedTrips);
		}
	}

	@Override
	public String describe() {
		return "Listar usuarios";
	}
}
