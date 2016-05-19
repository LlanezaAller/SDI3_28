package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.business.UsuariosService;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

public class ListarUsuarios implements Action{

	@Override
	public void execute() {
		System.out.println("Listando usuarios...");
		System.out.println("");
		System.out.println("Nick\t\tNombre\t\tApellidos\t\tNº Viajes creados \t\t Nº Viajes Participados");
		UsuariosService userService = Factories.business.getUsuariosService();
		List<User> users =userService.findAll();
		
		int participatedTrips, promotedTrips;
		for(User u:users){
			participatedTrips=promotedTrips=0;
			for(Trip t: u.getTrips()){
				if(t.getStatus()==TripStatus.DONE)
					participatedTrips++;
				if(t.getPromoter().getId()==u.getId())
					promotedTrips++;
			}
			System.out.println(u.getLogin() + "\t" +u.getName() + "\t" + u.getSurname() + "\t" + promotedTrips + "\t" + participatedTrips);
		}
	}

	@Override
	public String describe() {
		return "Listar usuarios";
	}
}
