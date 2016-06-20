package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.ws.AsientosService;
import com.sdi.ws.EJBAsientosServiceService;
import com.sdi.ws.EJBUsuariosServiceService;
import com.sdi.ws.Seat;
import com.sdi.ws.TripStatus;
import com.sdi.ws.User;
import com.sdi.ws.UsuariosService;

public class ListarUsuarios implements Action {

	@Override
	public void execute() {
		System.out.println("Listando usuarios...");
		System.out.println("");
		System.out
				.println("Nick\t\tNombre\t\tApellidos\t\tNº Viajes creados \t\t Nº Viajes Participados");
		UsuariosService userService = new EJBUsuariosServiceService().getUsuariosServicePort();
		List<User> users = userService.findAll();
		AsientosService seatService = new EJBAsientosServiceService().getAsientosServicePort();
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
					+ u.getSurname() + "\t" + promotedTrips + "\t"
					+ participatedTrips);
		}
		
	}

	@Override
	public String describe() {
		return "Listar usuarios";
	}
}
