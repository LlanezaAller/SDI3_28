package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.ws.EJBUsuariosServiceService;
import com.sdi.ws.EJBViajesServiceService;
import com.sdi.ws.SeatStatus;
import com.sdi.ws.TripStatus;
import com.sdi.ws.User;
import com.sdi.ws.UsuariosService;
import com.sdi.ws.ViajesService;

public class ListarUsuarios implements Action {

	@Override
	public void execute() {
		System.out.println("Listando usuarios...");
		System.out.println("");
		System.out
				.println("Nick\t\tNombre\t\tApellidos\t\tNº Viajes creados \t\t Nº Viajes Participados");
		UsuariosService userService = new EJBUsuariosServiceService().getUsuariosServicePort();
		List<User> users = userService.findAll();
		ViajesService tripService = new EJBViajesServiceService().getViajesServicePort();
		int participatedTrips, promotedTrips;
		
		for (User u : users) {
			participatedTrips = tripService.findTripsByUserAndStatus(u.getId(), TripStatus.DONE, SeatStatus.ACCEPTED).size();
			promotedTrips = tripService.findAllTripsByPromoterId(u.getId()).size();
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
