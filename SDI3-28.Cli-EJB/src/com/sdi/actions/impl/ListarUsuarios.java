package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.ws.EJBUsuariosServiceService;
import com.sdi.ws.EJBViajesServiceService;
import com.sdi.ws.EntityNotFoundException_Exception;
import com.sdi.ws.Trip;
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
		long userID;
		
		try {
			for (User u : users) {
					userID = userService.getIdByLogin(u.getLogin());
				participatedTrips = promotedTrips = 0;
				for (Trip t : tripService.findAllTripsByPromoterId(userID)) {
					if (t.getStatus() == TripStatus.DONE)
						participatedTrips++;
					if (t.getPromoter().getId() == u.getId())
						promotedTrips++;
				}
				System.out.println(u.getLogin() + "\t" + u.getName() + "\t"
						+ u.getSurname() + "\t" + promotedTrips + "\t"
						+ participatedTrips);
			}
		} catch (EntityNotFoundException_Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String describe() {
		return "Listar usuarios";
	}
}
